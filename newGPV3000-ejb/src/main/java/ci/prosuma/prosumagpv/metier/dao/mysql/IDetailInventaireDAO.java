package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.stock.DetailInventaire;
import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;

public interface IDetailInventaireDAO {
	
	
	public DetailInventaire  createOrUpdateDetailInventaire(DetailInventaire ei);
	
	public DetailInventaire  getDetailInventaire(long id);
	
	public boolean removeDetailInventaire(DetailInventaire ei);
	
	public List<DetailInventaire> findAllDetailInventaireForArticles(String codeArticle, String pvtCode);
        
        public List<DetailInventaire> findDetailInventaireByEntete(String numEntete);
        
        public List<DetailInventaire> findDetailInventaireByEntete(EnteteInventaire eneteInv);

}
