package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosuma.prosumagpv.entity.LienArtFour;

public interface IFournisseurService {

	public Fournisseur createOrUpdateFournisseur(Fournisseur a);

	public Fournisseur getFournisseur(String refFournisseur);

	public boolean removeFournisseur(Fournisseur entity);

	public List<Fournisseur> getAllFournisseur();
	
	public List<Fournisseur> findByRaisonSocialKeyWord(String keyword);
	
	public List<Fournisseur> findByRefKeyWord(String keyword);
	
	
	public String loadFournisseurDesignation(String refFour);
	
	
	
	
	
	public LienArtFour createOrUpdateLAF(LienArtFour entity);

	public LienArtFour getLAF(long id);
	
	public boolean removeLAF(LienArtFour entity);

	public List<Fournisseur> getAllFournisseurForArticle(String  codeArticle);

	public List<String> getAllArticleForFournisseur(String refFournisseur);

	public List<String>  getAllArticleCommandableForFournisseur(String refFournisseur);
	
	public List<Fournisseur> getAllFournisseurOrderBy();
	
	LienArtFour getLienArtFourByFourANdArt(String codeArticle,
			String refFournisseur);

	public List<Fournisseur> findFourByRSorRefKeyword(String keyword);
	
	

}
