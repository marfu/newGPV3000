package ci.prosuma.prosumagpv.metier.service;

import java.util.List;

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

public interface IClassificationService {

	public Secteur createOrUpdateSecteur(Secteur ei);

	public Secteur getSecteur(String code);

	public boolean removeSecteur(Secteur ei);

	public List<Secteur> getAllSecteur();

	public Rayon createOrUpdateRayon(Rayon ei);

	public Rayon getRayon(String code);

	public boolean removeRayon(Rayon ei);

	public List<Rayon> getAllRayon();

	public Famille createOrUpdateFamille(Famille ei);

	public Famille getFamille(String code);

	public boolean removeFamille(Famille ei);

	public List<Famille> getAllFamille();

	public SousFamille createOrUpdateSousFamille(SousFamille ei);

	public SousFamille getSousFamille(String code);

	public boolean removeSousFamille(SousFamille ei);

	public List<SousFamille> getAllSousFamille();

	public CategorieClient createOrUpdateCategorieClient(CategorieClient ei);

	public CategorieClient getCategorieClient(String categorie);

	public boolean removeCategorieClient(CategorieClient ei);

	public List<CategorieClient> getAllCategorieClient();

	public CodeAnalytique createOrUpdateCodeAnalytique(CodeAnalytique ei);

	public CodeAnalytique getCodeAnalytique(String code);

	public boolean removeCodeAnalytique(CodeAnalytique ei);

	public List<CodeAnalytique> getAllCodeAnalytique();

	public TypeDepot createOrUpdateTypeDepot(TypeDepot ei);

	public TypeDepot getTypeDepot(String code);

	public boolean removeTypeDepot(TypeDepot ei);

	public List<TypeDepot> getAllTypeDepot();

	public ModeReglement createOrUpdateModeReglement(ModeReglement ei);

	public ModeReglement getModeReglement(String code);

	public boolean removeModeReglement(ModeReglement ei);

	public List<ModeReglement> getAllModeReglement();

	public TypeMouvementStock createOrUpdateTypeMouvementStock(
			TypeMouvementStock ei);

	public TypeMouvementStock getTypeMouvementStock(String code);

	public boolean removeTypeMouvementStock(TypeMouvementStock ei);

	public List<TypeMouvementStock> getAllTypeMouvementStock();
	
	public List<TypeMouvementStock> getAllTypeMouvementStockByOrigineMouvement(String origineMouvement);

	public OrigineMouvement createOrUpdateOrigineMouvement(OrigineMouvement ei);

	public OrigineMouvement getOrigineMouvement(String code);

	public boolean removeOrigineMouvement(OrigineMouvement ei);

	public List<OrigineMouvement> getAllOrigineMouvement();

	public TauxASDI createOrUpdateTauxASDI(TauxASDI ei);

	public TauxASDI getTauxASDI(String code);

	public boolean removeTauxASDI(TauxASDI ei);

	public List<TauxASDI> getAllTauxASDI();

	public TauxTVA createOrUpdateTauxTVA(TauxTVA ei);

	public TauxTVA getTauxTVA(String code);

	public boolean removeTauxTVA(TauxTVA ei);

	public List<TauxTVA> getAllTauxTVA();
	
	public List<Rayon> getAllRayonBySecteur(String codeSecteur);
	
	public List<Famille> getAllFamilleByRayonBySecteur(String codeRayon);
	
	public List<SousFamille> getAllSousFamilleByFamilleByRayonBySecteur(String codeFamille);
	
	public Festif createOrUpdateFestif(Festif ei);
	
	public Festif getFestif(long code);
	
	public boolean removeFestif(Festif ei);
	
	public List<Festif> getAllFestif();

	public List<Article> getAllArticleFestif(String pvtCode);

	public boolean isArticleFestif(String codeArticle, String pvtCode);

	public Festif getFestif(String codeArticle, String mag);

}
