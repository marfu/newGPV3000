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
//@Table(name = "PARAM_TAUX_TVA")
public class TauxTVA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TAUX_TVA_PK")
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;

	@Column(name = "TAUX",nullable = false)
	private float taux;


	public TauxTVA() {
	}

	@Override
	public int hashCode() {
		return getCode().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getCode() == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TauxTVA other = (TauxTVA) obj;
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

	public float getTaux() {
		return taux;
	}

	public void setTaux(float taux) {
		this.taux = taux;
	}

}
