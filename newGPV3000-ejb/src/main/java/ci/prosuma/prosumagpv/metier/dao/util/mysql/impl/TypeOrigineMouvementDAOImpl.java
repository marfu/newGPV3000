package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;

@Stateless
@Local(ITypeOrigineMouvementDAO.class)
public class TypeOrigineMouvementDAOImpl  implements ITypeOrigineMouvementDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public OrigineMouvement createOrUpdateOrigineMouvement(OrigineMouvement ei) {
		OrigineMouvement temp = getOrigineMouvement(ei.getCode());
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
	public OrigineMouvement getOrigineMouvement(String code) {
		try{
		return em.find(OrigineMouvement.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeOrigineMouvement(OrigineMouvement ei) {
		OrigineMouvement a = getOrigineMouvement(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrigineMouvement> getAllOrigineMouvement() {
		Query query = em.createQuery("SELECT u  FROM OrigineMouvement u ");
		return query.getResultList();
	}

}
