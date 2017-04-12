package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.stock.DetailInventaire;
import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteInventaireDAO;
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
@Local(IEnteteInventaireDAO.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class EnteteInventaireDAOImpl implements IEnteteInventaireDAO,
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    protected EntityManager em;

    @Resource
    public UserTransaction tx;

    @Override
    public EnteteInventaire createOrUpdateEnteteInventaire(EnteteInventaire ei) {
        EnteteInventaire temp = getEnteteInventaire(ei.getId());
        if (temp != null) {
            try {
                tx.begin();
                em.merge(ei);
                em.flush();
                tx.commit();
                return ei;
            } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
                Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            em.persist(ei);
            return ei;
        }
    }

    @Override
    public EnteteInventaire getEnteteInventaire(long id) {
        EnteteInventaire a = em.find(EnteteInventaire.class, id);
        return a;

    }

    @Override
    public EnteteInventaire getEnteteInventaireWithLazyLoad(long id) {
        EnteteInventaire a = em.find(EnteteInventaire.class, id);
        if (a != null && a.getDetailInventaire() != null) {
            a.getDetailInventaire().size();
        }
        return a;
    }

    @Override
    public boolean removeEnteteInventaire(EnteteInventaire ei) {
        try {
            tx.begin();
            EnteteInventaire a = getEnteteInventaire(ei.getId());
            if (a != null) {
                
                em.remove(a);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMag(String pvtCode) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.pvt.pvtCode=:pvtCode");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagBySecteurAndRayon(
            String pvtCode, String codeSecteur, String codeRayon) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.secteur.code=:codeSecteur AND u.rayon.code=:codeRayon AND u.pvt.pvtCode=:pvtCode");
        query.setParameter("codeSecteur", codeSecteur);
        query.setParameter("codeRayon", codeRayon);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagGenerer(
            String pvtCode) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.pvt.pvtCode=:pvtCode AND u.generer = TRUE");
        query.setParameter("pvtCode", pvtCode);
        List<EnteteInventaire> listTemp = query.getResultList();
        if (listTemp != null) {
            for (EnteteInventaire ei : listTemp) {
                if (ei.getDetailInventaire() != null) {
                    ei.getDetailInventaire().size();
                }
            }
        }
        return listTemp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagLancer(
            String pvtCode) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.pvt.pvtCode=:pvtCode AND u.lancer = TRUE  AND u.cloturer = FALSE AND u.generer = TRUE");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagNonCloturer(
            String pvtCode) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.pvt.pvtCode=:pvtCode AND u.cloturer = FALSE AND u.generer = TRUE");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagCloturer(
            String pvtCode) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.pvt.pvtCode=:pvtCode AND u.cloturer = TRUE");
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagByGisement(
            String pvtCode, String gisementDebut, String gisementFin) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE u.gisementDebut >= :gisementDebut AND u.gisementFin <= :gisementFin AND u.pvt.pvtCode=:pvtCode");
        query.setParameter("gisementDebut", gisementDebut);
        query.setParameter("gisementFin", gisementFin);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> getAllEnteteInventaireForMagByDateRange(
            String pvtCode, Date dateDebut, Date dateFin) {
        Query query = em
                .createQuery("SELECT u  FROM EnteteInventaire u WHERE (u.dateInventaire BETWEEN :dateDebut AND :dateFin ) AND u.pvt.pvtCode=:pvtCode");
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DetailInventaire> getAllDetailInvetaireForEntete(
            long enteteId) {
        Query query = em
                .createQuery("SELECT u.detailInventaire  FROM EnteteInventaire u WHERE  u.id=:id");
        query.setParameter("id", enteteId);
        return query.getResultList();
    }

    @Override
    public DetailInventaire getDetailForInventaireAndArticle(long enteteInv,
            String codeArticle) {
        try {
            Query query = em
                    .createQuery("SELECT dt FROM EnteteInventaire a   JOIN a.detailInventaire dt  WHERE a.id=:id  AND  dt.article.codeArticle=:codeArticle");
            query.setParameter("id", enteteInv);
            query.setParameter("codeArticle", codeArticle);
            return (DetailInventaire) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("null");
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EnteteInventaire> executeLazyQuery(String query,
            int first, int pageSize) {
        Query q = em.createQuery(query);
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    @Override
    public Long getRowCount(String query) {
        Query q = em.createQuery(query);
        return (Long) q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getAllArticleForEnteteInventaire(long enteteId) {
        Query query = em
                .createQuery("SELECT d.Article FROM EnteteInventaire e JOIN e.detailInventaire d JOIN d.Article WHERE  e.id=:id");
        query.setParameter("id", enteteId);
        return query.getResultList();
    }

    @Override
    public Boolean checkArticleExistInInventaireOutstanding(String codeArticle, String pvtCode) {
        Query query = em
                .createQuery("SELECT dt FROM EnteteInventaire e JOIN e.detailInventaire dt  WHERE e.cloturer=false   AND e.pvt.pvtCode=:pvtCode AND dt.article.codeArticle=:codeArticle");
        query.setParameter("codeArticle", codeArticle);
        query.setParameter("pvtCode", pvtCode);
        return query.getResultList().isEmpty();

    }

    private String getLastIdByMag(String pvt) {
        Query query = em.createNativeQuery("select  NUMERO_INVENTAIRE from INVENTAIRE_ENTETE where CODE_MAGASIN_FK=:pvt order by ENTETE_INVENTAIRE_PK desc");
        query.setMaxResults(1);
        query.setParameter("pvt", pvt);
        try {
            String result = (String) query.getSingleResult();
            Integer num = Integer.valueOf(result) + 1;
            String id =  num.toString();
            return id;
        } catch (Exception ex) {
            return "1";
        }
    }

    @Override
    public EnteteInventaire saveInventaire(EnteteInventaire ei) {

        try {
            ei.setNumInventaire(getLastIdByMag(ei.getPvt().getPvtCode()));
            try {
                tx.begin();
                em.persist(ei);
                tx.commit();
                return ei;
            } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
                Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } catch (javax.persistence.PersistenceException ex) {
            try {
                tx.rollback();
                System.out.println("@@@@@@@@@@@@@@@@@@@@@  erreur  :      " + ex.getMessage());
                System.out.println("@@@@@@@@@@@@@@@@@@@@@  erreur 2  :      " + ex.getClass().getName());
                boolean rep = true;
                while (rep) {
                    ei.setNumInventaire(getLastIdByMag(ei.getPvt().getPvtCode()));
                    try {
                        tx.begin();
                        em.persist(ei);
                        tx.commit();
                        rep = false;
                    } catch (javax.persistence.PersistenceException e) {
                        tx.rollback();
                        System.out.println("@@@@@@@@@@ Error :  " + e.getMessage());
                    } catch (NotSupportedException | HeuristicMixedException | HeuristicRollbackException | RollbackException ex1) {
                        Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                }
                return ei;
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
        }

    }

    @Override
    public EnteteInventaire getEnteteInventaireWithLazyLoadAndJoinFetch(long id) {
        Query query = em.createQuery("select e from EnteteInventaire e Join FETCH e.detailInventaire where e.id=:id");
        query.setParameter("id", id);
        try {
            return (EnteteInventaire) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public EnteteInventaire updateInventaire(EnteteInventaire ei) {
        try {
            tx.begin();
            em.merge(ei);
            tx.commit();
            return ei;
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            Logger.getLogger(EnteteInventaireDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
