package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.ModeReglement;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeModeReglementDAO;

@Stateless
@Local(ITypeModeReglementDAO.class)
public class TypeModeReglementDAOImpl  implements ITypeModeReglementDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public ModeReglement createOrUpdateModeReglement(ModeReglement ei) {
		ModeReglement temp = getModeReglement(ei.getCode());
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
	public ModeReglement getModeReglement(String code) {
		try{
		return em.find(ModeReglement.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeModeReglement(ModeReglement ei) {
		ModeReglement a = getModeReglement(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModeReglement> getAllModeReglement() {
		Query query = em
				.createQuery("SELECT u  FROM ModeReglement u ");
		return query.getResultList();
	}

}
