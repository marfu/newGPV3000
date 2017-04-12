package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailBonCommandeDAO;
import java.util.List;
import javax.persistence.Query;

@Stateless
@Local(IDetailBonCommandeDAO.class)
public class DetailBonCommandeDAOImpl implements IDetailBonCommandeDAO,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;

	@Override
	public DetailBonCommandeFournisseur createOrUpdateDBCF(
			DetailBonCommandeFournisseur a) {
		DetailBonCommandeFournisseur temp = getDBCF(a.getId());
		if (temp != null) {
			em.merge(a);
			em.flush();
			return a;
		} else {
			em.persist(a);
			return a;
		}
	}

	@Override
	public DetailBonCommandeFournisseur getDBCF(long entityId) {
		try{
		return em.find(DetailBonCommandeFournisseur.class, entityId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeDBCF(DetailBonCommandeFournisseur entity) {
		DetailBonCommandeFournisseur a = getDBCF(entity.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

    @Override
    public List<DetailBonCommandeFournisseur> getDetailsByEBCF(long id) {
        Query query = em.createQuery("SELECT d FROM DetailBonCommandeFournisseur d Where d.enteteBCF.id=:enteteid");
        query.setParameter("enteteid", id);
        return query.getResultList();
    }

}
