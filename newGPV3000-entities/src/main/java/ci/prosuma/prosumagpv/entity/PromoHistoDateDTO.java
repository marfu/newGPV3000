/**
 * 
 */
package ci.prosuma.prosumagpv.entity;

import java.util.Date;

/**
 * @author tagsergi
 *
 */
public class PromoHistoDateDTO {
	
	private Date dateDebutFacturation , dateDebutPromo, dateFinFacturation, dateFinPromo;
	
	
	

	public PromoHistoDateDTO() {
		super();
	}

	public Date getDateDebutFacturation() {
		return dateDebutFacturation;
	}

	public void setDateDebutFacturation(Date dateDebutFacturation) {
		this.dateDebutFacturation = dateDebutFacturation;
	}

	public Date getDateDebutPromo() {
		return dateDebutPromo;
	}

	public void setDateDebutPromo(Date dateDebutPromo) {
		this.dateDebutPromo = dateDebutPromo;
	}

	public Date getDateFinFacturation() {
		return dateFinFacturation;
	}

	public void setDateFinFacturation(Date dateFinFacturation) {
		this.dateFinFacturation = dateFinFacturation;
	}

	public Date getDateFinPromo() {
		return dateFinPromo;
	}

	public void setDateFinPromo(Date dateFinPromo) {
		this.dateFinPromo = dateFinPromo;
	}

	@Override
	public String toString() {
		return "PromoHistoDate [dateDebutFacturation=" + dateDebutFacturation
				+ ", dateDebutPromo=" + dateDebutPromo
				+ ", dateFinFacturation=" + dateFinFacturation
				+ ", dateFinPromo=" + dateFinPromo + "]";
	}
	
	

}
