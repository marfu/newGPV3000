package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosuma.prosumagpv.metier.dao.mysql.IFournisseurDAO;

@Stateless
@Local(IFournisseurDAO.class)
@SuppressWarnings("unchecked")
public class FournisseurDAOImpl implements IFournisseurDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Fournisseur createOrUpdateFournisseur(Fournisseur a) {
		Fournisseur temp = getFournisseur(a.getRefFournisseur());
		if (temp != null) {
			em.merge(a);
			em.flush();
			return a;
		} else {
			em.persist(a);
			em.flush();
			return a;
		}

	}

	

	@Override
	public Fournisseur getFournisseur(String refFournisseur) {
		try{
		return em.find(Fournisseur.class, refFournisseur);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeFournisseur(Fournisseur entity) {
		Fournisseur a = getFournisseur(entity.getRefFournisseur());
		if(a != null){
			em.remove(a);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Fournisseur> getAllFournisseur() {
		Query query = em.createQuery("SELECT a FROM Fournisseur a ");
		return query.getResultList();
	}
	@Override
	public List<Fournisseur> getAllFournisseurOrderBy() {
		Query query = em.createQuery("SELECT a FROM Fournisseur a ORDER BY a.raisonSocial ASC ");
		return query.getResultList();
	}



	@Override
	public List<Fournisseur> findByRaisonSocialKeyWord(String keyword) {
		
		String s = "SELECT a FROM Fournisseur a WHERE  a.raisonSocial LIKE  '%"+keyword+"%' OR a.motDirecteur LIKE  '%"+keyword+"%'";
		Query q = em.createQuery(s);
		return q.getResultList();
	}



	@Override
	public List<Fournisseur> findByRefKeyWord(String keyword) {
		Query query = em.createQuery("SELECT a FROM Fournisseur a WHERE a.refFournisseur LIKE '%:keyword%' ");
		query.setParameter("keyword", keyword);
		return query.getResultList();
	}



	@Override
	public String loadFournisseurDesignation(String refFour) {
		return getFournisseur(refFour).getRaisonSocial();
	}



	@Override
	public List<Fournisseur> findFourByRSorRefKeyword(String keyword) {
		String s = "SELECT a FROM Fournisseur a WHERE a.refFournisseur LIKE '%"+keyword+"%' OR a.raisonSocial LIKE  '%"+keyword+"%'";
		Query query = em.createQuery(s);
		return query.getResultList();
	}




}
