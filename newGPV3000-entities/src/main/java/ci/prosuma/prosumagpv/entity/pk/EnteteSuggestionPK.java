package ci.prosuma.prosumagpv.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnteteSuggestionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CODE_MAGASIN_PK")
	private String pvtCode;

	@Column(name = "NUM_DOSSIER_PK")
	private String numDossier;
	


	public EnteteSuggestionPK() {
	}

	
	

	public EnteteSuggestionPK(String pvtCode, String numDossier) {
		super();
		this.pvtCode = pvtCode;
		this.numDossier = numDossier;
		
	}




	@Override
	public int hashCode() {
		return (getPvtCode() +  getNumDossier()).hashCode();
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
		final EnteteSuggestionPK other = (EnteteSuggestionPK) obj;
		if ( !getPvtCode().equals(other.getPvtCode())
				  || ! getNumDossier().equals(other.getNumDossier()) ) {
			return false;
		}
		return true;
	}




	public String getPvtCode() {
		return pvtCode;
	}




	public void setPvtCode(String pvtCode) {
		this.pvtCode = pvtCode;
	}




	public String getNumDossier() {
		return numDossier;
	}




	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}




	
	
	

}
