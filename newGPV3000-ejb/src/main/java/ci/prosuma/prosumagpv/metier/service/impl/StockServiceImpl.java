package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;
import ci.prosuma.prosumagpv.metier.service.IStockArticleService;

@Stateless
@Local(IStockArticleService.class)
public class StockServiceImpl implements IStockArticleService , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IStockArticleDAO stockArticleDAO;

	@Override
	public StockArticle createOrUpdateStockArticle(StockArticle entity) {
		return stockArticleDAO.createOrUpdateStockArticle(entity);
	}

	@Override
	public StockArticle getStockArticle(long entityId) {
		return stockArticleDAO.getStockArticle(entityId);
	}

	@Override
	public boolean removeStockArticle(StockArticle entity) {
		return stockArticleDAO.removeStockArticle(entity);
	}

	

	
	
}
