package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.LienArtPromo;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienArtPromoDAO;

@Stateless
@Local(ILienArtPromoDAO.class)
public class LienArtPromoDAOImpl implements ILienArtPromoDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public LienArtPromo createOrUpdateLAP(LienArtPromo a) {
		LienArtPromo temp = getLAP(a.getId());
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
	public LienArtPromo getLAP(long id) {
		return em.find(LienArtPromo.class, id);
	}

	@Override
	public boolean removeLAP(LienArtPromo entity) {
		LienArtPromo a = getLAP(entity.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LienArtPromo getLienArtPromoByPromoAndArt(String codeArticle,
			String promo) {
		try {
			Query query = em.createQuery("SELECT a FROM LienArtPromo a WHERE a.article=:codeArticle AND a.promo=:promo AND a.actif = TRUE ");
			query.setParameter("codeArticle", codeArticle);
			query.setParameter("promo", promo);
			return (LienArtPromo) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllPromoForArticle(String codeArticle) {
		Query query = em.createQuery("SELECT a.promo FROM LienArtPromo a WHERE a.article=:codeArticle AND a.actif = TRUE ");
		query.setParameter("codeArticle", codeArticle);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LienArtPromo> getAllLienArtPromoForArticle(String codeArticle) {
		try {
			Query query = em.createQuery("SELECT a FROM LienArtPromo a WHERE a.article=:codeArticle");
			query.setParameter("codeArticle", codeArticle);
			return (List<LienArtPromo>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllArticleForPromo(String promo) {
		Query query = em.createQuery("SELECT a.article FROM LienArtPromo a WHERE a.promo=:promo AND a.actif = TRUE ");
		query.setParameter("promo", promo);
		return query.getResultList();
	}

	@Override
	public void uncheckAllPromoForArticle(String numArticle) {
		List<LienArtPromo> listLAP = getAllLienArtPromoForArticle(numArticle);
		for (LienArtPromo lienArtPromo : listLAP) {
			lienArtPromo.setActif(false);
			createOrUpdateLAP(lienArtPromo);
		}
	}

}
