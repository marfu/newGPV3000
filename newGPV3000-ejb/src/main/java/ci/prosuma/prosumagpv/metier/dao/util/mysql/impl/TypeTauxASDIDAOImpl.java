package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.TauxASDI;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxASDIDAO;

@Stateless
@Local(ITypeTauxASDIDAO.class)
public class TypeTauxASDIDAOImpl  implements ITypeTauxASDIDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public TauxASDI createOrUpdateTauxASDI(TauxASDI ei) {
		TauxASDI temp = getTauxASDI(ei.getCode());
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
	public TauxASDI getTauxASDI(String code) {
		try{
		return em.find(TauxASDI.class, code);
		
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeTauxASDI(TauxASDI ei) {
		TauxASDI a = getTauxASDI(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TauxASDI> getAllTauxASDI() {
		Query query = em
				.createQuery("SELECT u  FROM TauxASDI u ");
		return query.getResultList();
	}

}
