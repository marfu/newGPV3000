package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;
import ci.prosuma.prosumagpv.entity.util.TauxASDI;
import ci.prosuma.prosumagpv.entity.util.TauxTVA;

public interface IArticleService {

	/* ARTICLES METHODES **/
    
    

	public List<String> getAllCodeArticleForMag(String pvtCode);

	public List<Article> findAllArticleByCodeArticleKeyWord(String keyword,
			String pvtCode);

	public List<Article> findAllArticleByDesignationKeyWord(String keyword,
			String pvtCode);

	public Article createOrUpdateArticle(Article a);

	public Article getArticle(ArticleMagRefPK entityId);
        
        public Article getArticleWithStock(ArticleMagRefPK entityId);
	
	public Article getArticlePrincipal(Article entitySecondaire);

	public boolean removeArticle(Article entity);

	public List<Article> getAllArticleForMag(String pvtCode);

	public List<Article> getAllArticleBySecteurAndMag(String codeSecteur,
			String pvtCode);
        
         public List<Article> getAllArticleBySecteurAndMagForInventaire(String codeSecteur, String pvtCode);
        public List<Article> getAllArticleByRayonAndMagForInventaire(String codeRayon,String pvtCode);
        public List<Article> getAllArticleInGisementBySecteurAndMagForInventaire(
            String codeSecteur, long codeGisementDebut, long codeGisementFin,
            String pvtCode);
        
        public List<Article> getAllArticleInGisementByRayonAndMagForInventaire(String codeRayon,
            long codeGisementDebut, long codeGisementFin, String pvtCode);

	public List<Article> getAllArticleByRayonAndMag(String codeRayon,
			String pvtCode);

	public List<Article> getAllArticleByFamilleAndMag(String codeFamille,
			String pvtCode);

	public List<Article> getAllArticleBySousFamilleAndMag(
			String codeSousFamille, String pvtCode);

	public List<Article> getAllArticleForMagActifMagasin(String pvtCode);

	public List<Article> getAllArticleForMagCommandableCentrale(String pvtCode);

	public List<Article> getAllArticleByTypeForMag(String pvtCode,
			TypeArticle typeArticle);

	public List<Article> getAllArticleForCodeAnalityqueAndPvt(
			String codeAnalityque, String pvtCode);

	public List<Article> getAllArticleInGisementAndPvt(long codeGisementDebut,
			long codeGisementFin, String pvtCode);
        
        public List<Article> getAllArticleInGisementAndPvtForInventaire(long codeGisementDebut,
			long codeGisementFin, String pvtCode);

	public List<Article> getAllArticleForPromoAndPVT(String promo,
			String pvtCode);

	public String getDesignationForArticle(String pvtCode, String codeArticle);

	/* UTIL METHODES **/

	public float calculateHTForPrice(String taux, float price);

	public List<String> getAllCodeArticleForMagUpdated(String pvtCode);
	
	public List<Article> getAllArticleSecondaire(Article entity);

	public List<Article> getLastArticleSecondaire(Article entity, int limit);

	public List<Article> getAllArticleInGisementByRayonAndMag(String codeRayon,
			long codeGisementDebut, long codeGisementFin, String pvtCode);

	public List<Article> getAllArticleInGisementBySecteurAndMag(String codeSecteur,
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

	public List<String> getAllArticleForLienPromoAndPVT(String libelReduit,String mag);
	
	public List<String> getAllArticleByMagAndPromo(String libelReduit,String mag);
	
	public List<Article> getAllArticleByPromoAndMag(String libelReduit,String mag);
	
	public List<String> getAllArticleByHistorique(String promo, String pvtCode,Date debutPromo);
	
	public List<String> findAllArticleInInventaireNonClot(String pvtCode);
	
	public List<Article> findAllArticleByPastPromo(String codePromo, String codeMag, Date dateDebut, Date dateFin);
        
        public TauxTVA getTVAByCode(String codeTVA);
        public TauxASDI getASDIByCode(String codeASDI);
}
