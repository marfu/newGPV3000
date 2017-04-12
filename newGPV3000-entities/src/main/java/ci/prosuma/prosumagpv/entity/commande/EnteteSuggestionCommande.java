/**
 * 
 */
package ci.prosuma.prosumagpv.entity.commande;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ci.prosuma.prosumagpv.entity.pk.EnteteSuggestionPK;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatDossierSuggestion;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeDossier;

/**
 * @author Serge AYEPI
 * 
 */
//@Entity
//@Table(name = "SUGGESTION_COMMANDE_ENTETE")
//@IdClass(value = EnteteSuggestionPK.class)
public class EnteteSuggestionCommande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE_MAGASIN_PK")
	private String pvtCode;

	@Id
	@Column(name = "NUM_DOSSIER_PK")
	private String numDossier;
	

	

	@Column(name = "DATE_SUGGESTION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSuggestion;

	@Column(name = "DATE_LIMITE_ENVOI")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLimiteEnvoi;

	@Column(name = "DATE_LIVRAISON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLivaison;

	
	@Column(name = "CODE_PROMO_FK")
	private String promo;

	
	@Column(name = "REF_FOURNISSEUR_FK")
	private String fournisseur;

	@Column(name = "SI_CLOTURER")
	private boolean cloturer;

	@Column(name = "SECTEUR_FK")
	private String secteur;

	@Column(name = "RAYON_FK")
	private String rayon;

	@Column(name = "OBSERVATION")
	private String observation;

	@Column(name = "ETAT_DU_DOSSIER")
	@Enumerated(EnumType.STRING)
	private EtatDossierSuggestion etatDossier;

	@Column(name = "MODE_DU_DOSSIER")
	@Enumerated(EnumType.STRING)
	private ModeDossier modeDossier;

	@Column(name = "TYPE_APPRO")
	private String typeAppro;
	
	private boolean alerte;
	
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "enteteSuggestion")
	private List<DetailSuggestionCommande> detailSuggestionCommande;

	@Column(name = "UTILISATEUR_CREATION")
	private String utilisateurCrea;

	@Override
	public int hashCode() {
		return (getPvtCode()  + getNumDossier()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EnteteSuggestionPK other = (EnteteSuggestionPK) obj;
		if ( !getPvtCode().equals(other.getPvtCode())
				  || ! getNumDossier().equals(other.getNumDossier()) ) {
			return false;
		}
		return true;
	}

	
	

	public String getPvtCode() {
		return pvtCode;
	}

	public void setPvtCode(String pvtCode) {
		this.pvtCode = pvtCode;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	

	public Date getDateSuggestion() {
		return dateSuggestion;
	}

	public void setDateSuggestion(Date dateSuggestion) {
		this.dateSuggestion = dateSuggestion;
	}

	public Date getDateLimiteEnvoi() {
		return dateLimiteEnvoi;
	}

	public void setDateLimiteEnvoi(Date dateLimiteEnvoi) {
		this.dateLimiteEnvoi = dateLimiteEnvoi;
	}

	public Date getDateLivaison() {
		return dateLivaison;
	}

	public void setDateLivaison(Date dateLivaison) {
		this.dateLivaison = dateLivaison;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<DetailSuggestionCommande> getDetailSuggestionCommande() {
		return detailSuggestionCommande;
	}

	public void setDetailSuggestionCommande(
			List<DetailSuggestionCommande> detailSuggestionCommande) {
		this.detailSuggestionCommande = detailSuggestionCommande;
	}

	public void addDetailSuggestionCommande(
			DetailSuggestionCommande detailSuggestionCommande) {
		if(this.detailSuggestionCommande == null){
			this.detailSuggestionCommande = new ArrayList<DetailSuggestionCommande>();
		}
		this.detailSuggestionCommande.add(detailSuggestionCommande);
	}


	public String getUtilisateurCrea() {
		return utilisateurCrea;
	}

	public void setUtilisateurCrea(String utilisateurCrea) {
		this.utilisateurCrea = utilisateurCrea;
	}

	public EtatDossierSuggestion getEtatDossier() {
		return etatDossier;
	}

	public void setEtatDossier(EtatDossierSuggestion etatDossier) {
		this.etatDossier = etatDossier;
	}

	public ModeDossier getModeDossier() {
		return modeDossier;
	}

	public void setModeDossier(ModeDossier modeDossier) {
		this.modeDossier = modeDossier;
	}

	public String getTypeAppro() {
		return typeAppro;
	}

	public void setTypeAppro(String typeAppro) {
		this.typeAppro = typeAppro;
	}

	public boolean isCloturer() {
		return cloturer;
	}

	public void setCloturer(boolean cloturer) {
		this.cloturer = cloturer;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getRayon() {
		return rayon;
	}

	public void setRayon(String rayon) {
		this.rayon = rayon;
	}
/*
	@Override
	public String toString() {
		return "EnteteSuggestionCommande [pvtCode=" + pvtCode + ", numDossier="
				+ numDossier + ", dateSuggestion=" + dateSuggestion
				+ ", dateLimiteEnvoi=" + dateLimiteEnvoi + ", dateLivaison="
				+ dateLivaison + ", promo=" + promo + ", fournisseur="
				+ fournisseur + ", cloturer=" + cloturer + ", secteur="
				+ secteur + ", rayon=" + rayon + ", observation=" + observation
				+ ", etatDossier=" + etatDossier + ", modeDossier="
				+ modeDossier + ", typeAppro=" + typeAppro
				+ ", detailSuggestionCommande=" + detailSuggestionCommande
				+ ", utilisateurCrea=" + utilisateurCrea + "]";
	}
*/
	/**
	 * @return the alerte
	 */
	public boolean isAlerte() {
		return alerte;
	}

	/**
	 * @param alerte the alerte to set
	 */
	public void setAlerte(boolean alerte) {
		this.alerte = alerte;
	}

	
	

}
