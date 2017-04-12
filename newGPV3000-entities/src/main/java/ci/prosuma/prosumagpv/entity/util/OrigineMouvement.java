/**
 * 
 */
package ci.prosuma.prosumagpv.entity.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeOrigineMouvement;

/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "PARAM_OPRIGINE_MOUVEMENT")
public class OrigineMouvement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORIGINE_MOUVEMENT_PK")
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;


	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_ORIGINE_MOUVEMENT")
	private TypeOrigineMouvement typeOrigineMouvement;

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
		final OrigineMouvement other = (OrigineMouvement) obj;
		if (other == null || other.getCode() == null || getCode() == null)
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


	public TypeOrigineMouvement getTypeOrigineMouvement() {
		return typeOrigineMouvement;
	}

	public void setTypeOrigineMouvement(
			TypeOrigineMouvement typeOrigineMouvement) {
		this.typeOrigineMouvement = typeOrigineMouvement;
	}

	
	public String myToString() {
		return "OrigineMouvement [code=" + code + ", libelle=" + libelle
				+ ", typeOrigineMouvement=" + typeOrigineMouvement + "]";
	}

	
}