package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.TypeDepot;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeDepotDAO;

@Stateless
@Local(ITypeDepotDAO.class)
public class TypeDepotDAOImpl  implements ITypeDepotDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public TypeDepot createOrUpdateTypeDepot(TypeDepot ei) {
		TypeDepot temp = getTypeDepot(ei.getCode());
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
	public TypeDepot getTypeDepot(String code) {
		try{
		return em.find(TypeDepot.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeTypeDepot(TypeDepot ei) {
		TypeDepot a = getTypeDepot(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeDepot> getAllTypeDepot() {
		Query query = em
				.createQuery("SELECT u  FROM TypeDepot u ");
		return query.getResultList();
	}

}
