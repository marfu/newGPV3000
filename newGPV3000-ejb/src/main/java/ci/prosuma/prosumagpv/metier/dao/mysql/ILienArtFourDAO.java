package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosuma.prosumagpv.entity.LienArtFour;

public interface ILienArtFourDAO {

	public LienArtFour createOrUpdateLAF(LienArtFour entity);

	public LienArtFour getLAF(long id);
	
	public boolean removeLAF(LienArtFour entity);

	public List<Fournisseur> getAllFournisseurForArticle(String  codeArticle);

	public List<String> getAllArticleForFournisseur(String refFournisseur);

	public List<String>  getAllArticleCommandableForFournisseur(String refFournisseur);

	public LienArtFour getLienArtFourByFourANdArt(String codeArticle,
			String refFournisseur);

	
}
