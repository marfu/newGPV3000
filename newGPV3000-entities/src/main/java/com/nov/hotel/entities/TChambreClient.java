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
@Table(name = "T_CH_CLIENT")
public class TChambreClient implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TCH_CLI_ID")
    private long tarifId;
    
    @ManyToOne
   	@JoinColumn(name = "TCH_CLI_CLIENT", referencedColumnName = "TCLI_ID")
    private TClient client;
    
    @ManyToOne
   	@JoinColumn(name = "TCH_CLI_CHAMBRE", referencedColumnName = "TCH_ID")
    private TChambre chambre;
    
    @Column(name = "TCH_CLI_DATE_ARR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateArr;
    
    @Column(name = "TCH_CLI_DATE_DEP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDep;

	public long getTarifId() {
		return tarifId;
	}

	public void setTarifId(long tarifId) {
		this.tarifId = tarifId;
	}

	public TClient getClient() {
		return client;
	}

	public void setClient(TClient client) {
		this.client = client;
	}

	public TChambre getChambre() {
		return chambre;
	}

	public void setChambre(TChambre chambre) {
		this.chambre = chambre;
	}

	public Date getDateArr() {
		return dateArr;
	}

	public void setDateArr(Date dateArr) {
		this.dateArr = dateArr;
	}

	public Date getDateDep() {
		return dateDep;
	}

	public void setDateDep(Date dateDep) {
		this.dateDep = dateDep;
	}

	@Override
	public String toString() {
		return "TChambreClient [tarifId=" + tarifId + ", client=" + client + ", chambre=" + chambre + ", dateArr="
				+ dateArr + ", dateDep=" + dateDep + "]";
	}

}
