package ci.prosuma.prosumagpv.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromoPvtRefPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CODE_MAGASIN_FK")
	private String pvt;

	@Column(name = "CODE_PROMO_FK")
	private String promo;

	public PromoPvtRefPK() {
	}

	public PromoPvtRefPK(String pvt, String promo) {
		super();
		this.pvt = pvt;
		this.promo = promo;
	}

	public String getPvt() {
		return pvt;
	}

	public void setPvt(String pvt) {
		this.pvt = pvt;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	@Override
	public int hashCode() {
		return (getPromo() + getPvt()).hashCode();
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
		final PromoPvtRefPK other = (PromoPvtRefPK) obj;
		if (!getPromo().equals(other.getPromo()) 
				|| !getPvt().equals(other.getPvt()) ) {
			return false;
		}
		return true;
	}

}
