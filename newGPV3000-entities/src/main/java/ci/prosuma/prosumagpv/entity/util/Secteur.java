/**
 * 
 */
package ci.prosuma.prosumagpv.entity.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "PARAM_SECTEUR")
//@NamedQueries({
//		@NamedQuery(name = "Secteur.getAllSecteur", query = "SELECT sec FROM Secteur sec "),
//		@NamedQuery(name = "Secteur.getSecteurByCode", query = "SELECT sec FROM Secteur sec WHERE sec.code=:codeSecteur"),
//		@NamedQuery(name = "Secteur.getDesignationByCode", query = "SELECT sec.libelle FROM Secteur sec WHERE sec.code=:codeSecteur")
//
//})
public class Secteur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Id
	@Column(unique = true, name = "SECTEUR_PK")
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;



	public Secteur() {
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
		final Secteur other = (Secteur) obj;
		
		if(other == null || other.getCode() == null || other.getCode().equals(""))
			return false;
		
		if(this == null || getCode() == null)
			return false;
		
		if (!getCode().equals(other.getCode())) {
			return false;
		}
		return true;
	}

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	public String MytoString() {
		return "Secteur [code=" + code + ", libelle=" + libelle + "]";
	}

	@Override
	public String toString() {
		return "Secteur [code=" + code + ", libelle=" + libelle + "]";
	}

	
	
	

}
