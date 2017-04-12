package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



//@Entity
//@Table(name = "FOURNISSEUR")
public class Fournisseur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, name = "REF_FOURNISSEUR_PK" )
	private String refFournisseur;

	@Column(unique = true, name = "EMAIL" )
	private String email;

	@Column(name = "NUM_TEL" )
	private String numTel;

	@Column(name = "BP" )
	private String bp;

	@Column(name = "NUM_CEL")
	private String numCell;

	@Column(name = "NUM_FAX" )
	private String numFax;

	@Column(name = "NOTES" )
	private String notes;

	@Column(name = "ADRESSE")
	private String adresse;

	@Column(name = "VILLE")
	private String ville;

	@Column(name = "PAYS")
	private String pays;

	@Column(name = "SI_ACTIF")
	private boolean enable;
	
	@Column(name = "MODIFIER")
	private boolean modifier;
	
	@Column(name = "NOUVEAU")
	private boolean nouveau;

	@Column(name = "RAISON_SOCIAL",nullable = false)
	private String raisonSocial;

	@Column(name = "NUM_CC")
	private String numContribuable;

	@Column(name = "NUM_RC")
	private String registreCommerce;

	@Column(name = "SI_LIVRAISON_CENTRALE")
	private boolean livraisonCentrale;

	@Column(name = "SI_LIVRAISON_DIRECT")
	private boolean livraisonDirecte;

	@Column(name = "MOT_DIRECTEUR")
	private String motDirecteur;

	
	@Column(name = "MODE_REGLEMENT")
	private String modeReglement;

	@Column(name = "CONTACT")
	private String contact;

	/**
	 * 
	 */
	public Fournisseur() {
	}

	@Override
	public int hashCode() {
		return super.hashCode();
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
		final Fournisseur other = (Fournisseur) obj;
		if (other == null || other.getRefFournisseur() == null
				|| getRefFournisseur() == null)
			return false;

		if (!getRefFournisseur().equals(other.getRefFournisseur())) {
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return this.getRefFournisseur();
	}

	public boolean needUpdate(Fournisseur four){
	
		if( isEnable() != four.isEnable())
			return true;
		if( isLivraisonCentrale() != four.isLivraisonCentrale())
			return true;
		if( isLivraisonDirecte() != four.isLivraisonDirecte())
			return true;
		
		return false;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getNumCell() {
		return numCell;
	}

	public void setNumCell(String numCell) {
		this.numCell = numCell;
	}

	public String getNumFax() {
		return numFax;
	}

	public void setNumFax(String numFax) {
		this.numFax = numFax;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRefFournisseur() {
		return refFournisseur;
	}

	public void setRefFournisseur(String refFournisseur) {
		this.refFournisseur = refFournisseur;
	}

	public String getMotDirecteur() {
		return motDirecteur;
	}

	public void setMotDirecteur(String motDirecteur) {
		this.motDirecteur = motDirecteur;
	}

	public boolean isLivraisonCentrale() {
		return livraisonCentrale;
	}

	public void setLivraisonCentrale(boolean livraisonCentrale) {
		this.livraisonCentrale = livraisonCentrale;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public boolean isLivraisonDirecte() {
		return livraisonDirecte;
	}

	public void setLivraisonDirecte(boolean livraisonDirecte) {
		this.livraisonDirecte = livraisonDirecte;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	public String getNumContribuable() {
		return numContribuable;
	}

	public void setNumContribuable(String numContribuable) {
		this.numContribuable = numContribuable;
	}

	public String getRegistreCommerce() {
		return registreCommerce;
	}

	public void setRegistreCommerce(String registreCommerce) {
		this.registreCommerce = registreCommerce;
	}

	public boolean isModifier() {
		return modifier;
	}

	public void setModifier(boolean modifier) {
		this.modifier = modifier;
	}

	public boolean isNouveau() {
		return nouveau;
	}

	public void setNouveau(boolean nouveau) {
		this.nouveau = nouveau;
	}

	
	

}
