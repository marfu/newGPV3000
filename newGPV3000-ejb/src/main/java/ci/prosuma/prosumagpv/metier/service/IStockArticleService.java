package ci.prosuma.prosumagpv.metier.service;

import ci.prosuma.prosumagpv.entity.stock.StockArticle;

public interface IStockArticleService {

	public StockArticle createOrUpdateStockArticle(StockArticle entity);

	public StockArticle getStockArticle(long entityId);

	public boolean removeStockArticle(StockArticle entity);


}
