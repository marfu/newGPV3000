/**
 * 
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import ci.prosuma.prosumagpv.entity.stock.Depot;

//@Entity
//@Table(name = "POINT_DE_VENTE")
public class PointDeVente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE_MAGASIN_PK", unique = true, length = 3)
	private String pvtCode;

	@Column(name = "MOT_DIRECTEUR", length = 25)
	private String motDirecteur;

	@Column(name = "RAISON_SOCIAL", length = 50,nullable = false)
	private String raisonSocialFournisseur;

	@Column(name = "ADRESSE_1")
	private String adresse1;

	@Column(name = "ADRESSE_2")
	private String adresse2;

	@Column(name = "CODE_POSTAL")
	private String codePostal;

	@Column(name = "VILLE")
	private String ville;

	@Column(name = "REGION")
	private String region;

	@Column(name = "PAYS")
	private String pays;

	@Column(name = "NUM_TEL")
	private String phoneNumber;

	@Column(name = "NUM_FAX")
	private String faxNumber;

	@Column(name = "NUM_GERANT")
	private String numGerant;

	@Column(name = "NOM_GERANT")
	private String nomGerant;

	@Column(name = "GESTION_PRIX_VENTE")
	private boolean gestionPrixVente;

	@Column(name = "TYPE_POINT_VENTE")
	private String typePointDeVente;

	@Column(name = "LDAP_OU")
	private String ldapOU;

	@Column(name = "SI_VENTE_DEMI_GROS")
	private boolean demiGros;

	@Column(name = "SI_VENTE_TERMES")
	private boolean ventesTermes;

	@Column(name = "SI_VENTE_E_COMMERCE")
	private boolean eCommerce;

	@Column(name = "SI_MULTI_DEPOT")
	private boolean multiDepot;

	@Column(name = "SI_COMMANDE_CENTRALE")
	private boolean cdeCentrale;

	@Column(name = "SI_COMMANDE_LD")
	private boolean cdeLD;

	@Column(name = "SI_ACTIF")
	private boolean actif;

	@Column(name = "CODE_IP")
	private String codeIP;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH })
	@JoinTable(name = "TABLE_LIAISON_PVT_DEPOT", 
                joinColumns = @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"), 
                inverseJoinColumns = @JoinColumn(name = "DEPOT_MAGASIN_FK", referencedColumnName = "DEPOT_PK")
        )
	private List<Depot> depot;

	public PointDeVente() {
	}

	@Override
	public int hashCode() {
		if(getPvtCode() != null)
		return getPvtCode().hashCode();
		
		return 1;
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
		final PointDeVente other = (PointDeVente) obj;
		if (getPvtCode() == null || other.getPvtCode() == null) {
			return false;
		}

		if (!getPvtCode().equals(other.getPvtCode())) {
			return false;
		}
		return true;
	}

	public String getLdapOU() {
		return ldapOU;
	}

	public void setLdapOU(String ldapOU) {
		this.ldapOU = ldapOU;
	}

	public String getPvtCode() {
		return pvtCode;
	}

	public void setPvtCode(String pvtCode) {
		this.pvtCode = pvtCode;
	}

	public String getMotDirecteur() {
		return motDirecteur;
	}

	public void setMotDirecteur(String motDirecteur) {
		this.motDirecteur = motDirecteur;
	}

	public String getRaisonSocialFournisseur() {
		return raisonSocialFournisseur;
	}

	public void setRaisonSocialFournisseur(String raisonSocialFournisseur) {
		this.raisonSocialFournisseur = raisonSocialFournisseur;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getNumGerant() {
		return numGerant;
	}

	public void setNumGerant(String numGerant) {
		this.numGerant = numGerant;
	}

	public String getNomGerant() {
		return nomGerant;
	}

	public void setNomGerant(String nomGerant) {
		this.nomGerant = nomGerant;
	}

	public boolean isGestionPrixVente() {
		return gestionPrixVente;
	}

	public void setGestionPrixVente(boolean gestionPrixVente) {
		this.gestionPrixVente = gestionPrixVente;
	}

	public String getTypePointDeVente() {
		return typePointDeVente;
	}

	public void setTypePointDeVente(String typePointDeVente) {
		this.typePointDeVente = typePointDeVente;
	}

//	public List<CompteBancaire> getCompteBancaires() {
//		return compteBancaires;
//	}
//
//	public void setCompteBancaires(List<CompteBancaire> compteBancaires) {
//		this.compteBancaires = compteBancaires;
//	}

	public boolean isDemiGros() {
		return demiGros;
	}

	public void setDemiGros(boolean demiGros) {
		this.demiGros = demiGros;
	}

	public boolean isVentesTermes() {
		return ventesTermes;
	}

	public void setVentesTermes(boolean ventesTermes) {
		this.ventesTermes = ventesTermes;
	}

	public boolean iseCommerce() {
		return eCommerce;
	}

	public void seteCommerce(boolean eCommerce) {
		this.eCommerce = eCommerce;
	}

	public boolean isMultiDepot() {
		return multiDepot;
	}

	public void setMultiDepot(boolean multiDepot) {
		this.multiDepot = multiDepot;
	}

	public boolean isCdeCentrale() {
		return cdeCentrale;
	}

	public void setCdeCentrale(boolean cdeCentrale) {
		this.cdeCentrale = cdeCentrale;
	}

	public boolean isCdeLD() {
		return cdeLD;
	}

	public void setCdeLD(boolean cdeLD) {
		this.cdeLD = cdeLD;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getCodeIP() {
		return codeIP;
	}

	public void setCodeIP(String codeIP) {
		this.codeIP = codeIP;
	}

	public List<Depot> getDepot() {
		return depot;
	}

	public void setDepot(List<Depot> depot) {
		this.depot = depot;
	}
        
        
/*
	@Override
	public String toString() {
		return "PointDeVente [pvtCode=" + pvtCode + ", motDirecteur="
				+ motDirecteur + ", raisonSocialFournisseur="
				+ raisonSocialFournisseur + ", adresse1=" + adresse1
				+ ", adresse2=" + adresse2 + ", codePostal=" + codePostal
				+ ", ville=" + ville + ", region=" + region + ", pays=" + pays
				+ ", phoneNumber=" + phoneNumber + ", faxNumber=" + faxNumber
				+ ", numGerant=" + numGerant + ", nomGerant=" + nomGerant
				+ ", gestionPrixVente=" + gestionPrixVente
				+ ", typePointDeVente=" + typePointDeVente + ", ldapOU="
				+ ldapOU + ", demiGros=" + demiGros + ", ventesTermes="
				+ ventesTermes + ", eCommerce=" + eCommerce + ", multiDepot="
				+ multiDepot + ", cdeCentrale=" + cdeCentrale + ", cdeLD="
				+ cdeLD + ", actif=" + actif + ", codeIP=" + codeIP
				+ ", depot=" + depot + "]";
	}
*/

    @Override
    public String toString() {
        return "PointDeVente{" + "pvtCode=" + pvtCode + ", motDirecteur=" + motDirecteur + ", raisonSocialFournisseur=" + raisonSocialFournisseur + ", phoneNumber=" + phoneNumber + ", numGerant=" + numGerant + ", nomGerant=" + nomGerant + ", gestionPrixVente=" + gestionPrixVente + ", typePointDeVente=" + typePointDeVente + ", actif=" + actif + ", codeIP=" + codeIP + '}';
    }
	
}
