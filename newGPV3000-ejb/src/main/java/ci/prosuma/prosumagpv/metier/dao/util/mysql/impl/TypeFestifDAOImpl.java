package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.util.Festif;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFestifDAO;

@Stateless
@Local(ITypeFestifDAO.class)
public class TypeFestifDAOImpl implements ITypeFestifDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Festif createOrUpdateFestif(Festif ei) {
		Festif temp = getFestif(ei.getId());
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
	public Festif getFestif(long code) {
		try {
			return em.find(Festif.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeFestif(Festif ei) {
		Festif a = getFestif(ei.getId());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Festif> getAllFestif() {
		Query query = em.createQuery("SELECT u  FROM Festif u ");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticleFestif(String pvtCode) {
		Query q = em
				.createQuery("SELECT f.article From Festif f WHERE f.article.pvtCode=:pvtCode");
		q.setParameter("pvtCode", pvtCode);
		return q.getResultList();

	}

	@Override
	public boolean isArticleFestif(String codeArticle, String pvtCode) {
		Query q = em
				.createQuery("SELECT f.article From Festif f WHERE f.article.codeArticle=:codeArticle AND f.article.pvtCode=:pvtCode");
		q.setParameter("pvtCode", pvtCode);
		q.setParameter("codeArticle", codeArticle);
		return !q.getResultList().isEmpty();

	}

	@Override
	public Festif getFestif(String codeArticle, String mag) {
		try {
			Query q = em
					.createQuery("SELECT f From Festif f WHERE f.article.codeArticle=:codeArticle AND f.article.pvtCode=:pvtCode");
			q.setParameter("pvtCode", mag);
			q.setParameter("codeArticle", codeArticle);
			q.setMaxResults(1);
			return (Festif) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("ERREUR FESTIF INTROUVABLE : " + codeArticle + " "
					+ mag);
			return null;
		}
	}

}
