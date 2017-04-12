package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailSuggestionCommandeDAO;
import java.util.List;
import javax.persistence.Query;

@Stateless
@Local(IDetailSuggestionCommandeDAO.class)
public class DetailSuggestionCommandeDAOImpl implements IDetailSuggestionCommandeDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public DetailSuggestionCommande createOrUpdateDSC(DetailSuggestionCommande a) {
		DetailSuggestionCommande temp = getDSC(a.getId());
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
	public DetailSuggestionCommande getDSC(long entityId) {
		try{
		return em.find(DetailSuggestionCommande.class, entityId);
	} catch (Exception e) {
		return null;
	}
	}
	
	@Override
	public boolean removeDSC(DetailSuggestionCommande entity) {
		DetailSuggestionCommande a = getDSC(entity.getId());
		if(a != null){
			em.remove(a);
			return true;
		}else{
			return false;
		}
	}

    @Override
    public List<DetailSuggestionCommande> getAllDetailSugestionWithquantityByEnteteSug(EnteteSuggestionCommande entete) {
        Query query = em.createQuery("Select e from DetailSuggestionCommande e where e.enteteSuggestion=:entete  and e.qteUCCommander>0");
        query.setParameter("entete", entete);
        return query.getResultList();
    }

	
	
	

	

	
	
}
