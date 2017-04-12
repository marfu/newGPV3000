/**
 * 
 */
package ci.prosuma.prosumagpv.entity.util.conf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "sql_request")
public class SqlRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long id;

	@Column(unique = true,nullable = false)
	private String libelle;

	@Column(length = 4000)
	private String requete;

	public SqlRequest() {
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
		final SqlRequest other = (SqlRequest) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
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

	public String getRequete() {
		return requete;
	}

	public void setRequete(String requete) {
		this.requete = requete;
	}

}
