package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;

@Stateless
@Local(ITypeMouvementStockDAO.class)
public class TypeMouvementStockDAOImpl  implements ITypeMouvementStockDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public TypeMouvementStock createOrUpdateTypeMouvementStock(TypeMouvementStock ei) {
		TypeMouvementStock temp = getTypeMouvementStock(ei.getCode());
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
	public TypeMouvementStock getTypeMouvementStock(String code) {
		try{
		return em.find(TypeMouvementStock.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeTypeMouvementStock(TypeMouvementStock ei) {
		TypeMouvementStock a = getTypeMouvementStock(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMouvementStock> getAllTypeMouvementStock() {
		Query query = em
				.createQuery("SELECT u  FROM TypeMouvementStock u ");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMouvementStock> getAllTypeMouvementStockByOrigineMouvement(String origineMouvement ) {
		Query query = em
				.createQuery("SELECT u  FROM TypeMouvementStock u WHERE u.origineMouvement=:origineMouvement ");
		query.setParameter("origineMouvement", origineMouvement);
		return query.getResultList();
	}
	

}
