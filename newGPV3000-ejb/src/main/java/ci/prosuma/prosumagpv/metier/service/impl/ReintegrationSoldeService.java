package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IPointDeVenteService;
import ci.prosuma.prosumagpv.metier.service.IReintegrationSoldeService;

@Stateless
@Local(IReintegrationSoldeService.class)
public class ReintegrationSoldeService implements IReintegrationSoldeService,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435190760022086127L;

	@EJB
	private IEnteteMouvementDAO enteteMouvementDAO;

	@EJB
	private IPointDeVenteService pointDeVenteService;

	@EJB
	private ITypeOrigineMouvementDAO origineMouvementDAO;

	@EJB
	private ITypeMouvementStockDAO typeMouvementStockDAO;

	@EJB
	private ITypeTauxTVADAO tauxTVADAO;
        
        @EJB
        private IArticleDAO articleDao;

	@Override
	public void process(Article selectedArt, Article artSecond, float qteStock) {
            Article selectedArticle = articleDao.getArticleWithStock(new ArticleMagRefPK(selectedArt.getPvtCode(),selectedArt.getCodeArticle()));
            Article artSecondaire = articleDao.getArticleWithStock(new ArticleMagRefPK(artSecond.getPvtCode(),artSecond.getCodeArticle()));
		// CREATION selectedArt
		// MOUVEMENT REINTEGRATION SUR ARTICLE PRINCIPAL
		String user = "GPV3000";
		PointDeVente pvt = pointDeVenteService.getPVT(selectedArticle.getPvtCode());
		OrigineMouvement origine = origineMouvementDAO.getOrigineMouvement("DLR");
		if (origine == null) {
			origine = origineMouvementDAO.getOrigineMouvement("DLR");
		}

		EnteteMouvement em = new EnteteMouvement();
		em.setDateMouvement(new Date(System.currentTimeMillis()));
		em.setObservations("MOUVEMENT DE DLR+ SUR ARTICLE PRINCIPAL : "
				+ selectedArticle.getCodeArticle());
		em.setOrigineMouvement(origine);
		em.setPvt(pvt);
		em.setUserCreation(user);
		DetailMouvement dm = new DetailMouvement();
		dm.setArticle(selectedArticle);

		dm.setArtPrixReviens(selectedArticle.getPrixReviensTTCEnCours());
		dm.setArtPrixReviensHT((long) calculateHTForPrice(
				selectedArticle.getTauxTVA(),
				selectedArticle.getPrixReviensTTCEnCours()));
		dm.setArtPrixVente(selectedArticle.getPrixVenteTTCEnCours());
		dm.setArtPrixVenteHT((long) calculateHTForPrice(
				selectedArticle.getTauxTVA(),
				selectedArticle.getPrixVenteTTCEnCours()));

		dm.setCodeAnal(selectedArticle.getCodeAnalytique());
		dm.setDateMouvement(em.getDateMouvement());
		dm.setDepot(selectedArticle.getStock().getDepot());
		dm.setDesignationArt(selectedArticle.getDesignation());
		dm.setFamille(selectedArticle.getFamille());
		dm.setObservations("DLR + SUR ARTICLE "
				+ selectedArticle.getCodeArticle() + " QTE " + qteStock);
		dm.setQteMvt(qteStock);
		dm.setQtePhysiqueAvantMouvement(selectedArticle.getStock()
				.getQteComptable());
		dm.setRayon(selectedArticle.getRayon());
		dm.setSecteur(selectedArticle.getSecteur());
		dm.setSens(1);
                dm.setEnteteMouvement(em);
		dm.setSousFamille(selectedArticle.getSousFamille());
		dm.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock("DLR+"));

		List<DetailMouvement> listDm = new ArrayList<DetailMouvement>();
		listDm.add(dm);
		em.setMouvements(listDm);

		em = enteteMouvementDAO.createOrUpdateEnteteMouvementWithoutMAJStock(em);

		// MOUVEMENT REINTEGRATION SUR ARTICLESECONDAIRE
		EnteteMouvement em1 = new EnteteMouvement();
		em1.setDateMouvement(new Date(System.currentTimeMillis()));
		em1.setObservations("MOUVEMENT DE DLR- SUR ARTICLE SECONDAIRE : "
				+ artSecondaire.getCodeArticle());
		em1.setOrigineMouvement(origine);
		em1.setPvt(pvt);
		em1.setUserCreation(user);
		DetailMouvement dm2 = new DetailMouvement();
		dm2.setArticle(artSecondaire);
                dm2.setEnteteMouvement(em1);
		dm2.setArtPrixReviens(artSecondaire.getPrixReviensTTCEnCours());
		dm2.setArtPrixReviensHT((long) calculateHTForPrice(
				artSecondaire.getTauxTVA(),
				artSecondaire.getPrixReviensTTCEnCours()));
		dm2.setArtPrixVente(artSecondaire.getPrixVenteTTCEnCours());
		dm2.setArtPrixVenteHT((long) calculateHTForPrice(
				artSecondaire.getTauxTVA(),
				artSecondaire.getPrixVenteTTCEnCours()));

		dm2.setCodeAnal(artSecondaire.getCodeAnalytique());
		dm2.setDateMouvement(em1.getDateMouvement());
		dm2.setDepot(artSecondaire.getStock().getDepot());
		dm2.setDesignationArt(artSecondaire.getDesignation());
		dm2.setFamille(artSecondaire.getFamille());
		dm2.setObservations("DLR - SUR ARTICLE "
				+ artSecondaire.getCodeArticle() + " QTE " + qteStock);
		dm2.setQteMvt(qteStock);
		dm2.setQtePhysiqueAvantMouvement(artSecondaire.getStock()
				.getQteComptable());
		dm2.setRayon(artSecondaire.getRayon());
		dm2.setSecteur(artSecondaire.getSecteur());
		dm2.setSens(-1);
		dm2.setSousFamille(artSecondaire.getSousFamille());
		dm2.setTypeMouvement(typeMouvementStockDAO
				.getTypeMouvementStock("DLR-"));

		List<DetailMouvement> listDm2 = new ArrayList<DetailMouvement>();
		listDm2.add(dm2);
		em1.setMouvements(listDm2);

		em1 = enteteMouvementDAO
				//.createOrUpdateEnteteMouvement(em1);
				.createOrUpdateEnteteMouvementWithoutMAJStock(em1);
	}

	private float calculateHTForPrice(String code, float price) {
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

}
