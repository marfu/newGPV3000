/**
 * 
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author montan
 *
 */
@Entity
@Table(name = "T_OFFRE_TARIFAIRE")
public class TOffreTarifaire implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOFF_ID")
    private long offreId;
    
    @Column(name = "TOFF_TITRE")
    private String offTitre;
    
    @Column(name = "TOFF_DATE_CREATE")
   	@Temporal(TemporalType.TIMESTAMP)
   	private Date offDateCreate;
       
    @Column(name = "TOFF_DATE_MODIF")
   	@Temporal(TemporalType.TIMESTAMP)
   	private Date offDateModif;
       

	public long getOffreId() {
		return offreId;
	}

	public void setOffreId(long offreId) {
		this.offreId = offreId;
	}

	public String getOffTitre() {
		return offTitre;
	}

	public void setOffTitre(String offTitre) {
		this.offTitre = offTitre;
	}

	public Date getOffDateCreate() {
		return offDateCreate;
	}

	public void setOffDateCreate(Date offDateCreate) {
		this.offDateCreate = offDateCreate;
	}

	public Date getOffDateModif() {
		return offDateModif;
	}

	public void setOffDateModif(Date offDateModif) {
		this.offDateModif = offDateModif;
	}

	@Override
	public String toString() {
		return "TOffreTarifaire [offreId=" + offreId + ", offTitre=" + offTitre + ", offDateCreate=" + offDateCreate
				+ ", offDateModif=" + offDateModif + "]";
	}

}
