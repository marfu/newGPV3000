package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;

@Stateless
@Local(IStockArticleDAO.class)
public class StockArticleDAOImpl implements IStockArticleDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Override

    public StockArticle createOrUpdateStockArticle(StockArticle ei) {
        StockArticle temp = getStockArticle(ei.getId());
        if (temp != null) {
            em.merge(ei);
            em.flush();
            return ei;
        } else {
            em.persist(ei);
            return ei;
        }
    }

    @Override
    public StockArticle getStockArticle(long id) {
        return em.find(StockArticle.class, id);
    }

    @Override
    public boolean removeStockArticle(StockArticle ei) {
        StockArticle a = getStockArticle(ei.getId());
        if (a != null) {
            em.remove(a);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public StockArticle getStockArticleForArticle(String pvtCode, String codeArticle) {
        try {
            Query query = em.createQuery("SELECT u  FROM StockArticle u WHERE u.article.pvtCode=:pvtCode AND u.article.codeArticle=:codeArticle");
            query.setParameter("pvtCode", pvtCode);
            query.setParameter("codeArticle", codeArticle);
            return (StockArticle) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public StockArticle createStock(StockArticle entity) {
        em.persist(entity);
        return entity;
    }

}
