package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosuma.prosumagpv.entity.LienArtFour;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienArtFourDAO;

@Stateless
@Local(ILienArtFourDAO.class)
public class LienArtFourDAOImpl implements ILienArtFourDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public LienArtFour createOrUpdateLAF(LienArtFour a) {
		LienArtFour temp = getLAF(a.getId());
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
	public LienArtFour getLAF(long id) {
		return em.find(LienArtFour.class, id);
	}

	@Override
	public boolean removeLAF(LienArtFour entity) {
		LienArtFour a = getLAF(entity.getId());
		if(a != null){
			em.remove(a);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public LienArtFour getLienArtFourByFourANdArt(String codeArticle,String refFournisseur) {
		try{
		Query  query =  em.createQuery("SELECT a FROM LienArtFour a WHERE a.article=:codeArticle AND a.fournisseur=:refFournisseur ");
		query.setParameter("codeArticle", codeArticle);
		query.setParameter("refFournisseur", refFournisseur);
		return  (LienArtFour) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fournisseur> getAllFournisseurForArticle(String codeArticle) {
		Query  query =  em.createQuery("SELECT a.fournisseur FROM LienArtFour a WHERE a.article=:codeArticle ");
		query.setParameter("codeArticle", codeArticle);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllArticleForFournisseur(String refFournisseur) {
		Query  query =  em.createQuery("SELECT a.article FROM LienArtFour a WHERE a.fournisseur=:refFournisseur  ");
		query.setParameter("refFournisseur", refFournisseur);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllArticleCommandableForFournisseur(String refFournisseur){
		try{
			Query  query =  em.createQuery("SELECT a.article FROM LienArtFour a WHERE a.fournisseur=:refFournisseur AND a.commandable = TRUE  ");
			query.setParameter("refFournisseur", refFournisseur);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	

}
