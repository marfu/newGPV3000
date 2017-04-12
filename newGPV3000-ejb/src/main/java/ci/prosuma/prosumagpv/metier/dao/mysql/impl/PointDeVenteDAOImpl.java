package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
@Local(IPointDeVenteDAO.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class PointDeVenteDAOImpl implements IPointDeVenteDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Resource
    private UserTransaction tx;

    @Override
    public PointDeVente createOrUpdatePVT(PointDeVente a) {
        try {
            
            PointDeVente temp = getPVT(a.getPvtCode());
            if (temp != null) {
                tx.begin();
                em.merge(a);
                em.flush();
                tx.commit();
                return a;
            } else {
                tx.begin();
                em.persist(a);
                em.flush();
                tx.commit();
                return a;
            }

        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(PointDeVenteDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return null;
        }

    }

    @Override
    public PointDeVente getPVT(String code) {
        try {
            return em.find(PointDeVente.class, code);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removePVT(PointDeVente entity) {
        try {
            PointDeVente a = getPVT(entity.getPvtCode());
            if (a != null) {
                tx.begin();
                em.remove(a);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(PointDeVenteDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PointDeVente> getAllPVTActive() {
        Query query = em.createQuery("SELECT a FROM PointDeVente a WHERE a.actif = TRUE");
        return query.getResultList();
    }

    @Override
    public PointDeVente getPvtByLdapOU(String ldapOU) {
        Query query = em.createQuery("SELECT a FROM PointDeVente a WHERE a.ldapOU=:ldapOU  ");
        query.setParameter("ldapOU", ldapOU);
        return (PointDeVente) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PointDeVente> getAllPVTDB() {
        Query query = em.createQuery("SELECT a FROM PointDeVente a");
        return query.getResultList();
    }

    @Override
    public PointDeVente getPvtByMotDirecteur(String motDirecteur) {
        Query query = em.createQuery("SELECT a FROM PointDeVente a WHERE a.motDirecteur=:motDirecteur");
        query.setParameter("motDirecteur", motDirecteur);
        return (PointDeVente) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Depot> getAllDepotForMag(String pvtCode) {
        Query query = em.createQuery("SELECT d FROM PointDeVente a  JOIN a.depot d WHERE a.pvtCode=:pvtCode");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @Override
    public Depot getDepotPrincipalForMag(String pvtCode) {
        Query query = em.createQuery("SELECT d FROM PointDeVente a  JOIN a.depot d WHERE a.pvtCode=:pvtCode AND d.depotPrincipale = TRUE");
        query.setParameter("pvtCode", pvtCode);
        List<Depot> listDep =  query.getResultList();
        if(!listDep.isEmpty()){
            return listDep.get(0);
        }else{
            return null;
        }
    }

}
