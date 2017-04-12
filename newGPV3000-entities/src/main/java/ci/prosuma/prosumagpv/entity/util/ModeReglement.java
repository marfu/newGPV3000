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


import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeReglement;

/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "PARAM_MODE_REGLEMENT")
public class ModeReglement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, name ="MODE_REGLEMENT_PK")
	private String code;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_REGLEMENT")
	private TypeReglement typeReglement;

	@Column(name = "NBR_JOUR")
	private int nbrJour;



	public ModeReglement() {
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
		final ModeReglement other = (ModeReglement) obj;
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



	public TypeReglement getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(TypeReglement typeReglement) {
		this.typeReglement = typeReglement;
	}

	public int getNbrJour() {
		return nbrJour;
	}

	public void setNbrJour(int nbrJour) {
		this.nbrJour = nbrJour;
	}

}
