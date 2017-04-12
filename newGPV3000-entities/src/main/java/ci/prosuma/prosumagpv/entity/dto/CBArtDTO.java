package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;

import ci.prosuma.prosumagpv.entity.GenCode;

@SuppressWarnings("serial")
public class CBArtDTO implements Serializable {

	private GenCode gc;

	private String numArticle;

	private String codeMag;

	public CBArtDTO() {
	}

	public GenCode getGc() {
		return gc;
	}

	public void setGc(GenCode gc) {
		this.gc = gc;
	}

	public String getNumArticle() {
		return numArticle;
	}

	public void setNumArticle(String numArticle) {
		this.numArticle = numArticle;
	}

	public String getCodeMag() {
		return codeMag;
	}

	public void setCodeMag(String codeMag) {
		this.codeMag = codeMag;
	}

}
