/**
 * 
 */
package ci.prosuma.prosumagpv.entity.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "PARAM_SOUS_FAMILLE")
public class SousFamille implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SOUS_FAMILLE_PK",nullable = false)
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;

	@Column(name = "FAMILLE_FK",nullable = false)
	private String famille;

	@Column(name = "RAYON_FK",nullable = false)
	private String rayon;

	@Column(name = "SECTEUR_FK",nullable = false)
	private String secteur;

	public SousFamille() {
	}

	@Override
	public int hashCode() {
		return getCode().hashCode();
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
		final SousFamille other = (SousFamille) obj;
		if (!getCode().equals(other.getCode())) {
			return false;
		}
		return true;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.trim();
	}

	public String getRayon() {
		return rayon;
	}

	public void setRayon(String rayon) {
		this.rayon = rayon.trim();
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur.trim();
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille.trim();
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	public String MytoString() {
		return "SousFamille [code=" + code + ", libelle=" + libelle
				+ ", famille=" + famille + ", rayon=" + rayon + ", secteur="
				+ secteur + "]";
	}

	
}
