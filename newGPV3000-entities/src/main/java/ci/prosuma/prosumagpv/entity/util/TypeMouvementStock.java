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
//@Table(name = "PARAM_MOUVEMENT_STOCK")
public class TypeMouvementStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TYPE_MOUVEMENT_PK")
	private String code;
	
	

	@Column(name ="ORIGINE_MOUVEMENT_FK")
	private String origineMouvement;

	@Column(name = "NUM_CODE_MOUVEMENT")
	private String numCodeMouvement;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SENS")
	private String sens;

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
		final TypeMouvementStock other = (TypeMouvementStock) obj;
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

	


	public String getOrigineMouvement() {
		return origineMouvement;
	}

	public void setOrigineMouvement(String origineMouvement) {
		this.origineMouvement = origineMouvement;
	}

	public String getNumCodeMouvement() {
		return numCodeMouvement;
	}

	public void setNumCodeMouvement(String numCodeMouvement) {
		this.numCodeMouvement = numCodeMouvement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}
/*
	@Override
	public String toString() {
		return "TypeMouvementStock [code=" + code + ", origineMouvement="
				+ origineMouvement + ", numCodeMouvement=" + numCodeMouvement
				+ ", description=" + description + ", sens=" + sens + "]";
	}
*/
	
	
	
	
}