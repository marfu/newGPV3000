package com.nov.hotel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author montan
 */
@Entity
@Table(name = "T_STATU")
public class TStatu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "STATU_ID", length=10)
    private StatuEnum statuId;
    
    @Column(name = "STATU_LIBELLE")
    private String statuLibelle;
    
    @Column(name = "STATU_DESCRIPTION")
    private String statuDesc;

    public TStatu() {
    }

	public StatuEnum getStatuId() {
		return statuId;
	}

	public void setStatuId(StatuEnum statuId) {
		this.statuId = statuId;
	}

	public String getStatuLibelle() {
		return statuLibelle;
	}

	public void setStatuLibelle(String statuLibelle) {
		this.statuLibelle = statuLibelle;
	}

	public String getStatuDesc() {
		return statuDesc;
	}

	public void setStatuDesc(String statuDesc) {
		this.statuDesc = statuDesc;
	}
	
	public TStatu(StatuEnum statuId, String statuLibelle, String statuDesc) {
		super();
		this.statuId = statuId;
		this.statuLibelle = statuLibelle;
		this.statuDesc = statuDesc;
	}

	@Override
	public String toString() {
		return "TStatu [statuId=" + statuId + ", statuLibelle=" + statuLibelle + ", statuDesc=" + statuDesc + "]";
	}

}
