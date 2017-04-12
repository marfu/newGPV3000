/**
 * 
 */
package ci.prosuma.prosumagpv.entity.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDeDepot;

/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "DEPOT")
public class Depot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "DEPOT_PK")
	private long id;

	@Column(name = "CODE",nullable = false)
	private String code;

	

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;

	@Column(name = "SI_DEPOT_PRINCIPAL")
	private boolean depotPrincipale;

	@Column(name = "SI_ACTIF")
	private boolean actif;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_DE_DEPOT")
	private TypeDeDepot typeDeDepot;

	public Depot() {
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) getId();
		return result;
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
		final Depot other = (Depot) obj;
		if (getId() != other.getId()) {
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

	

	public boolean isDepotPrincipale() {
		return depotPrincipale;
	}

	public void setDepotPrincipale(boolean depotPrincipale) {
		this.depotPrincipale = depotPrincipale;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String mytoString() {
		return "Depot [id=" + id + ", code=" + code + ", libelle=" + libelle
				+ ", depotPrincipale=" + depotPrincipale + ", actif=" + actif
				+ ", typeDeDepot=" + typeDeDepot + "]";
	}

}
