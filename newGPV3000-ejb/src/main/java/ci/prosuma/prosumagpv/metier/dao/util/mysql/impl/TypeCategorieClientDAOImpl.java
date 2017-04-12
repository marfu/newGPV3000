package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.CategorieClient;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCategorieClientDAO;

@Stateless
@Local(ITypeCategorieClientDAO.class)
public class TypeCategorieClientDAOImpl  implements ITypeCategorieClientDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public CategorieClient createOrUpdateCategorieClient(CategorieClient ei) {
		CategorieClient temp = getCategorieClient(ei.getCategorie());
		if (temp != null) {
			em.merge(ei);
			em.flush();
			return ei;
		} else {
			em.persist(ei);
			em.flush();
			return ei;
		}
	}

	@Override
	public CategorieClient getCategorieClient(String categorie) {
		try{
		return em.find(CategorieClient.class, categorie);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeCategorieClient(CategorieClient ei) {
		CategorieClient a = getCategorieClient(ei.getCategorie());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategorieClient> getAllCategorieClient() {
		Query query = em
				.createQuery("SELECT u  FROM CategorieClient u ");
		return query.getResultList();
	}

}
