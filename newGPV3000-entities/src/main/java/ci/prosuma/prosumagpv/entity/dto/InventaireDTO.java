package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InventaireDTO implements Serializable{

	
	private String codeArticle;
	
	private float qte;
	
	private String type;
	
	private long numInv;
	
	private boolean giser;
	
	public InventaireDTO() {
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public float getQte() {
		return qte;
	}

	public void setQte(float qte) {
		this.qte = qte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getNumInv() {
		return numInv;
	}

	public void setNumInv(long numInv) {
		this.numInv = numInv;
	}

	public boolean isGiser() {
		return giser;
	}

	public void setGiser(boolean giser) {
		this.giser = giser;
	}
	
	
	
}
