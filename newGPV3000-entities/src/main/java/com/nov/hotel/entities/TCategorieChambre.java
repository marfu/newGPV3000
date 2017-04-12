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
@Table(name = "T_CAT_CHAMBRE")
public class TCategorieChambre implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TCAT_CHAMBRE_ID")
    private long catChambreId;
    
    @Column(name = "TCAT_CHAMBRE_LIB")
    private String catChambreLib;
    
    @Column(name = "TCAT_CHAMBRE_DESCR")
    private String catChambreDesc;
    
    @Column(name = "TCAT_CHAMBRE_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date catChambreDateCrea;
    
    @Column(name = "TCAT_CHAMBRE_DATE_MODIF")
   	@Temporal(TemporalType.TIMESTAMP)
   	private Date catChambreDateModif;
    
   @ManyToOne   	
   @JoinColumn(name = "STATU_ID", referencedColumnName = "STATU_ID")    
   private TStatu cliStatut;
    
   
//    private String cliStatut;

	public long getCatChambreId() {
		return catChambreId;
	}

	public void setCatChambreId(long catChambreId) {
		this.catChambreId = catChambreId;
	}

	public String getCatChambreLib() {
		return catChambreLib;
	}

	public void setCatChambreLib(String catChambreLib) {
		this.catChambreLib = catChambreLib;
	}

	public String getCatChambreDesc() {
		return catChambreDesc;
	}

	public void setCatChambreDesc(String catChambreDesc) {
		this.catChambreDesc = catChambreDesc;
	}

	public Date getCatChambreDateCrea() {
		return catChambreDateCrea;
	}

	public void setCatChambreDateCrea(Date catChambreDateCrea) {
		this.catChambreDateCrea = catChambreDateCrea;
	}

	public Date getCatChambreDateModif() {
		return catChambreDateModif;
	}

	public void setCatChambreDateModif(Date catChambreDateModif) {
		this.catChambreDateModif = catChambreDateModif;
	}

    public TStatu getCliStatut() {
        return cliStatut;
    }

//	public TStatu getCliStatut() {
//		return cliStatut;
//	}
//
//	public void setCliStatut(TStatu cliStatut) {
//		this.cliStatut = cliStatut;
//	}
    public void setCliStatut(TStatu cliStatut) {
        this.cliStatut = cliStatut;
    }

    @Override
    public String toString() {
        return "TCategorieChambre{" + "catChambreId=" + catChambreId + ", catChambreLib=" + catChambreLib + ", catChambreDesc=" + catChambreDesc + ", catChambreDateCrea=" + catChambreDateCrea + ", catChambreDateModif=" + catChambreDateModif + '}';
    }

	
	
    
}
