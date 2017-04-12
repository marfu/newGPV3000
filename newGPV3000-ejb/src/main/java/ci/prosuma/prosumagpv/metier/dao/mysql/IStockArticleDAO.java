package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.stock.StockArticle;

public interface IStockArticleDAO {

	public StockArticle createOrUpdateStockArticle(StockArticle entity);

	public StockArticle getStockArticle(long entityId);

	public boolean removeStockArticle(StockArticle entity);

	public StockArticle getStockArticleForArticle(String pvtCode,String codeArticle);
        
        public StockArticle createStock(StockArticle entity);


}
