package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.HistoriquePromo;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;
import ci.prosuma.prosumagpv.entity.util.TauxASDI;
import ci.prosuma.prosumagpv.entity.util.TauxTVA;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriquePromoDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxASDIDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IArticleService;

@Stateless
@Local(IArticleService.class)
public class ArticleServiceImpl implements IArticleService, Serializable {
	
	
	private static Logger logger = Logger.getLogger(ArticleServiceImpl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IArticleDAO articleDAO;
	
	@EJB
	private IHistoriquePromoDAO historiquePromoDao;

	@EJB
	private ITypeTauxTVADAO tauxTVADAO;
        
        @EJB
	private ITypeTauxASDIDAO tauxASDIDAO;

	/* EXECUTE QUERY BY PVT CODE **/

//	@Override
//	public List<Article> findAllArticleByCodeArticleKeyWord(String keyword,
//			String pvtCode) {
//		StringBuilder query = new StringBuilder();
//		query.append("SELECT e FROM Article e WHERE e.pvtCode =:pvtCode AND e.prixVenteTTCEnCours != 0 AND e.designation LIKE '").append(keyword).append("%'");
//		return executeQueryForMag(query.toString(), pvtCode);
//
//	}
        
        @Override
	public List<Article> findAllArticleByCodeArticleKeyWord(String keyword,
			String pvtCode) {
		return articleDAO.findAllArticleByCodeArticleKeyWord(keyword, pvtCode);

	}

	@Override
	public float calculateHTForPrice(String code, float price) {
		if (price == 0)
			return 0;
		if (null == tauxTVADAO.getTauxTVA(code)) {
			System.out.println("taux null");
			return price;
		}

		float taux = tauxTVADAO.getTauxTVA(code).getTaux();
		if (taux != 0) {
			float taxe = (1 + (taux / 100));
			if (taxe != 1) {
				return price / taxe;
			} else {
				return price;
			}
		} else {
			return price;
		}
	}

	@Override
	public List<Article> findAllArticleByDesignationKeyWord(String keyword,
			String pvtCode) {

		StringBuffer query = new StringBuffer();
		query.append("SELECT e FROM Article e WHERE e.pvtCode =:pvtCode AND e.prixVenteTTCEnCours != 0 AND e.designation LIKE '%"
				+ keyword + "%'");
		return executeQueryForMag(query.toString(), pvtCode);

	}

	@Override
	public Article createOrUpdateArticle(Article a) {

		return articleDAO.createOrUpdateArticle(a);

	}

	@Override
	public Article getArticle(ArticleMagRefPK entityId) {
		return articleDAO.getArticle(entityId);
	}
        
        @Override
        public Article getArticleWithStock(ArticleMagRefPK entityId){
            return articleDAO.getArticleWithStock(entityId);
        }

	@Override
	public boolean removeArticle(Article entity) {
		return articleDAO.removeArticle(entity);
	}

	@Override
	public List<Article> getAllArticleForMag(String pvtCode) {
		return articleDAO.getAllArticleForMag(pvtCode);
	}

	@Override
	public List<Article> getAllArticleBySecteurAndMag(String codeSecteur,
			String pvtCode) {
		return articleDAO.getAllArticleBySecteurAndMag(codeSecteur, pvtCode);
	}

	@Override
	public List<Article> getAllArticleByRayonAndMag(String codeRayon,
			String pvtCode) {
		return articleDAO.getAllArticleByRayonAndMag(codeRayon, pvtCode);
	}

	@Override
	public List<Article> getAllArticleByFamilleAndMag(String codeFamille,
			String pvtCode) {
		return articleDAO.getAllArticleByFamilleAndMag(codeFamille, pvtCode);
	}

	@Override
	public List<Article> getAllArticleBySousFamilleAndMag(
			String codeSousFamille, String pvtCode) {
		return articleDAO.getAllArticleBySousFamilleAndMag(codeSousFamille,
				pvtCode);
	}

	@Override
	public List<Article> getAllArticleForMagActifMagasin(String pvtCode) {
		return articleDAO.getAllArticleForMagActifMagasin(pvtCode);
	}

	@Override
	public List<Article> getAllArticleForMagCommandableCentrale(String pvtCode) {
		return articleDAO.getAllArticleForMagCommandableCentrale(pvtCode);
	}

	@Override
	public List<Article> getAllArticleByTypeForMag(String pvtCode,
			TypeArticle typeArticle) {
		return articleDAO.getAllArticleByTypeForMag(pvtCode, typeArticle);
	}

	@Override
	public List<Article> getAllArticleForCodeAnalityqueAndPvt(
			String codeAnalityque, String pvtCode) {
		return articleDAO.getAllArticleForCodeAnalityqueAndPvt(codeAnalityque,
				pvtCode);
	}

	@Override
	public List<Article> getAllArticleInGisementAndPvt(long codeGisementDebut,
			long codeGisementFin, String pvtCode) {
		return articleDAO.getAllArticleInGisementAndPvt(codeGisementDebut,
				codeGisementFin, pvtCode);
	}

	private List<Article> executeQueryForMag(String query, String pvtCode) {
		return articleDAO.executeQueryForMag(query, pvtCode);
	}

	@Override
	public List<Article> getAllArticleForPromoAndPVT(String promo,
			String pvtCode) {
		return articleDAO.getAllArticleForPromoAndPVT(promo, pvtCode);
	}

	@Override
	public List<String> getAllCodeArticleForMag(String pvtCode) {
		return articleDAO.getAllCodeArticleForMag(pvtCode);
	}

	@Override
	public String getDesignationForArticle(String pvtCode, String codeArticle) {
		return articleDAO.getDesignationForArticle(pvtCode, codeArticle);
	}

	@Override
	public List<String> getAllCodeArticleForMagUpdated(String pvtCode) {
		return articleDAO.getAllCodeArticleForMagUpdated(pvtCode);
	}

	@Override
	public Article getArticlePrincipal(Article entitySecondaire) {
		return articleDAO.getArticlePrincipal(entitySecondaire);
	}

	@Override
	public List<Article> getAllArticleSecondaire(Article entity) {
		return articleDAO.getAllArticleSecondaire(entity);
	}

	@Override
	public List<Article> getLastArticleSecondaire(Article entity, int limit) {
		return articleDAO.getLastArticleSecondaire(entity, limit);
	}

	@Override
	public List<Article> getAllArticleInGisementByRayonAndMag(String codeRayon,
			long codeGisementDebut, long codeGisementFin, String pvtCode) {
		return articleDAO.getAllArticleInGisementByRayonAndMag(codeRayon,
				codeGisementDebut, codeGisementFin, pvtCode);
	}

	@Override
	public List<Article> getAllArticleInGisementBySecteurAndMag(
			String codeSecteur, long codeGisementDebut, long codeGisementFin,
			String pvtCode) {
		return articleDAO.getAllArticleInGisementBySecteurAndMag(codeSecteur,
				codeGisementDebut, codeGisementFin, pvtCode);
	}

	@Override
	public List<Article> getAllArticleInGisementBySecRayFamSousFamAndMag(
			Date dateDebut, Date dateFin,
			String codeSecteur, String codeRayon, String codeFamille,
			String codeSousFamille, long codeGisementDebut,
			long codeGisementFin, String pvtCode) {
		return articleDAO.getAllArticleInGisementBySecRayFamSousFamAndMag(
				dateDebut, dateFin,
				codeSecteur, codeRayon, codeFamille, codeSousFamille,
				codeGisementDebut, codeGisementFin, pvtCode);
	}

	@Override
	public List<Article> getListArticleStockZero(Date dateDebut, Date dateFin,
			String codeSecteur, String codeRayon, String codeFamille,
			String codeSousFamille, long codeGisementDebut,
			long codeGisementFin, String pvtCode) {
		return articleDAO.getListArticleStockZero(dateDebut, dateFin,
				codeSecteur, codeRayon, codeFamille,
				codeSousFamille, codeGisementDebut,
				codeGisementFin, pvtCode);
	}

	@Override
	public List<String> getAllArticleForMagAndPVTAndLAP(String promo,
			String pvtCode) {
		return articleDAO.getAllArticleForMagAndPVTAndLAP(promo, pvtCode);
	}

	@Override
	public List<String> getAllArticleForLienPromoAndPVT(String libelReduit,
			String mag) {
		return articleDAO.getAllArticleForLienPromoAndPVT(libelReduit, mag);
	}

	@Override
	public List<String> findAllArticleInInventaireNonClot(String pvtCode) {
		return articleDAO.findAllArticleInInventaireNonClot(pvtCode);
	}

	@Override
	public List<Article> findAllArticleByPastPromo(String codePromo,
			String codeMag, Date dateDebut, Date dateFin) {
		
		List<Article> listArticle =  new ArrayList<>();
		List<HistoriquePromo> listHisto = historiquePromoDao.getAllHistoriquePromoForPromoAndDate(codeMag, codePromo, dateDebut, dateFin);
		if(listHisto!=null && listHisto.size()>0){
			logger.info("   listHisto.size  =  "+listHisto.size());
			System.out.println("   listHisto.size  =  "+listHisto.size());
			for(HistoriquePromo h : listHisto){
				Article a = articleDAO.getArticle(new ArticleMagRefPK(codeMag, h.getCodeArticle()));
				logger.info("   Article  =  "+a.toString());
				System.out.println("   Article  =  "+a.toString());
				a.setPrixReviensPromoTTC((int) h.getPrixReviensTTCPromo());
				a.setPrixVentePromoTTC((int) h.getPrixVenteTTCPromo());
				a.setPrixVenteTTCEnCours((int) h.getPrixVenteTTCNormal());
				a.setPrixReviensTTCEnCours((int) h.getPrixReviensTTCNormal());
				listArticle.add(a);
				
			}
			
		}
		return listArticle;
	}

	@Override
	public List<String> getAllArticleByHistorique(String promo, String pvtCode,Date debutPromo) {
		return articleDAO.getAllArticleByHistorique(promo, pvtCode, debutPromo);
	}

	@Override
	public List<String> getAllArticleByMagAndPromo(String libelReduit,
			String mag) {
		// TODO Auto-generated method stub
//		List<String> response = new ArrayList<>();
//		List<Article> queryResp = articleDAO.getAllArticleByMagAndPromo(libelReduit, mag);
//		for(Article a : queryResp){
//			response.add(a.getCodeArticle());
//		}
		logger.info("   libelReduit  :  "+libelReduit+"  #  mag : "+mag);
		return articleDAO.getAllCodeArticleByMagAndPromo(libelReduit, mag);
	}

	@Override
	public List<Article> getAllArticleByPromoAndMag(String libelReduit,
			String mag) {
		// TODO Auto-generated method stub
		return articleDAO.getAllArticleByMagAndPromo(libelReduit, mag);
	}

    @Override
    public TauxTVA getTVAByCode(String codeTVA) {
        return tauxTVADAO.getTauxTVA(codeTVA);
    }

    @Override
    public TauxASDI getASDIByCode(String codeASDI) {
        return tauxASDIDAO.getTauxASDI(codeASDI);
    }

    @Override
    public List<Article> getAllArticleBySecteurAndMagForInventaire(String codeSecteur, String pvtCode) {
        return articleDAO.getAllArticleByRayonAndMagForInventaire(codeSecteur, pvtCode);
    }

    @Override
    public List<Article> getAllArticleByRayonAndMagForInventaire(String codeRayon, String pvtCode) {
        return articleDAO.getAllArticleByRayonAndMagForInventaire(codeRayon, pvtCode);
    }

    @Override
    public List<Article> getAllArticleInGisementBySecteurAndMagForInventaire(String codeSecteur, long codeGisementDebut, long codeGisementFin, String pvtCode) {
        return articleDAO.getAllArticleInGisementBySecteurAndMagForInventaire(codeSecteur, codeGisementDebut, codeGisementFin, pvtCode);
    }

    @Override
    public List<Article> getAllArticleInGisementByRayonAndMagForInventaire(String codeRayon, long codeGisementDebut, long codeGisementFin, String pvtCode) {
        return articleDAO.getAllArticleInGisementByRayonAndMagForInventaire(codeRayon, codeGisementDebut, codeGisementFin, pvtCode);
    }

    @Override
    public List<Article> getAllArticleInGisementAndPvtForInventaire(long codeGisementDebut, long codeGisementFin, String pvtCode) {
        return articleDAO.getAllArticleInGisementAndPvtForInventaire(codeGisementDebut, codeGisementFin, pvtCode);
    }
}
