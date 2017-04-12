/**
 * 
 */
package com.nov.hotel.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author montan
 *
 */
@Entity
@Table(name = "T_REMISE")
public class TRemise implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TREMISE_ID")
    private long remiseId;
    
    @Column(name = "TREMISE_LIB")
    private String remiseLib;
    
    @Column(name = "TREMISE_TAUX")
    private Double remiseTaux;

	public long getRemiseId() {
		return remiseId;
	}

	public void setRemiseId(long remiseId) {
		this.remiseId = remiseId;
	}

	public String getRemiseLib() {
		return remiseLib;
	}

	public void setRemiseLib(String remiseLib) {
		this.remiseLib = remiseLib;
	}

    public Double getRemiseTaux() {
        return remiseTaux;
    }

    public void setRemiseTaux(Double remiseTaux) {
        this.remiseTaux = remiseTaux;
    }

	

	@Override
	public String toString() {
		return "TRemise [remiseId=" + remiseId + ", remiseLib=" + remiseLib + ", remiseTaux=" + remiseTaux + "]";
	}
    
}