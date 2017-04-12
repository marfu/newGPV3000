package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.LienArtPromo;

public interface ILienArtPromoDAO {

	public LienArtPromo createOrUpdateLAP(LienArtPromo entity);

	public LienArtPromo getLAP(long id);

	public boolean removeLAP(LienArtPromo entity);

	public List<String> getAllPromoForArticle(String codeArticle);

	public List<String> getAllArticleForPromo(String promo);

	public LienArtPromo getLienArtPromoByPromoAndArt(String codeArticle,
			String promo);

	public void uncheckAllPromoForArticle(String numArticle);

	public List<LienArtPromo> getAllLienArtPromoForArticle(String codeArticle);

}
