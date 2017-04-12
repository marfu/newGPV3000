package ci.prosuma.metier.dataexchange.jdbc.db2.dao;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Fournisseur;

public interface IFournisseurAS400DAO {
	
	public List<Fournisseur> getAllFournisseurAS400() ;
	
	
	
	public String getEmailByFournisseurAS400(String refFour) ;

}
