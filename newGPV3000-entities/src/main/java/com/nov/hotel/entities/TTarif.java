/**
 * 
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "T_TARIF")
public class TTarif implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TTARIF_ID")
    private long tarifId;
    
    @ManyToOne
   	@JoinColumn(name = "TTARIF_OFF_ID", referencedColumnName = "TOFF_ID")
    private TOffreTarifaire offre;
    
    @ManyToOne
   	@JoinColumn(name = "TTARIF_CH_CATEGORIE", referencedColumnName = "TCAT_CHAMBRE_ID")
    private TCategorieChambre chCategorie;
    
    @Column(name = "TTARIF_PRIX")
	private double TTARIF_PRIX;

	public TOffreTarifaire getOffre() {
		return offre;
	}

	public void setOffre(TOffreTarifaire offre) {
		this.offre = offre;
	}

	public TCategorieChambre getChCategorie() {
		return chCategorie;
	}

	public void setChCategorie(TCategorieChambre chCategorie) {
		this.chCategorie = chCategorie;
	}

	public double getTTARIF_PRIX() {
		return TTARIF_PRIX;
	}

	public void setTTARIF_PRIX(double tTARIF_PRIX) {
		TTARIF_PRIX = tTARIF_PRIX;
	}

	@Override
	public String toString() {
		return "TTarif [offre=" + offre + ", chCategorie=" + chCategorie + ", TTARIF_PRIX=" + TTARIF_PRIX + "]";
	}


}
