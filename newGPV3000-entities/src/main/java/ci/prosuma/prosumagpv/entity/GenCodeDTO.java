/**
 * 
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;

/**
 * @author tagsergi
 *
 */
public class GenCodeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String debutLigne;
	private String genCode;
	private Integer nullAscii1 ;
	private String tvaEnASCII;
	private Character euro ='\u20ac';
	private Integer codePromoENAscii;
	private String famille;
	private String premiereSeriedeZero="00000000";
	private String numbreArtcleLot;
	private String prixDevente;
	private String prixDuLot;
	private String articleLie="0000";
	private String designation;
	private String derniereSerieZero="0000000000";
	

	public GenCodeDTO() {
		
		super();
		euro ='\u20ac';
		nullAscii1 =0;
		derniereSerieZero="0000000000";
		articleLie="0000";
		premiereSeriedeZero="00000000";
		// TODO Auto-generated constructor stub
	}
	public String getDebutLigne() {
		return debutLigne;
	}
	public void setDebutLigne(String debutLigne) {
		this.debutLigne = debutLigne;
	}
	public String getGenCode() {
		return genCode;
	}
	public void setGenCode(String genCode) {
		this.genCode = genCode;
	}
	public Integer getNullAscii1() {
		return nullAscii1;
	}
	public void setNullAscii1(Integer nullAscii1) {
		this.nullAscii1 = nullAscii1;
	}
	public String getTvaEnASCII() {
		return tvaEnASCII;
	}
	public void setTvaEnASCII(String tvaEnASCII) {
		this.tvaEnASCII = tvaEnASCII;
	}
	public Character getEuro() {
		return euro;
	}
	public void setEuro(Character euro) {
		this.euro = euro;
	}
	public Integer getCodePromoENAscii() {
		return codePromoENAscii;
	}
	public void setCodePromoENAscii(Integer codePromoENAscii) {
		this.codePromoENAscii = codePromoENAscii;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public String getPremiereSeriedeZero() {
		return premiereSeriedeZero;
	}
	public void setPremiereSeriedeZero(String premiereSeriedeZero) {
		this.premiereSeriedeZero = premiereSeriedeZero;
	}
	public String getNumbreArtcleLot() {
		return numbreArtcleLot;
	}
	public void setNumbreArtcleLot(String numbreArtcleLot) {
		this.numbreArtcleLot = numbreArtcleLot;
	}
	public String getPrixDevente() {
		return prixDevente;
	}
	public void setPrixDevente(String prixDevente) {
		this.prixDevente = prixDevente;
	}
	public String getPrixDuLot() {
		return prixDuLot;
	}
	public void setPrixDuLot(String prixDuLot) {
		this.prixDuLot = prixDuLot;
	}
	public String getArticleLie() {
		return articleLie;
	}
	public void setArticleLie(String articleLie) {
		this.articleLie = articleLie;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDerniereSerieZero() {
		return derniereSerieZero;
	}
	public void setDerniereSerieZero(String derniereSerieZero) {
		this.derniereSerieZero = derniereSerieZero;
	}
	
	
	@Override
	public String toString() {
		return "GenCode [debutLigne=" + debutLigne + ", genCode=" + genCode + ", nullAscii1=" + nullAscii1
				+ ", tvaEnASCII=" + tvaEnASCII + ", euro=" + euro + ", codePromoENAscii=" + codePromoENAscii
				+ ", famille=" + famille + ", premiereSeriedeZero=" + premiereSeriedeZero + ", numbreArtcleLot="
				+ numbreArtcleLot + ", prixDevente=" + prixDevente + ", prixDuLot=" + prixDuLot + ", articleLie="
				+ articleLie + ", designation=" + designation + ", derniereSerieZero=" + derniereSerieZero + "]";
	}

}
