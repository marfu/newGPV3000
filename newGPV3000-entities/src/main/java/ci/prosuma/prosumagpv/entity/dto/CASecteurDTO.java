package ci.prosuma.prosumagpv.entity.dto;

import java.math.BigDecimal;

public class CASecteurDTO {
	
	private String codeSecteur;
	
	private String libSecteur;
	
	private BigDecimal prixFacturation;
	
	private BigDecimal prixVente;
	
	private BigDecimal marge;
	
	private Float pourcentage;

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}

	public String getLibSecteur() {
		return libSecteur;
	}

	public void setLibSecteur(String libSecteur) {
		this.libSecteur = libSecteur;
	}

	public BigDecimal getPrixFacturation() {
		return prixFacturation;
	}

	public void setPrixFacturation(BigDecimal prixFacturation) {
		this.prixFacturation = prixFacturation;
	}

	public BigDecimal getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(BigDecimal prixVente) {
		this.prixVente = prixVente;
	}

	public BigDecimal getMarge() {
		return marge;
	}

	public void setMarge(BigDecimal marge) {
		this.marge = marge;
	}

	public Float getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Float pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Override
	public String toString() {
		return "CASecteurDTO [codeSecteur=" + codeSecteur + ", libSecteur="
				+ libSecteur + ", prixFacturation=" + prixFacturation
				+ ", prixVente=" + prixVente + ", marge=" + marge
				+ ", pourcentage=" + pourcentage + "]";
	}
	
	
}
