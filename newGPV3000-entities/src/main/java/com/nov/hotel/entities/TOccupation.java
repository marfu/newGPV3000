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
import javax.persistence.UniqueConstraint;

/**
 * @author montan
 *
 */
@Entity
//@Table(name = "T_OCCUPATION")
@Table(name = "T_OCCUPATION", uniqueConstraints = { @UniqueConstraint(columnNames = { "TOCC_NUM_RESERVATION", "TOCC_CLI_ID", "TOCC_CH_ID"})})
public class TOccupation implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOCC_ID")
    private long occId;
    
    @ManyToOne
   	@JoinColumn(name = "TOCC_CH_ID", referencedColumnName = "TCH_ID")
    private TChambre chambre;
    
    @ManyToOne
   	@JoinColumn(name = "TOCC_CLI_ID", referencedColumnName = "TCLI_ID")
    private TClient client;
    
    @Column(name = "TOCC_NUM_RESERVATION")
    private String numReservation;
    
    @Column(name = "TOCC_FACTURE_ID")
    private long factureId;
    
    @Column(name = "TOCC_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date occDateCreate;
    
    @Column(name = "TOCC_DATE_ARR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date occDateArr;
    
    @Column(name = "TOCC_DATE_DEP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date occDateDep;

	public TChambre getChambre() {
		return chambre;
	}

	public void setChambre(TChambre chambre) {
		this.chambre = chambre;
	}

	public TClient getClient() {
		return client;
	}

	public void setClient(TClient client) {
		this.client = client;
	}

	public Date getOccDateCreate() {
		return occDateCreate;
	}

	public void setOccDateCreate(Date occDateCreate) {
		this.occDateCreate = occDateCreate;
	}

	public Date getOccDateDep() {
		return occDateDep;
	}

	public void setOccDateDep(Date occDateDep) {
		this.occDateDep = occDateDep;
                
	}

    public long getOccId() {
        return occId;
    }

    public void setOccId(long occId) {
        this.occId = occId;
    }
        
        

    public String getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }

    public long getFactureId() {
        return factureId;
    }

    public void setFactureId(long factureId) {
        this.factureId = factureId;
    }

    public Date getOccDateArr() {
        return occDateArr;
    }

    public void setOccDateArr(Date occDateArr) {
        this.occDateArr = occDateArr;
    }

        
        
        
        
	@Override
	public String toString() {
		return "TOccupation [chambre=" + chambre + ", client=" + client + ", occDateCreate=" + occDateCreate
				+ ", occDateDep=" + occDateDep + "]";
	}
    
    

}
