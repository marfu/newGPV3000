/**
 * 
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "T_SERVICE")
public class TService implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TSCE_ID")
    private long sceId;
    
    @Column(name = "SCE_NOM")
    private String sceNom;
    
    @Column(name = "SCE_IS_SERVICE")
    private Boolean isService;
    
    @Column(name = "TSCE_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sceDateCreate;
    
    @Column(name = "TDFACT_DATE_MODIF")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sceDateModif;
    
    @ManyToOne
   	@JoinColumn(name = "TSCE_PARENT_ID", referencedColumnName = "TSCE_ID")
    private TService sceParentId;
    
    @Column(name = "TSCE_PRIX")
    private double scePrix;
    
    @Column(name = "TSCE_VERIFICATION",unique=true)
    private String sceVerification;

	public long getSceId() {
		return sceId;
	}

	public void setSceId(long sceId) {
		this.sceId = sceId;
	}

	public String getSceNom() {
		return sceNom;
	}

	public void setSceNom(String sceNom) {
		this.sceNom = sceNom;
	}

	public Boolean getIsService() {
		return isService;
	}

	public void setIsService(Boolean isService) {
		this.isService = isService;
	}

	public Date getSceDateCreate() {
		return sceDateCreate;
	}

	public void setSceDateCreate(Date sceDateCreate) {
		this.sceDateCreate = sceDateCreate;
	}

	public Date getSceDateModif() {
		return sceDateModif;
	}

	public void setSceDateModif(Date sceDateModif) {
		this.sceDateModif = sceDateModif;
	}

	public TService getSceParentId() {
		return sceParentId;
	}

	public void setSceParentId(TService sceParentId) {
		this.sceParentId = sceParentId;
	}

	public double getScePrix() {
		return scePrix;
	}

	public void setScePrix(double scePrix) {
		this.scePrix = scePrix;
	}

    public String getSceVerification() {
        return sceVerification;
    }

    public void setSceVerification(String sceVerification) {
        this.sceVerification = sceVerification;
    }

        
        
        
	@Override
	public String toString() {
		return "TService [sceId=" + sceId + ", sceNom=" + sceNom + ", isService=" + isService + ", sceDateCreate="
				+ sceDateCreate + ", sceDateModif=" + sceDateModif + ", sceParentId=" + sceParentId + ", scePrix="
				+ scePrix + "]";
	}
    
}