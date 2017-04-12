/**
 * 
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ci.prosuma.prosumagpv.entity.pk.PromoPvtRefPK;

/**
 * @author Prophyl.com
 * 
 */
//@Entity
//@Table(name = "TABLE_LIAISON_PROMOTION_PVT")
//@IdClass(value = PromoPvtRefPK.class)
public class LienPromoPvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE_MAGASIN_FK", length = 11)
	private String pvt;

	@Id
	@Column(name = "CODE_PROMO_FK", length = 7)
	private String promo;


	public LienPromoPvt() {
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

	public boolean needUpdate(LienPromoPvt laf) {
		if (laf == null) {
			return true;
		}
		if (getPvt() != null && laf.getPvt() != null
				&& !getPvt().equals(laf.getPvt()))
			return true;
		if (getPromo() != null && laf.getPromo() != null
				&& !getPromo().equals(laf.getPromo()))
			return true;

		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pvt == null) ? 0 : pvt.hashCode());
		result = prime * result + ((promo == null) ? 0 : promo.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LienPromoPvt other = (LienPromoPvt) obj;
		if (pvt == null) {
			if (other.pvt != null)
				return false;
		} else if (!pvt.equals(other.pvt))
			return false;
		if (promo == null) {
			if (other.promo != null)
				return false;
		} else if (!promo.equals(other.promo))
			return false;
		return true;
	}
	
	

}
