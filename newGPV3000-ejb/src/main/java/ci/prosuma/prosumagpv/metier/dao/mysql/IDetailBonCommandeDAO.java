package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import java.util.List;

public interface IDetailBonCommandeDAO {
	
	public DetailBonCommandeFournisseur createOrUpdateDBCF(DetailBonCommandeFournisseur entity);
	

	public DetailBonCommandeFournisseur getDBCF(long entityId) ;

	public boolean removeDBCF(DetailBonCommandeFournisseur entity) ;
        
        public List<DetailBonCommandeFournisseur> getDetailsByEBCF(long id);


	

}
