package ci.prosuma.prosumagpv.entity.dto;

import java.math.BigDecimal;

public class CAFamilleDTO {
	
	private String codeSecteur;
	
	private String codeRayon;
	
	private String codeFamille;
	
	private String libFamille;
	
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

	public String getCodeRayon() {
		return codeRayon;
	}

	public void setCodeRayon(String codeRayon) {
		this.codeRayon = codeRayon;
	}

	public String getCodeFamille() {
		return codeFamille;
	}

	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}

	public String getLibFamille() {
		return libFamille;
	}

	public void setLibFamille(String libFamille) {
		this.libFamille = libFamille;
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
	
	

}
