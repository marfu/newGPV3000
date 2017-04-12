package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import ci.prosuma.prosumagpv.entity.pk.EnteteSuggestionPK;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteSuggestionCommandeDAO;

@Stateless
@Local(IEnteteSuggestionCommandeDAO.class) 
public class EnteteSuggestionCommandeDAOImpl implements IEnteteSuggestionCommandeDAO, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;

	@Override
	public EnteteSuggestionCommande createOrUpdateESC(EnteteSuggestionCommande a) {
		EnteteSuggestionCommande temp = getESC(new EnteteSuggestionPK(a.getPvtCode(), a.getNumDossier()));
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
	public EnteteSuggestionCommande getESC(EnteteSuggestionPK  pk) {
		try{
			EnteteSuggestionCommande esc =  em.find(EnteteSuggestionCommande.class, pk);
			if(esc != null && esc.getDetailSuggestionCommande() !=null){
				esc.getDetailSuggestionCommande().size();
			}
			return esc;
		} catch (Exception e) {
			return null;
		}		
	}

	@Override
	public boolean removeESC(EnteteSuggestionCommande entity) {
		EnteteSuggestionCommande a = getESC(new EnteteSuggestionPK(entity.getPvtCode(),entity.getNumDossier()));
		if(a != null){
			em.remove(a);
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnteteSuggestionCommande> getAllESCForMag(String pvtCode) {
		Query query = em.createQuery("SELECT u  FROM EnteteSuggestionCommande u WHERE u.pvtCode=:pvtCode ORDER BY u.dateSuggestion DESC");
		query.setParameter("pvtCode", pvtCode);
		List<EnteteSuggestionCommande> result =  query.getResultList();
		//3s
		/*
		if(result != null){
			for(EnteteSuggestionCommande ec : result){
				if(ec.getDetailSuggestionCommande() != null)
					ec.getDetailSuggestionCommande().size();
			}
		}
		*/
		return result;
	}
	
	
	
	

}
