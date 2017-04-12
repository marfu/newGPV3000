package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.TauxTVA;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;

@Stateless
@Local(ITypeTauxTVADAO.class)
public class TypeTauxTVADAOImpl  implements ITypeTauxTVADAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public TauxTVA createOrUpdateTauxTVA(TauxTVA ei) {
		TauxTVA temp = getTauxTVA(ei.getCode());
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
	public TauxTVA getTauxTVA(String code) {
		try{
		return em.find(TauxTVA.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeTauxTVA(TauxTVA ei) {
		TauxTVA a = getTauxTVA(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TauxTVA> getAllTauxTVA() {
		Query query = em
				.createQuery("SELECT u  FROM TauxTVA u ");
		return query.getResultList();
	}

}
