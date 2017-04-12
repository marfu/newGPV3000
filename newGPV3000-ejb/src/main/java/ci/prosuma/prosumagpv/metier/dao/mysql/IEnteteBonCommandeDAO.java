package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeLivraison;

public interface IEnteteBonCommandeDAO {

	public List<DetailBonCommandeFournisseur> getAllDSCforESC(long enteteId);

	public EnteteBonCommandeFournisseur createOrUpdateEBCF(
			EnteteBonCommandeFournisseur entity);
        
        public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtatWhithDetails(
            String mag, OrigineCommande origineCommande,
            EtatCommande etatCommande);

	public EnteteBonCommandeFournisseur createOrUpdateEBCFDetail(EnteteBonCommandeFournisseur a);
        
        public EnteteBonCommandeFournisseur updateBonCommande(EnteteBonCommandeFournisseur a);
        
        
//        public EnteteBonCommandeFournisseur UpdateEBCFDetail(EnteteBonCommandeFournisseur a);

	public EnteteBonCommandeFournisseur updateEBCFReceptDetail(
			EnteteBonCommandeFournisseur a);

	public EnteteBonCommandeFournisseur getEBCF(long entityId);
        
        public EnteteBonCommandeFournisseur getEBCFWithDetails(long entityId);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndOrigineAndEtat(
			String mag, OrigineCommande origineCommande,
			EtatCommande etatCommande);

	public boolean removeEBCF(EnteteBonCommandeFournisseur entity);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMag(
			String mag);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRange(
			String mag, Date dateDebut, Date dateFin);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
			String mag, Date dateDebut, Date dateFin,
			OrigineCommande origineCommande, EtatCommande etatCommande);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndUserCreation(
			String mag, String userName);

	public List<EnteteBonCommandeFournisseur> getAllEnteteBonCommandeFournisseurForMagAndEtat(
			String mag, EtatCommande etatCommande);

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

	public List<EnteteBonCommandeFournisseur> filterDataTable(String query,
			String etatSearch, String numSearch, String secteurSearch,
			String rayonSearch, String fourSearch, String mag);

	public List<EnteteBonCommandeFournisseur> executeLazyQuery(String query,
			int first, int pageSize);

	public Long getRowCount(String query);

	public List<EnteteBonCommandeFournisseur> getLastCommandEnteteBonCommandeFournisseurForMag(
			String mag, int limit, OrigineCommande origine, EtatCommande etat);

	public List<EnteteBonCommandeFournisseur> getAllCommandEnteteBonCommandeFournisseurForMagForListEtat(
			String mag, OrigineCommande origine, List<EtatCommande> etat);
        
        
        public EnteteBonCommandeFournisseur saveBonCommande(EnteteBonCommandeFournisseur ebcf);
}
