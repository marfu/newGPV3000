package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.dto.ArticleDTO;
import ci.prosuma.prosumagpv.entity.dto.CommandeDTO;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;

public interface IArticleDAO {

	public String getDesignationForArticle(String pvtCode, String codeArticle);

	public Article createOrUpdateArticle(Article a);
	
	public List<Article> getAllArticleForConsigneAndPvt(String pvtCode);

	public Article getArticle(ArticleMagRefPK entityId);
        
        public Article getArticleNative(ArticleMagRefPK entityId);
        
        public Article getArticleWithStock(ArticleMagRefPK entityId);
	
	public String getArticleByCodeAndMag(String mag,String codeArticle);
	
	public Article getArticlePrincipal(Article entitySecondaire);

	public boolean removeArticle(Article entity);

	public List<Article> getAllArticleForMag(String pvtCode);
        
        public List<ArticleDTO> getAllArticleDTOForMag(String pvtCode);

	public List<Article> getAllArticleSecondaire(Article entity);

	public List<String> getAllCodeArticleForMag(String pvtCode);

	public List<String> getAllCodeArticleForMagUpdated(String pvtCode);

	public List<Article> getAllArticleBySecteurAndMag(String codeSecteur, String pvtCode);

	public List<Article> getAllArticleByRayonAndMag(String codeRayon, String pvtCode);

	public List<Article> getAllArticleByFamilleAndMag(String codeFamille, String pvtCode);

	public List<Article> getAllArticleBySousFamilleAndMag(String codeSousFamille, String pvtCode);

	public List<Article> getAllArticleForMagActifMagasin(String pvtCode);

	public List<Article> getAllArticleForMagCommandableCentrale(String pvtCode);

	public List<Article> getAllArticleByTypeForMag(String pvtCode, TypeArticle typeArticle);

	public List<Article> getAllArticleForCodeAnalityqueAndPvt(String codeAnalityque, String pvtCode);

	public List<Article> getAllArticleInGisementAndPvt(long codeGisementDebut, long codeGisementFin, String pvtCode);
        public List<Article> getAllArticleBySecteurAndMagForInventaire(String codeSecteur, String pvtCode);
        public List<Article> getAllArticleByRayonAndMagForInventaire(String codeRayon,String pvtCode);
        public List<Article> getAllArticleInGisementBySecteurAndMagForInventaire(
            String codeSecteur, long codeGisementDebut, long codeGisementFin,
            String pvtCode);
        
        public List<Article> getAllArticleInGisementByRayonAndMagForInventaire(String codeRayon,
            long codeGisementDebut, long codeGisementFin, String pvtCode);
        
        public List<Article> getAllArticleInGisementAndPvtForInventaire(long codeGisementDebut,
			long codeGisementFin, String pvtCode);

	public List<Article> executeQueryForMag(String query, String pvtCode);

	public List<Article> getAllArticleForPromoAndPVT(String promo, String pvtCode);
	
	/* modif By AYEPI*/
	public List<Article> getAllArticleForPromoAndMag(String promo, String pvtCode);
        
        public List<CommandeDTO> findAllArticleForPromoAndMag(String promo, String pvtCode);

	public List<Article> getLastArticleSecondaire(Article entity, int limit);

	public List<Article> getAllArticleInGisementBySecteurAndMag(String codeSecteur,
			long codeGisementDebut, long codeGisementFin, String pvtCode);

	public List<Article> getAllArticleInGisementByRayonAndMag(String codeRayon,
			long codeGisementDebut, long codeGisementFin, String pvtCode);

	public List<Article> getListArticleStockZero(Date dateDebut, Date dateFin,
			String codeSecteur, String codeRayon, String codeFamille,
			String codeSousFamille, long codeGisementDebut,
			long codeGisementFin, String pvtCode);

	public List<Article> getAllArticleInGisementBySecRayFamSousFamAndMag(
			Date dateDebut, Date dateFin,
			String codeSecteur, String codeRayon, String codeFamille,
			String codeSousFamille, long codeGisementDebut,
			long codeGisementFin, String pvtCode);

	public List<String> getAllArticleForMagAndPVTAndLAP(String promo, String pvtCode);
	
	public List<Article> getAllArticleByMagAndPromo(String promo, String pvtCode);

	public List<String> getAllArticleForLienPromoAndPVT(String promo, String pvtCode);
	
	public List<String> getAllArticleByHistorique(String promo, String pvtCode,Date debutPromo);

	public List<String> findAllArticleInInventaireNonClot(String pvtCode);

	void UpdatePrixZero();
	
	public List<Article> getAllArticleByMagForUpdate(String pvt);
	
	public List<String> getAllCodeArticleByMagAndPromo(String promo, String pvtCode);
        
        public List<Article> findAllArticleByCodeArticleKeyWord(String keyword, String pvtCode);

}
