package ci.prosuma.prosumagpv.entity.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDeDepot;

/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "PARAM_DEPOT")
public class TypeDepot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, name = "DEPOT_PK")
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;


	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_DEPOT")
	private TypeDeDepot typeDeDepot;

	public TypeDepot() {
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
		final TypeDepot other = (TypeDepot) obj;
		if (!getCode().equals(other.getCode())) {
			return false;
		}
		return true;
	}

	public TypeDeDepot getTypeDeDepot() {
		return typeDeDepot;
	}

	public void setTypeDeDepot(TypeDeDepot typeDeDepot) {
		this.typeDeDepot = typeDeDepot;
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
/*
	@Override
	public String toString() {
		return "TypeDepot [code=" + code + ", libelle=" + libelle
				+ ", typeDeDepot=" + typeDeDepot + "]";
	}
*/
	
}
