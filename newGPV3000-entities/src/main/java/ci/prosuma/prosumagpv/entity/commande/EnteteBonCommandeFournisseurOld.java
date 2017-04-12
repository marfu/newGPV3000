///**
// * 
// */
//package ci.prosuma.prosumagpv.entity.commande;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//
//import ci.prosuma.prosumagpv.entity.Fournisseur;
//import ci.prosuma.prosumagpv.entity.PointDeVente;
//import ci.prosuma.prosumagpv.entity.stock.Depot;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeLivraison;
//import ci.prosuma.prosumagpv.entity.util.Rayon;
//import ci.prosuma.prosumagpv.entity.util.Secteur;
//
///**
// * @author AKHDAR Zoul
// * 
// */
//@Entity
//@Table(name = "BON_COMMANDE_FOURNISSEUR_ENTETE")
//public class EnteteBonCommandeFournisseurOld implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name = "ENTETE_COMMANDE_PK")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
//	@ManyToOne
//	@JoinColumn(name = "REF_FOURNISSEUR_FK", referencedColumnName = "REF_FOURNISSEUR_PK")
//	private Fournisseur fournisseur;
//
//	@Column(name = "ORIGINE_COMMANDE")
//	@Enumerated(EnumType.STRING)
//	private OrigineCommande origineCommande;
//
//	@Column(name = "TYPE_LIVRAISON")
//	@Enumerated(EnumType.STRING)
//	private TypeLivraison typeLivraison;
//
//	@Column(name = "NUM_SUGGESTION")
//	private String numDossierSuggestion;
//	
//
//	@Column(name = "OBSERVATION",nullable = false)
//	private String observation;
//
//	@ManyToOne
//	@JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK")
//	private PointDeVente pvt;
//
//	@ManyToOne
//	@JoinColumn(name = "DEPOT_FK", referencedColumnName = "DEPOT_PK")
//	private Depot depot;
//
//	@ManyToOne
//	@JoinColumn(name = "SECTEUR_FK", referencedColumnName = "SECTEUR_PK")
//	private Secteur secteur;
//
//	@ManyToOne
//	@JoinColumn(name = "RAYON_FK", referencedColumnName = "RAYON_PK")
//	private Rayon rayon;
//
//	@Column(name = "ETAT_COMMANDE")
//	@Enumerated(EnumType.STRING)
//	private EtatCommande etatCommande;
//
//	@Column(name = "VALEUR_COMMANDE_PF")
//	private double valeurPF;
//
//	@Column(name = "VALEUR_COMMANDE_PV")
//	private double valeurPV;
//
//	@Column(name = "VALEUR_MARGE")
//	private double valeurMarge;
//	
//	@Column(name = "VALEUR_MARGE_POURCENT")
//	private double valeurMargePourcent;
//
//	@Column(name = "UTILISATEUR_CREATION")
//	private String userCreation;
//
//	@Column(name = "UTILISATEUR_MODIFICATION")
//	private String userModification;
//
//	@Column(name = "UTILISATEUR_VALIDATION")
//	private String userValidation;
//
//	@Column(name = "UTILISATEUR_RECEPTION")
//	private String userReception;
//
//	@Column(name = "UTILISATEUR_CLOTURE")
//	private String userCloture;
//
//	@Column(name = "DATE_TRANSMITION_COMMANDE")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateTransmission;
//
//	@Column(name = "DATE_CREATION_COMMANDE")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateCreation;
//
//	@Column(name = "DATE_RECEPTION")
//	@Temporal(TemporalType.DATE)
//	private Date dateReception;
//	
//	@Column(name = "DATE_LIVRAISON")
//	@Temporal(TemporalType.DATE)
//	private Date dateLivraison;
//	
//	
//	private String blFournisseur;
//	
//	private boolean cloturer;
//	
//	private boolean alerte;
//		
//	@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true )
//	@JoinTable(name = "TABLE_LIAISON_BCF_ENTETE_DETAIL", joinColumns = @JoinColumn(name = "ENTETE_COMMANDE_FK", referencedColumnName = "ENTETE_COMMANDE_PK"), inverseJoinColumns = @JoinColumn(name = "DETAIL_COMMANDE_FK", referencedColumnName = "DETAIL_COMMANDE_PK"))
//	private List<DetailBonCommandeFournisseur> detailBonCommande;
//
//	public EnteteBonCommandeFournisseurOld() {
//	}
//
//	@Override
//	public int hashCode() {
//		final int PRIME = 31;
//		int result = 1;
//		result = PRIME * result + (int) getId();
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		final EnteteBonCommandeFournisseurOld other = (EnteteBonCommandeFournisseurOld) obj;
//		if (getId() != other.getId()) {
//			return false;
//		}
//		return true;
//	}
//
//	
//	
//	
//	public String getBlFournisseur() {
//		return blFournisseur;
//	}
//
//	public void setBlFournisseur(String blFournisseur) {
//		this.blFournisseur = blFournisseur;
//	}
//
//	public boolean isCloturer() {
//		return cloturer;
//	}
//
//	public void setCloturer(boolean cloturer) {
//		this.cloturer = cloturer;
//	}
//
//	public String getUserCreation() {
//		return userCreation;
//	}
//
//	public void setUserCreation(String userCreation) {
//		this.userCreation = userCreation;
//	}
//
//	
//
//	public String getNumDossierSuggestion() {
//		return numDossierSuggestion;
//	}
//
//	public void setNumDossierSuggestion(String numDossierSuggestion) {
//		this.numDossierSuggestion = numDossierSuggestion;
//	}
//
//	public PointDeVente getPvt() {
//		return pvt;
//	}
//
//	public void setPvt(PointDeVente pvt) {
//		this.pvt = pvt;
//	}
//
//	public Secteur getSecteur() {
//		return secteur;
//	}
//
//	public void setSecteur(Secteur secteur) {
//		this.secteur = secteur;
//	}
//
//	public Rayon getRayon() {
//		return rayon;
//	}
//
//	public void setRayon(Rayon rayon) {
//		this.rayon = rayon;
//	}
//
//	public double getValeurPF() {
//		return valeurPF;
//	}
//
//	public void setValeurPF(double valeurPF) {
//		this.valeurPF = valeurPF;
//	}
//
//	public double getValeurPV() {
//		return valeurPV;
//	}
//
//	public void setValeurPV(double valeurPV) {
//		this.valeurPV = valeurPV;
//	}
//
//	public double getValeurMarge() {
//		return valeurMarge;
//	}
//
//	public void setValeurMarge(double valeurMarge) {
//		this.valeurMarge = valeurMarge;
//	}
//
//	public String getUserModification() {
//		return userModification;
//	}
//
//	public void setUserModification(String userModification) {
//		this.userModification = userModification;
//	}
//
//	public String getUserValidation() {
//		return userValidation;
//	}
//
//	public void setUserValidation(String userValidation) {
//		this.userValidation = userValidation;
//	}
//
//	public String getUserReception() {
//		return userReception;
//	}
//
//	public void setUserReception(String userReception) {
//		this.userReception = userReception;
//	}
//
//	public String getUserCloture() {
//		return userCloture;
//	}
//
//	public void setUserCloture(String userCloture) {
//		this.userCloture = userCloture;
//	}
//
//	public Date getDateReception() {
//		return dateReception;
//	}
//
//	public void setDateReception(Date dateReception) {
//		this.dateReception = dateReception;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Fournisseur getFournisseur() {
//		return fournisseur;
//	}
//
//	public void setFournisseur(Fournisseur fournisseur) {
//		this.fournisseur = fournisseur;
//	}
//
//	public OrigineCommande getOrigineCommande() {
//		return origineCommande;
//	}
//
//	public void setOrigineCommande(OrigineCommande origineCommande) {
//		this.origineCommande = origineCommande;
//	}
//
//	public TypeLivraison getTypeLivraison() {
//		return typeLivraison;
//	}
//
//	public void setTypeLivraison(TypeLivraison typeLivraison) {
//		this.typeLivraison = typeLivraison;
//	}
//
//	public String getObservation() {
//		return observation;
//	}
//
//	public void setObservation(String observation) {
//		this.observation = observation;
//	}
//
//	public List<DetailBonCommandeFournisseur> getDetailBonCommande() {
//		return detailBonCommande;
//	}
//
//	public void setDetailBonCommande(
//			List<DetailBonCommandeFournisseur> detailBonCommande) {
//		this.detailBonCommande = detailBonCommande;
//	}
//
//	public Depot getDepot() {
//		return depot;
//	}
//
//	public void setDepot(Depot depot) {
//		this.depot = depot;
//	}
//
//	public EtatCommande getEtatCommande() {
//		return etatCommande;
//	}
//
//	public void setEtatCommande(EtatCommande etatCommande) {
//		this.etatCommande = etatCommande;
//	}
//
//	public Date getDateTransmission() {
//		return dateTransmission;
//	}
//
//	public void setDateTransmission(Date dateTransmission) {
//		this.dateTransmission = dateTransmission;
//	}
//
//	public Date getDateCreation() {
//		return dateCreation;
//	}
//
//	public void setDateCreation(Date dateCreation) {
//		this.dateCreation = dateCreation;
//	}
//
//	public double getValeurMargePourcent() {
//		return valeurMargePourcent;
//	}
//
//	public void setValeurMargePourcent(double valeurMargePourcent) {
//		this.valeurMargePourcent = valeurMargePourcent;
//	}
//
//	public Date getDateLivraison() {
//		return dateLivraison;
//	}
//
//	public void setDateLivraison(Date dateLivraison) {
//		this.dateLivraison = dateLivraison;
//	}
//
//	/**
//	 * @return the alerte
//	 */
//	public boolean isAlerte() {
//		return alerte;
//	}
//
//	/**
//	 * @param alerte the alerte to set
//	 */
//	public void setAlerte(boolean alerte) {
//		this.alerte = alerte;
//	}
///*
//	@Override
//	public String toString() {
//		return "EnteteBonCommandeFournisseur [id=" + id + ", fournisseur="
//				+ fournisseur + ", origineCommande=" + origineCommande
//				+ ", typeLivraison=" + typeLivraison
//				+ ", numDossierSuggestion=" + numDossierSuggestion
//				+ ", observation=" + observation + ", pvt=" + pvt + ", depot="
//				+ depot + ", secteur=" + secteur + ", rayon=" + rayon
//				+ ", etatCommande=" + etatCommande + ", valeurPF=" + valeurPF
//				+ ", valeurPV=" + valeurPV + ", valeurMarge=" + valeurMarge
//				+ ", valeurMargePourcent=" + valeurMargePourcent
//				+ ", userCreation=" + userCreation + ", userModification="
//				+ userModification + ", userValidation=" + userValidation
//				+ ", userReception=" + userReception + ", userCloture="
//				+ userCloture + ", dateTransmission=" + dateTransmission
//				+ ", dateCreation=" + dateCreation + ", dateReception="
//				+ dateReception + ", dateLivraison=" + dateLivraison
//				+ ", blFournisseur=" + blFournisseur + ", cloturer=" + cloturer
//				+ ", alerte=" + alerte + ", detailBonCommande="
//				+ detailBonCommande + "]";
//	}	
//	*/
//	
//}
