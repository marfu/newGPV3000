package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Fournisseur;

public interface IFournisseurDAO {

	public Fournisseur createOrUpdateFournisseur(Fournisseur a);

	public Fournisseur getFournisseur(String refFournisseur);

	public boolean removeFournisseur(Fournisseur entity);

	public List<Fournisseur> getAllFournisseur();
	
	public List<Fournisseur> getAllFournisseurOrderBy();
	
	public List<Fournisseur> findByRaisonSocialKeyWord(String keyword);
	
	public List<Fournisseur> findByRefKeyWord(String keyword);
	
	public List<Fournisseur> findFourByRSorRefKeyword(String keyword);
	
	public String loadFournisseurDesignation(String refFour);
	
}
