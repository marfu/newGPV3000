package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.util.CategorieClient;
import ci.prosuma.prosumagpv.entity.util.CodeAnalytique;
import ci.prosuma.prosumagpv.entity.util.Famille;
import ci.prosuma.prosumagpv.entity.util.Festif;
import ci.prosuma.prosumagpv.entity.util.ModeReglement;
import ci.prosuma.prosumagpv.entity.util.OrigineMouvement;
import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosuma.prosumagpv.entity.util.SousFamille;
import ci.prosuma.prosumagpv.entity.util.TauxASDI;
import ci.prosuma.prosumagpv.entity.util.TauxTVA;
import ci.prosuma.prosumagpv.entity.util.TypeDepot;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCategorieClientDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeCodeAnalytiqueDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeDepotDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFestifDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeModeReglementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeRayonDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSecteurDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSousFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxASDIDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeTauxTVADAO;
import ci.prosuma.prosumagpv.metier.service.IClassificationService;

@Stateless
@Local(IClassificationService.class)
public class ClassificationServiceImpl implements IClassificationService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ITypeCategorieClientDAO categorieClientDAO;

	@EJB
	private ITypeCodeAnalytiqueDAO codeAnalytiqueDAO;

	@EJB
	private ITypeDepotDAO typeDepotDAO;

	@EJB
	private ITypeFamilleDAO familleDAO;

	@EJB
	private ITypeModeReglementDAO modeReglementDAO;

	@EJB
	private ITypeMouvementStockDAO mouvementsStockDAO;

	@EJB
	private ITypeOrigineMouvementDAO origineMouvementDAO;

	@EJB
	private ITypeRayonDAO rayonDAO;

	@EJB
	private ITypeSecteurDAO secteurDAO;

	@EJB
	private ITypeSousFamilleDAO sousFamilleDAO;

	@EJB
	private ITypeTauxTVADAO tauxTVADAO;

	@EJB
	private ITypeTauxASDIDAO tauxASDIDAO;

	@EJB
	private ITypeFestifDAO festifDAO;

	
	@Override
	public Secteur createOrUpdateSecteur(Secteur ei) {
		return secteurDAO.createOrUpdateSecteur(ei);
	}

	@Override
	public Secteur getSecteur(String code) {
		return secteurDAO.getSecteur(code);
	}

	@Override
	public boolean removeSecteur(Secteur ei) {
		return secteurDAO.removeSecteur(ei);
	}

	@Override
	public List<Secteur> getAllSecteur() {
		return secteurDAO.getAllSecteur();
	}

	@Override
	public Rayon createOrUpdateRayon(Rayon ei) {
		return rayonDAO.createOrUpdateRayon(ei);
	}

	@Override
	public Rayon getRayon(String code) {
		return rayonDAO.getRayon(code);
	}

	@Override
	public boolean removeRayon(Rayon ei) {
		return rayonDAO.removeRayon(ei);

	}

	@Override
	public List<Rayon> getAllRayon() {
		return rayonDAO.getAllRayon();
	}

	@Override
	public Famille createOrUpdateFamille(Famille ei) {
		return familleDAO.createOrUpdateFamille(ei);
	}

	@Override
	public Famille getFamille(String code) {
		return familleDAO.getFamille(code);
	}

	@Override
	public boolean removeFamille(Famille ei) {
		return familleDAO.removeFamille(ei);
	}

	@Override
	public List<Famille> getAllFamille() {
		return familleDAO.getAllFamille();
	}

	@Override
	public SousFamille createOrUpdateSousFamille(SousFamille ei) {
		return sousFamilleDAO.createOrUpdateSousFamille(ei);
	}

	@Override
	public SousFamille getSousFamille(String code) {
		return sousFamilleDAO.getSousFamille(code);
	}

	@Override
	public boolean removeSousFamille(SousFamille ei) {
		return sousFamilleDAO.removeSousFamille(ei);
	}

	@Override
	public List<SousFamille> getAllSousFamille() {
		return sousFamilleDAO.getAllSousFamille();
	}

	@Override
	public CategorieClient createOrUpdateCategorieClient(CategorieClient ei) {
		return categorieClientDAO.createOrUpdateCategorieClient(ei);
	}

	@Override
	public CategorieClient getCategorieClient(String categorie) {
		return categorieClientDAO.getCategorieClient(categorie);
	}

	@Override
	public boolean removeCategorieClient(CategorieClient ei) {
		return categorieClientDAO.removeCategorieClient(ei);
	}

	@Override
	public List<CategorieClient> getAllCategorieClient() {
		return categorieClientDAO.getAllCategorieClient();
	}

	@Override
	public CodeAnalytique createOrUpdateCodeAnalytique(CodeAnalytique ei) {
		return codeAnalytiqueDAO.createOrUpdateCodeAnalytique(ei);
	}

	@Override
	public CodeAnalytique getCodeAnalytique(String code) {
		return codeAnalytiqueDAO.getCodeAnalytique(code);
	}

	@Override
	public boolean removeCodeAnalytique(CodeAnalytique ei) {
		return codeAnalytiqueDAO.removeCodeAnalytique(ei);
	}

	@Override
	public List<CodeAnalytique> getAllCodeAnalytique() {
		return codeAnalytiqueDAO.getAllCodeAnalytique();
	}

	@Override
	public TypeDepot createOrUpdateTypeDepot(TypeDepot ei) {
		return typeDepotDAO.createOrUpdateTypeDepot(ei);
	}

	@Override
	public TypeDepot getTypeDepot(String code) {
		return typeDepotDAO.getTypeDepot(code);
	}

	@Override
	public boolean removeTypeDepot(TypeDepot ei) {
		return typeDepotDAO.removeTypeDepot(ei);
	}

	@Override
	public List<TypeDepot> getAllTypeDepot() {
		return typeDepotDAO.getAllTypeDepot();
	}

	@Override
	public ModeReglement createOrUpdateModeReglement(ModeReglement ei) {
		return modeReglementDAO.createOrUpdateModeReglement(ei);
	}

	@Override
	public ModeReglement getModeReglement(String code) {
		return modeReglementDAO.getModeReglement(code);
	}

	@Override
	public boolean removeModeReglement(ModeReglement ei) {
		return modeReglementDAO.removeModeReglement(ei);
	}

	@Override
	public List<ModeReglement> getAllModeReglement() {
		return modeReglementDAO.getAllModeReglement();
	}

	@Override
	public TypeMouvementStock createOrUpdateTypeMouvementStock(TypeMouvementStock ei) {
		return mouvementsStockDAO.createOrUpdateTypeMouvementStock(ei);
	}

	@Override
	public TypeMouvementStock getTypeMouvementStock(String code) {
		return mouvementsStockDAO.getTypeMouvementStock(code);
	}

	@Override
	public boolean removeTypeMouvementStock(TypeMouvementStock ei) {
		return mouvementsStockDAO.removeTypeMouvementStock(ei);
	}

	@Override
	public List<TypeMouvementStock> getAllTypeMouvementStock() {
		return mouvementsStockDAO.getAllTypeMouvementStock();
	}

	@Override
	public OrigineMouvement createOrUpdateOrigineMouvement(OrigineMouvement ei) {
		return origineMouvementDAO.createOrUpdateOrigineMouvement(ei);
	}

	@Override
	public OrigineMouvement getOrigineMouvement(String code) {
		return origineMouvementDAO.getOrigineMouvement(code);
	}

	@Override
	public boolean removeOrigineMouvement(OrigineMouvement ei) {
		return origineMouvementDAO.removeOrigineMouvement(ei);
	}

	@Override
	public List<OrigineMouvement> getAllOrigineMouvement() {
		return origineMouvementDAO.getAllOrigineMouvement();
	}

	@Override
	public TauxASDI createOrUpdateTauxASDI(TauxASDI ei) {
		return tauxASDIDAO.createOrUpdateTauxASDI(ei);
	}

	@Override
	public TauxASDI getTauxASDI(String code) {
		return tauxASDIDAO.getTauxASDI(code);
	}

	@Override
	public boolean removeTauxASDI(TauxASDI ei) {
		return tauxASDIDAO.removeTauxASDI(ei);
	}

	@Override
	public List<TauxASDI> getAllTauxASDI() {
		return tauxASDIDAO.getAllTauxASDI();
	}

	@Override
	public TauxTVA createOrUpdateTauxTVA(TauxTVA ei) {
		return tauxTVADAO.createOrUpdateTauxTVA(ei);
	}

	@Override
	public TauxTVA getTauxTVA(String code) {
		return tauxTVADAO.getTauxTVA(code);
	}

	@Override
	public boolean removeTauxTVA(TauxTVA ei) {
		return tauxTVADAO.removeTauxTVA(ei);
	}

	@Override
	public List<TauxTVA> getAllTauxTVA() {
		return tauxTVADAO.getAllTauxTVA();
	}

	@Override
	public List<Rayon> getAllRayonBySecteur(String codeSecteur) {
		return rayonDAO.getAllRayonBySecteur(codeSecteur);
	}

	@Override
	public List<Famille> getAllFamilleByRayonBySecteur(String codeRayon) {
		return familleDAO.getAllFamilleBySecteurAndRayon(codeRayon);
	}

	@Override
	public List<SousFamille> getAllSousFamilleByFamilleByRayonBySecteur(String codeFamille) {
		return sousFamilleDAO.getAllSousFamilleBySecteurAndRayonAndFamille(codeFamille);
	}

	@Override
	public List<TypeMouvementStock> getAllTypeMouvementStockByOrigineMouvement(String origineMouvement) {
		return mouvementsStockDAO.getAllTypeMouvementStockByOrigineMouvement(origineMouvement);
	}

	@Override
	public Festif createOrUpdateFestif(Festif ei) {
		return festifDAO.createOrUpdateFestif(ei);
	}

	@Override
	public Festif getFestif(long code) {
		return festifDAO.getFestif(code);
	}

	@Override
	public boolean removeFestif(Festif ei) {
		return festifDAO.removeFestif(ei);
	}

	@Override
	public List<Festif> getAllFestif() {
		return festifDAO.getAllFestif();
	}

	@Override
	public List<Article> getAllArticleFestif(String pvtCode) {
		return festifDAO.getAllArticleFestif(pvtCode);
	}

	@Override
	public boolean isArticleFestif(String codeArticle, String pvtCode) {
		return festifDAO.isArticleFestif(codeArticle, pvtCode);
	}

	@Override
	public Festif getFestif(String codeArticle, String mag) {
		return festifDAO.getFestif(codeArticle, mag);
	}

}
