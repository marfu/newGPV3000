package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.SousFamille;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSousFamilleDAO;

@Stateless
@Local(ITypeSousFamilleDAO.class)
public class TypeSousFamilleDAOImpl  implements ITypeSousFamilleDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public SousFamille createOrUpdateSousFamille(SousFamille ei) {
		SousFamille temp = getSousFamille(ei.getCode());
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
	public SousFamille getSousFamille(String code) {
		try{
		return em.find(SousFamille.class, code);
	} catch (Exception e) {
		return null;
	}
	}

	@Override
	public boolean removeSousFamille(SousFamille ei) {
		SousFamille a = getSousFamille(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SousFamille> getAllSousFamille() {
		Query query = em
				.createQuery("SELECT u  FROM SousFamille u ");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SousFamille> getAllSousFamilleBySecteurAndRayonAndFamille(String codeFamille) {
		Query query = em.createQuery("SELECT u  FROM SousFamille u  WHERE u.code LIKE :codeFamille");
		query.setParameter("codeFamille", codeFamille+"%");
		return query.getResultList();
	}

}
