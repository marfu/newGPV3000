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
@Table(name = "T_APPLICATION_URL")
public class TApplicationUrl implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAPP_URL_ID")
    private long appUrlId;
    
    @Column(name = "TAPP_URL_NOM")
    private String appUrlNom;
    
    @Column(name = "TAPP_URL_DESCR")
    private String appUrlDescr;
    
    @Column(name = "TAPP_URL_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appUrlDateCreate;
    
    @Column(name = "TAPP_URL_DATE_MODIF")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appUrlDateModif;
    
    @ManyToOne
   	@JoinColumn(name = "STATU_ID", referencedColumnName = "STATU_ID")
    private TStatu appUrlStatut;

	public long getAppUrlId() {
		return appUrlId;
	}

	public void setAppUrlId(long appUrlId) {
		this.appUrlId = appUrlId;
	}

	public String getAppUrlNom() {
		return appUrlNom;
	}

	public void setAppUrlNom(String appUrlNom) {
		this.appUrlNom = appUrlNom;
	}

	public String getAppUrlDescr() {
		return appUrlDescr;
	}

	public void setAppUrlDescr(String appUrlDescr) {
		this.appUrlDescr = appUrlDescr;
	}

	public Date getAppUrlDateCreate() {
		return appUrlDateCreate;
	}

	public void setAppUrlDateCreate(Date appUrlDateCreate) {
		this.appUrlDateCreate = appUrlDateCreate;
	}

	public Date getAppUrlDateModif() {
		return appUrlDateModif;
	}

	public void setAppUrlDateModif(Date appUrlDateModif) {
		this.appUrlDateModif = appUrlDateModif;
	}

	public TStatu getAppUrlStatut() {
		return appUrlStatut;
	}

	public void setAppUrlStatut(TStatu appUrlStatut) {
		this.appUrlStatut = appUrlStatut;
	}

	@Override
	public String toString() {
		return "ApplicationUrl [appUrlId=" + appUrlId + ", appUrlNom=" + appUrlNom + ", appUrlDescr=" + appUrlDescr
				+ ", appUrlDateCreate=" + appUrlDateCreate + ", appUrlDateModif=" + appUrlDateModif + ", appUrlStatut="
				+ appUrlStatut + "]";
	}
}
