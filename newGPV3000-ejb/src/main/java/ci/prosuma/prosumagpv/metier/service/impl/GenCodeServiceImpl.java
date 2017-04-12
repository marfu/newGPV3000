package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeGenCode;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineGenCode;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IGenCodeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IGenCodeService;
import ci.prosuma.prosumagpv.metier.service.IStockArticleService;
import ci.prosuma.prosumagpv.metier.util.app.Ean13Generator;

@Stateless
@Local(IGenCodeService.class)
public class GenCodeServiceImpl implements IGenCodeService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IArticleDAO articleDAO;

	@EJB
	private ITypeTauxTVADAO tauxTVADAO;

	@EJB
	private IGenCodeDAO genCodeDAO;

	@EJB
	private IPromoDAO promoDAO;

	@EJB
	private IJDBCConnectionMySQL mySql;

	@EJB
	private ITypeOrigineMouvementDAO origineMouvementDAO;

	@EJB
	private ITypeMouvementStockDAO typeMouvementStockDAO;

	@EJB
	private IEnteteMouvementDAO enteteMouvementDAO;

	@EJB
	private IStockArticleService stockArticleService;

	/** EXECUTE QUERY BY PVT CODE **/

	@Override
	public GenCode createOrUpdateGenCode(GenCode entity) {
		return genCodeDAO.createOrUpdateGenCode(entity);
	}

	@Override
	public GenCode getGenCode(long id) {
		return genCodeDAO.getGenCode(id);
	}

	@Override
	public boolean removeGenCode(GenCode entity) {
		return genCodeDAO.removeGenCode(entity);
	}

	@Override
	public GenCode getGenCodeByCodeAndPVT(String gcCode, String pvt) {
		return genCodeDAO.getGenCodeByCodeAndPVT(gcCode, pvt);
	}

	@Override
	public Article getArticleForGenCodeAndPvt(String gc, String pvt) {
		return genCodeDAO.getArticleForGenCodeAndPvt(gc, pvt);
	}

	@Override
	public GenCode createGenCodeSecondaire(Article a, long genCode,
			int caractereControle, String user) {
		GenCode selectedGenCode = new GenCode();
		selectedGenCode.setActif(true);
		selectedGenCode.setCatGen(CategorieGenCode.PRINCIPAL);
		selectedGenCode.setCoefSurArticleInPourcent(1f);
		selectedGenCode.setDateCreation(new Date(System.currentTimeMillis()));
		selectedGenCode.setModeGenCode(ModeGenCode.PRINCIPAL);
		selectedGenCode.setNouveauCB(true);
		selectedGenCode.setOrigineGenCode(OrigineGenCode.MAGASIN);
		selectedGenCode.setUserCreation(user);
		if (null != a.getPromo()) {
			Promo p = promoDAO.getPromo(a.getPromo());
			System.out.println("p.isEnFacturation");
			if (p.isEnFacturation()) {
				selectedGenCode.setPrixReviensTTCEnCours(a
						.getPrixReviensPromoTTC());
			} else {
				selectedGenCode.setPrixReviensTTCEnCours(a
						.getPrixReviensTTCEnCours());
			}
			if (p.isEnVente()) {
				selectedGenCode
						.setPrixVenteTTCEnCours(a.getPrixVentePromoTTC());
			} else {
				selectedGenCode.setPrixVenteTTCEnCours(a
						.getPrixVenteTTCEnCours());
			}
		} else {
			selectedGenCode.setPrixReviensTTCEnCours(a
					.getPrixReviensTTCEnCours());
			selectedGenCode.setPrixVenteTTCEnCours(a.getPrixVenteTTCEnCours());
		}

		DecimalFormat dm = new DecimalFormat("000000000000");
		selectedGenCode.setCode(dm.format(genCode));
		selectedGenCode.setCaractereControle(caractereControle + "");
		selectedGenCode = createOrUpdateGenCode(selectedGenCode);
		selectedGenCode.setArticle(a);
		try{
			selectedGenCode = createOrUpdateGenCode(selectedGenCode);
		}catch(Exception ex){
			return null;
		}
		
		return selectedGenCode;
	}

	@Override
	public GenCode createGenCodeDLV(Article selectedArt, int price,
			float qteStock, String user, PointDeVente pvt) {
		// CREATION DU PLU
                Article selectedArticle = articleDAO.getArticleWithStock(new ArticleMagRefPK(selectedArt.getPvtCode(),selectedArt.getCodeArticle()));
		GenCode selectedGenCode = new GenCode();
		selectedGenCode.setUserCreation(user);
		System.out.println("QTE STOCK " + qteStock);

		long seq = Long.parseLong(mySql.getSqlRequestByLibelle(pvt.getPvtCode()
				+ ".sequence.dlv"));
		String codeGenerer = "5000" + new DecimalFormat("00000000").format(seq);
		String codeArticleGenere = "5000"
				+ new DecimalFormat("00000").format(seq);
		GenCode gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		while (gc != null) {
			seq = seq + 1;
			codeGenerer = "5000" + new DecimalFormat("00000000").format(seq);
			codeArticleGenere = "5000" + new DecimalFormat("00000").format(seq);
			gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		}

		// CREATION DE ARTICLE SECONDAIRE

		Article temp = new Article();
		temp.setActif(true);
		temp.setCodeAnalytique(selectedArticle.getCodeAnalytique());
		temp.setCodeArticle(codeArticleGenere);
		temp.setCodeGisement(selectedArticle.getCodeGisement());
		temp.setColisage(selectedArticle.getColisage());
		temp.setCommandableCentrale(false);
		temp.setDateCreation(new Date(System.currentTimeMillis()));
		temp.setDesignation(selectedArticle.getDesignation());
		temp.setFamille(selectedArticle.getFamille());
		temp.setLibelReduit(selectedArticle.getLibelReduit());
		temp.setLivraisonCentrale(false);
		temp.setLivraisonDirecte(false);
		temp.setLivraisonEclatement(false);
		temp.setMotDirecteur(selectedArticle.getMotDirecteur());
		temp.setPasDeRupture(false);
		temp.setPrixVert(false);
		temp.setPromo(null);
		temp.setPvtCode(selectedArticle.getPvtCode());
		temp.setRayon(selectedArticle.getRayon());
		temp.setSecteur(selectedArticle.getSecteur());
		temp.setSousFamille(selectedArticle.getSousFamille());
		StockArticle sa = new StockArticle();
		sa.setDepot( selectedArticle.getStock().getDepot());
		sa.setQteComptable(0);
		sa.setDateDerniereEntrer(new Date(System.currentTimeMillis()));
		sa = stockArticleService.createOrUpdateStockArticle(sa);

		temp.setStock(sa);
		temp.setTauxASDI(selectedArticle.getTauxASDI());
		temp.setTauxTVA(selectedArticle.getTauxTVA());
		temp.setTypeArticle(TypeArticle.SECONDAIRE);
		temp.setArticlePrincipal(selectedArticle.getCodeArticle());
		temp.setPrixVenteTTCEnCours(price);

		if (null != selectedArticle.getPromo()) {
			Promo p = promoDAO.getPromo(selectedArticle.getPromo());
			if (p.isEnFacturation()) {
				temp.setPrixReviensTTCEnCours(selectedArticle
						.getPrixReviensPromoTTC());
				selectedGenCode.setPrixReviensTTCEnCours(selectedArticle
						.getPrixReviensPromoTTC());
			} else {
				temp.setPrixReviensTTCEnCours(selectedArticle
						.getPrixReviensTTCEnCours());
				selectedGenCode.setPrixReviensTTCEnCours(selectedArticle
						.getPrixReviensTTCEnCours());
			}
			if (p.isEnVente()) {
				temp.setPrixVenteTTCPrecedent(selectedArticle
						.getPrixVentePromoTTC());

			} else {
				temp.setPrixVenteTTCPrecedent(selectedArticle
						.getPrixVenteTTCEnCours());
			}

		} else {
			selectedGenCode.setPrixReviensTTCEnCours(selectedArticle
					.getPrixReviensTTCEnCours());
			temp.setPrixReviensTTCEnCours(selectedArticle
					.getPrixReviensTTCEnCours());
			temp.setPrixVenteTTCPrecedent(selectedArticle
					.getPrixVenteTTCEnCours());
		}

		temp.setPrixVenteTTCEnCours(price);

		temp = articleDAO.createOrUpdateArticle(temp);

		selectedGenCode.setCoefSurArticleInPourcent(temp
				.getPrixVenteTTCEnCours() / temp.getPrixVenteTTCPrecedent());
		selectedGenCode.setCode(codeGenerer);
		selectedGenCode.setCaractereControle(Ean13Generator
				.generateEAN13(selectedGenCode.getCode()));
		selectedGenCode.setActif(true);
		selectedGenCode.setCatGen(CategorieGenCode.DLVM);
		selectedGenCode.setDateCreation(new Date(System.currentTimeMillis()));
		selectedGenCode.setModeGenCode(ModeGenCode.DLVM);
		selectedGenCode.setNouveauCB(true);
		selectedGenCode.setOrigineGenCode(OrigineGenCode.MAGASIN);
		selectedGenCode.setPrixVenteTTCEnCours(price);
		selectedGenCode.setArticle(temp);
		selectedGenCode = createOrUpdateGenCode(selectedGenCode);

		seq = seq + 1;
		mySql.executeUpdateSequence(pvt.getPvtCode() + ".sequence.dlv",
				(int) seq);

		// CREATION MOUVEMENT
		// MOUVEMENT SORTIE SUR ARTICLE PRINCIPAL

		EnteteMouvement em = new EnteteMouvement();
		em.setDateMouvement(new Date(System.currentTimeMillis()));
		em.setObservations("MOUVEMENT DE DLV- SUR ARTICLE PRINCIPAL : "
				+ selectedArticle.getCodeArticle());
		em.setOrigineMouvement(origineMouvementDAO.getOrigineMouvement("DLV"));
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
                sa.setDepot( selectedArticle.getStock().getDepot());
		dm.setDesignationArt(selectedArticle.getDesignation());
		dm.setFamille(selectedArticle.getFamille());
		dm.setObservations("DLV - SUR ARTICLE "+ selectedArticle.getCodeArticle() + " QTE " + qteStock);
		dm.setQteMvt(qteStock);
		dm.setQtePhysiqueAvantMouvement(selectedArticle.getStock().getQteComptable());
		dm.setRayon(selectedArticle.getRayon());
		dm.setSecteur(selectedArticle.getSecteur());
		dm.setSens(-1);
                dm.setEnteteMouvement(em);
		dm.setSousFamille(selectedArticle.getSousFamille());
		dm.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock("DLV-"));

		List<DetailMouvement> listDm = new ArrayList<DetailMouvement>();
		listDm.add(dm);
		em.setMouvements(listDm);

		em = enteteMouvementDAO.createOrUpdateEnteteMouvement(em);

		// MOUVEMENT SORTIE SUR ARTICLESECONDAIRE

		System.out.println("MOUVEMENY 2222 0" + temp + " - " + qteStock);
		EnteteMouvement em1 = new EnteteMouvement();
		em1.setDateMouvement(new Date(System.currentTimeMillis()));
		em1.setObservations("MOUVEMENT DE DLV+ SUR ARTICLE SECONDAIRE : "
				+ temp.getCodeArticle());
		em1.setOrigineMouvement(origineMouvementDAO.getOrigineMouvement("DLV"));
		em1.setPvt(pvt);
		em1.setUserCreation(user);
		DetailMouvement dm2 = new DetailMouvement();
		dm2.setArticle(temp);
		dm2.setArtPrixReviens(temp.getPrixReviensTTCEnCours());
		dm2.setArtPrixReviensHT((long) calculateHTForPrice(temp.getTauxTVA(),
				temp.getPrixReviensTTCEnCours()));
		dm2.setArtPrixVente(temp.getPrixVenteTTCEnCours());
		dm2.setArtPrixVenteHT((long) calculateHTForPrice(temp.getTauxTVA(),
				temp.getPrixVenteTTCEnCours()));

		dm2.setCodeAnal(temp.getCodeAnalytique());
		dm2.setDateMouvement(em1.getDateMouvement());
		dm2.setDepot(temp.getStock().getDepot());
		dm2.setDesignationArt(temp.getDesignation());
		dm2.setFamille(temp.getFamille());
		dm2.setObservations("DLV + SUR ARTICLE " + temp.getCodeArticle()
				+ " QTE " + qteStock);
		dm2.setQteMvt(qteStock);
		dm2.setQtePhysiqueAvantMouvement(temp.getStock().getQteComptable());
		dm2.setRayon(temp.getRayon());
		dm2.setSecteur(temp.getSecteur());
		dm2.setSens(1);
                dm2.setEnteteMouvement(em1);;
		dm2.setSousFamille(temp.getSousFamille());
		dm2.setTypeMouvement(typeMouvementStockDAO.getTypeMouvementStock("DLV+"));

		List<DetailMouvement> listDm2 = new ArrayList<DetailMouvement>();
		listDm2.add(dm2);
		em1.setMouvements(listDm2);
		em1 = enteteMouvementDAO.createOrUpdateEnteteMouvement(em1);
		return selectedGenCode;
	}

	@Override
	public GenCode createGenCodeSurcond(Article selectedArticle, int price,
			int prixSuggerer, int colissage, String user, PointDeVente pvt) {

		GenCode selectedGenCode = new GenCode();
		selectedGenCode.setActif(true);
		selectedGenCode.setCatGen(CategorieGenCode.RCOND);
		selectedGenCode.setDateCreation(new Date(System.currentTimeMillis()));
		selectedGenCode.setModeGenCode(ModeGenCode.RCOND);
		selectedGenCode.setNouveauCB(true);
		selectedGenCode.setOrigineGenCode(OrigineGenCode.MAGASIN);
		selectedGenCode.setColisage(colissage);

		selectedGenCode.setPrixVenteTTCEnCours(price);
		selectedGenCode.setPrixSuggerer(prixSuggerer);

		if (null != selectedArticle.getPromo()) {
			Promo p = promoDAO.getPromo(selectedArticle.getPromo());
			if (p.isEnFacturation()) {
				selectedGenCode.setPrixReviensTTCEnCours((selectedArticle
						.getPrixReviensPromoTTC() * colissage));
			} else {
				selectedGenCode.setPrixReviensTTCEnCours((selectedArticle
						.getPrixReviensTTCEnCours() * colissage));
			}
			if (p.isEnVente()) {
				selectedGenCode.setCoefSurArticleInPourcent(selectedGenCode
						.getPrixVenteTTCEnCours()
						/ selectedArticle.getPrixVentePromoTTC());

			} else {
				selectedGenCode.setCoefSurArticleInPourcent(selectedGenCode
						.getPrixVenteTTCEnCours()
						/ selectedArticle.getPrixVenteTTCEnCours());
			}

		} else {
			selectedGenCode.setPrixReviensTTCEnCours((selectedArticle
					.getPrixReviensTTCEnCours() * colissage));
			selectedGenCode.setCoefSurArticleInPourcent(Float
					.valueOf(selectedGenCode.getPrixVenteTTCEnCours())
					/ Float.valueOf(selectedArticle.getPrixVenteTTCEnCours()));
		}

		long seq = Long.parseLong(mySql.getSqlRequestByLibelle(pvt.getPvtCode()
				+ ".sequence.surcond"));
		String codeGenerer = "6000" + new DecimalFormat("00000000").format(seq);

		GenCode gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		while (gc != null) {
			seq = seq + 1;
			codeGenerer = "6000" + new DecimalFormat("00000000").format(seq);
			gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		}

		selectedGenCode.setCode(codeGenerer);
		selectedGenCode.setCaractereControle(Ean13Generator
				.generateEAN13(selectedGenCode.getCode()));
		selectedGenCode.setArticle(selectedArticle);
		selectedGenCode = createOrUpdateGenCode(selectedGenCode);

		mySql.executeUpdateSequence(pvt.getPvtCode() + ".sequence.surcond",
				(int) seq);

		return selectedGenCode;
	}

	@Override
	public GenCode createGenCodeSousCond(Article selectedArticle, int price,
			int prixSuggerer, int colissage, int proportion, String user,
			PointDeVente pvt) {
		GenCode selectedGenCode = new GenCode();
		selectedGenCode.setActif(true);
		selectedGenCode.setCatGen(CategorieGenCode.SCOND);
		selectedGenCode.setDateCreation(new Date(System.currentTimeMillis()));
		selectedGenCode.setModeGenCode(ModeGenCode.SCOND);
		selectedGenCode.setNouveauCB(true);
		selectedGenCode.setOrigineGenCode(OrigineGenCode.MAGASIN);
		selectedGenCode.setColisage(Integer.parseInt(selectedArticle.getColisage()));

		selectedGenCode.setPrixVenteTTCEnCours(price);
		selectedGenCode.setPrixSuggerer(prixSuggerer);

		if (null != selectedArticle.getPromo()) {
			Promo p = promoDAO.getPromo(selectedArticle.getPromo());
			if (p.isEnFacturation()) {
				selectedGenCode.setPrixReviensTTCEnCours(Math.round(Float
						.valueOf(selectedArticle.getPrixReviensPromoTTC())
						/ Float.valueOf(proportion))
						* colissage);
			} else {
				selectedGenCode.setPrixReviensTTCEnCours(Math.round(Float
						.valueOf(selectedArticle.getPrixReviensTTCEnCours())
						/ Float.valueOf(proportion))
						* colissage);
			}
			if (p.isEnVente()) {
				selectedGenCode.setCoefSurArticleInPourcent(selectedGenCode
						.getPrixVenteTTCEnCours()
						/ selectedArticle.getPrixVentePromoTTC());
			} else {
				selectedGenCode.setCoefSurArticleInPourcent(selectedGenCode
						.getPrixVenteTTCEnCours()
						/ selectedArticle.getPrixVenteTTCEnCours());
			}

		} else {
			selectedGenCode
					.setPrixReviensTTCEnCours(Math.round(Float
							.valueOf(selectedArticle.getPrixReviensTTCEnCours())
							/ Float.valueOf(proportion))
							* colissage);
			selectedGenCode.setCoefSurArticleInPourcent(Float
					.valueOf(selectedGenCode.getPrixVenteTTCEnCours())
					/ Float.valueOf(selectedArticle.getPrixVenteTTCEnCours()));
		}

		long seq = Long.parseLong(mySql.getSqlRequestByLibelle(pvt.getPvtCode()
				+ ".sequence.souscond"));
		String codeGenerer = "7000" + new DecimalFormat("00000000").format(seq);

		GenCode gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		while (gc != null) {
			seq = seq + 1;
			codeGenerer = "7000" + new DecimalFormat("00000000").format(seq);
			gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		}

		selectedGenCode.setCode(codeGenerer);
		selectedGenCode.setCaractereControle(Ean13Generator
				.generateEAN13(selectedGenCode.getCode()));
		selectedGenCode.setArticle(selectedArticle);
		selectedGenCode = createOrUpdateGenCode(selectedGenCode);

		mySql.executeUpdateSequence(pvt.getPvtCode() + ".sequence.souscond",
				(int) seq);

		return selectedGenCode;
	}

	@Override
	public GenCode createGenCodeGros(Article selectedArticle, int price,
			int prixSuggerer, int colissage, String user, PointDeVente pvt) {
		GenCode selectedGenCode = new GenCode();
		selectedGenCode.setActif(true);
		selectedGenCode.setCatGen(CategorieGenCode.GROS);
		selectedGenCode.setDateCreation(new Date(System.currentTimeMillis()));
		selectedGenCode.setModeGenCode(ModeGenCode.GROS);
		selectedGenCode.setNouveauCB(true);
		selectedGenCode.setOrigineGenCode(OrigineGenCode.MAGASIN);
		selectedGenCode.setColisage(colissage);

		selectedGenCode.setPrixVenteTTCEnCours(price);
		selectedGenCode.setPrixSuggerer(prixSuggerer);

		if (null != selectedArticle.getPromo()) {
			Promo p = promoDAO.getPromo(selectedArticle.getPromo());
			if (p.isEnFacturation()) {
				selectedGenCode.setPrixReviensTTCEnCours((selectedArticle
						.getPrixReviensPromoTTC() * colissage));
			} else {
				selectedGenCode.setPrixReviensTTCEnCours((selectedArticle
						.getPrixReviensTTCEnCours() * colissage));
			}
			if (p.isEnVente()) {
				selectedGenCode.setCoefSurArticleInPourcent(selectedGenCode
						.getPrixVenteTTCEnCours()
						/ selectedArticle.getPrixVentePromoTTC());
			} else {
				selectedGenCode.setCoefSurArticleInPourcent(selectedGenCode
						.getPrixVenteTTCEnCours()
						/ selectedArticle.getPrixVenteTTCEnCours());
			}

		} else {
			selectedGenCode.setPrixReviensTTCEnCours((selectedArticle
					.getPrixReviensTTCEnCours() * colissage));
			selectedGenCode.setCoefSurArticleInPourcent(Float
					.valueOf(selectedGenCode.getPrixVenteTTCEnCours())
					/ Float.valueOf(selectedArticle.getPrixVenteTTCEnCours()));
		}

		long seq = Long.parseLong(mySql.getSqlRequestByLibelle(pvt.getPvtCode()
				+ ".sequence.gros"));
		String codeGenerer = "2004" + new DecimalFormat("00000000").format(seq);

		GenCode gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		while (gc != null) {
			seq = seq + 1;
			codeGenerer = "2004" + new DecimalFormat("00000000").format(seq);
			gc = getGenCodeByCodeAndPVT(codeGenerer, pvt.getPvtCode());
		}

		selectedGenCode.setCode(codeGenerer);
		selectedGenCode.setCaractereControle(Ean13Generator
				.generateEAN13(selectedGenCode.getCode()));
		selectedGenCode.setArticle(selectedArticle);
		selectedGenCode = createOrUpdateGenCode(selectedGenCode);

		mySql.executeUpdateSequence(pvt.getPvtCode() + ".sequence.gros",
				(int) seq);

		return selectedGenCode;
	}

	@Override
	public List<GenCode> getAllGenCodeForArticle(String codeArticle,String pvtCode) {
		List<GenCode> allGencode = genCodeDAO.getAllGenCodeForArticle(codeArticle, pvtCode);
		List<GenCode> finalGencode ;
		HashMap<String, GenCode> map = new HashMap<>();
		if(allGencode!=null){
                    allGencode.stream().forEach((code) -> {
                        map.put(code.getCode(), code);
                    });
		}
		if(map.size()>0){
			finalGencode = new ArrayList<>(map.values());
		}else{
			finalGencode= new ArrayList<>();
		}
		
		return finalGencode;
	}

	@Override
	public GenCode getGenCodeForArtAndGenCode(String genCode,
			String codeArticle, String pvtCode) {
		return genCodeDAO.getGenCodeForArtAndGenCode(genCode, codeArticle,
				pvtCode);
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

	@Override
	public List<GenCode> getAllGenCodeForArticleInPromo(String pvt, String promo) {
		return genCodeDAO.getAllGenCodeForArticleInPromo(pvt, promo);
	}

	@Override
	public List<GenCode> getAllGenCodeByType(CategorieGenCode cat,
			String pvtCode) {
		return genCodeDAO.getAllGenCodeByType(cat, pvtCode);
	}

	@Override
	public List<GenCode> getAllGenCodeInGisement(String pvt,
			long gisementDebut, long gisementFin) {
		return genCodeDAO.getAllGenCodeInGisement(pvt, gisementDebut,
				gisementFin);
	}

	@Override
	public List<GenCode> getAllGenCodeInSecteur(String pvt, String sec) {
		return genCodeDAO.getAllGenCodeInSecteur(pvt, sec);
	}

	@Override
	public List<GenCode> getAllGenCodeInRayon(String pvt, String ray) {
		return genCodeDAO.getAllGenCodeInRayon(pvt, ray);
	}

	@Override
	public List<GenCode> getAllGenCodeInFamille(String pvt, String famille) {
		return genCodeDAO.getAllGenCodeInFamille(pvt, famille);
	}

	@Override
	public List<GenCode> getAllGenCodeInSousFamille(String pvt,
			String souFamille) {
		return genCodeDAO.getAllGenCodeInSousFamille(pvt, souFamille);
	}

	@Override
	public GenCode getFirstGenCodeForArticle(String codeArticle, String pvtCode) {
		return genCodeDAO.getFirstGenCodeForArticle(codeArticle, pvtCode);
	}
	
	@Override
	public boolean logImpression(List<GenCode> listGenCode, String typeEtiquette, String pvtCode, String user, String typeLog) {

		BufferedWriter monFluxSortie;
		String logDate = (new SimpleDateFormat("yyyyMMdd-HHmmss")).format(new Date());
		String cheminLog = mySql.getSqlRequestByLibelle(pvtCode+ ".chemin.log");
		System.out.println("Début creation log");
		String type = "";
		monFluxSortie = null;

		try {
			monFluxSortie = new BufferedWriter(new FileWriter(cheminLog + "/" + typeLog + "Log-" + user + "-" + pvtCode + "-" + logDate + ".txt"));
			
			if(typeEtiquette.equals("1"))
				type = "ETIQ. PRODUIT";
			else if (typeEtiquette.equals("2")) 
				type = "ETIQ. PRODUIT / PRIX";
			else
				type = "ETIQ. GONDOLE";
			
			monFluxSortie.write(type);
			monFluxSortie.newLine();
			
			for (GenCode genCode : listGenCode) {
				monFluxSortie.write(genCode.myToString());
				monFluxSortie.newLine();		
			}
			monFluxSortie.close();
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
