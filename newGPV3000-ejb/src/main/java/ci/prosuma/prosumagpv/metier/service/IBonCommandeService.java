package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.HistoriqueEnvoiEBCF;
import ci.prosuma.prosumagpv.entity.pk.EnteteSuggestionPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeLivraison;

public interface IBonCommandeService {

	public EnteteBonCommandeFournisseur createOrUpdateEBCF(
			EnteteBonCommandeFournisseur entity);

	public EnteteBonCommandeFournisseur createOrUpdateEBCFDetail(
			EnteteBonCommandeFournisseur a);

	public EnteteBonCommandeFournisseur updateEBCFReceptDetail(
			EnteteBonCommandeFournisseur a);

	public EnteteBonCommandeFournisseur getEBCF(long entityId);
        
         public EnteteBonCommandeFournisseur getEBCFWithoutDetails(long entityId);

	public boolean removeEBCF(EnteteBonCommandeFournisseur entity);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMag(
			String mag);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
			String mag, Date dateDebut, Date dateFin, OrigineCommande origine,
			EtatCommande etat);

	public List<EnteteBonCommandeFournisseur> getLastCommandEnteteBonCommandeFournisseurForMag(
			String mag, int limit, OrigineCommande origine, EtatCommande etat);

	public EnteteBonCommandeFournisseur createTempBonCommande(
			EnteteBonCommandeFournisseur ebcf,
			List<DetailBonCommandeFournisseur> listDetailBonCommandeFournisseurs);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRange(
			String mag, Date dateDebut, Date dateFin);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndUserCreation(
			String mag, String userName);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndEtat(
			String mag, EtatCommande etatCommande);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(
			String mag, OrigineCommande origineCommande,
			EtatCommande etatCommande);
        
        public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtatWhithDetails(
            String mag, OrigineCommande origineCommande,
            EtatCommande etatCommande);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndFournisseur(
			String mag, String refFournisseur);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndSuggestion(
			String mag, long suggestionCommandeId);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigine(
			String mag, OrigineCommande origineCommande);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndTypeLivraison(
			String mag, TypeLivraison typelivraison);

	public List<EnteteBonCommandeFournisseur> getAllEBCFForMagBySecteurAndRayon(
			String mag, String codeSecteur, String codeRayon);

	public DetailBonCommandeFournisseur createOrUpdateDBCF(
			DetailBonCommandeFournisseur entity);

	public DetailBonCommandeFournisseur getDBCF(long entityId);

	public boolean removeDBCF(DetailBonCommandeFournisseur entity);

	public EnteteBonCommandeFournisseur createBonCommande(
			EnteteBonCommandeFournisseur ebcf,
			List<DetailBonCommandeFournisseur> listDetailBonCommande);

	public List<DetailBonCommandeFournisseur> getAllDSCforESC(long entity);

	/* ENTETE SUGGESTION COMMANDE **/

	public EnteteBonCommandeFournisseur saveSuggestionCommandeToBonCommandeFournisseur(
			EnteteSuggestionCommande entete, String mag, String user);

	public EnteteSuggestionCommande createOrUpdateESC(
			EnteteSuggestionCommande entity);

	public EnteteSuggestionCommande getESC(EnteteSuggestionPK a);

	public boolean removeESC(EnteteSuggestionCommande entity);

	public List<EnteteSuggestionCommande> getAllESCForMag(String mag);

	public String refreshESCFromAS400(String mag);

	public void saveSuggestionToAS400NoPlacement(String mag, String numDossier);

	/** DETAIL SUGGESTION **/

	public DetailSuggestionCommande createOrUpdateDSC(
			DetailSuggestionCommande entity);

	public DetailSuggestionCommande getDSC(long entityId);

	public boolean removeDSC(DetailSuggestionCommande entity);

	public List<EnteteBonCommandeFournisseur> filterDataTable(
			String etatSearch, String numSearch, String secteurSearch,
			String rayonSearch, String fourSearch, String mag);

	/* TEST FORCE **/

	public String sendCommandeReassort(PointDeVente pvt);
	
	public String sendCommandeReassortForAX(PointDeVente pvt);

	public String reSendCommandeReassort(PointDeVente pvt);

	public String sendCommandeLD(PointDeVente pvt,
			List<EnteteBonCommandeFournisseur> listEBCF);

	/* SAVE SUGGESTION AS4000 **/

	public void saveSuggestionToAS400(EnteteBonCommandeFournisseur ebcf);

	public List<EnteteBonCommandeFournisseur> executeLazyQuery(String query,
			int first, int pageSize);

	public Long getRowCount(String query);

	public String reSendCommandeLD(HistoriqueEnvoiEBCF histo, String codeMag);

	public Map<String, HashMap<String, Article>> importationCmdRapidPDA(
			String mag);

	public List<EnteteBonCommandeFournisseur> getAllCommandEnteteBonCommandeFournisseurForMagForListEtat(
			String mag, OrigineCommande origine, List<EtatCommande> etat);
        
        public EnteteBonCommandeFournisseur UpdateEBCF(EnteteBonCommandeFournisseur entity);

}
