package ci.prosuma.prosumagpv.entity.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeClient;

/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "PARAM_CATEGORIE_CLIENT")
public class CategorieClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CATEGORIE_CLIENT_PK")
	private String categorie;

	@Column(name = "LIBELLE",nullable = false)
	private String libelle;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_CLIENT")
	private TypeClient typeClient;



	public CategorieClient() {
	}

	public CategorieClient(String categorie, String libelle
			) {
		super();
		this.categorie = categorie;
		this.libelle = libelle;
	}

	@Override
	public int hashCode() {
		return getLibelle().hashCode();
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
		final CategorieClient other = (CategorieClient) obj;
		if (!getLibelle().equals(other.getLibelle())) {
			return false;
		}
		return true;
	}

	public TypeClient getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(TypeClient typeClient) {
		this.typeClient = typeClient;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



}
