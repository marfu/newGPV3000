package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.CategorieClient;

public interface ITypeCategorieClientDAO {
	
	
	public CategorieClient  createOrUpdateCategorieClient(CategorieClient ei);
	
	public CategorieClient  getCategorieClient(String categorie);
	
	public boolean removeCategorieClient(CategorieClient ei);
	
	public List<CategorieClient> getAllCategorieClient();
	
	


	
	

}
