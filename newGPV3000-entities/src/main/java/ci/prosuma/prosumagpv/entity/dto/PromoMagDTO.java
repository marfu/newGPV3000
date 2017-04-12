package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;

import ci.prosuma.prosumagpv.entity.Promo;

@SuppressWarnings("serial")
public class PromoMagDTO implements Serializable {
	
	private Promo  promo;
	
	private String codeMagasin;
	
	public PromoMagDTO() {
	}

	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}

	public String getCodeMagasin() {
		return codeMagasin;
	}

	public void setCodeMagasin(String codeMagasin) {
		this.codeMagasin = codeMagasin;
	}
	
	

}
