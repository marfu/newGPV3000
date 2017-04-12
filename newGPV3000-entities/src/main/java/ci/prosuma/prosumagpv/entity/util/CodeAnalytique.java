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
//@Table(name = "PARAM_CODE_ANALYTIQUE")
public class CodeAnalytique implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE_ANALYTIQUE_PK")
	private String codeAnalytique;

	@Column(name = "LIBELLE")
	private String libelle;

	


	public CodeAnalytique() {
	}

	public CodeAnalytique(String codeAnalytique, String libelle
			) {
		super();
		this.codeAnalytique = codeAnalytique;
		this.libelle = libelle;
		
		
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
		final CodeAnalytique other = (CodeAnalytique) obj;
		if (!getCodeAnalytique().equals(other.getCodeAnalytique())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return getCodeAnalytique().hashCode();
	}

	public String getCodeAnalytique() {
		return codeAnalytique;
	}

	public void setCodeAnalytique(String codeAnalytique) {
		this.codeAnalytique = codeAnalytique;
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
		return "CodeAnalytique [codeAnalytique=" + codeAnalytique
				+ ", libelle=" + libelle + "]";
	}

	*/

	

}
