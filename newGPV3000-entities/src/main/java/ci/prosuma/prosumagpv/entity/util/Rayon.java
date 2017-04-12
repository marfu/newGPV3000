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
//@Table(name = "PARAM_RAYON")
public class Rayon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name ="RAYON_PK")
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;

	@Column(name = "SECTEUR_FK",nullable = false)
	private String secteur;



	public Rayon() {
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
		final Rayon other = (Rayon) obj;
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

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur.trim();
	}

	
	
	public String getLibelle() {
		return libelle;
	}

	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	@Override
	public String toString() {
		return getCode() + " - " + getLibelle();
	}

	
	public String MytoString() {
		return "Rayon [code=" + code + ", libelle=" + libelle + ", secteur="
				+ secteur + "]";
	}
}
