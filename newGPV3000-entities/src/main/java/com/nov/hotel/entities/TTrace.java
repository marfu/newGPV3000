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
@Table(name = "T_TRACE")
public class TTrace implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRACE_ID")
    private long traceId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TRACE_TYPE")
    private TypeTrace traceType;
    
    @Column(name = "TRACE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
	private Date traceDate;
    
    @Column(name = "TRACE_DESC")
	private String traceDesc;

    @ManyToOne
    private TUtilisateur traceUser;

	public long getTraceId() {
		return traceId;
	}

	public void setTraceId(long traceId) {
		this.traceId = traceId;
	}



	public Date getTraceDate() {
		return traceDate;
	}

	public void setTraceDate(Date traceDate) {
		this.traceDate = traceDate;
	}

	public TUtilisateur getTraceUser() {
		return traceUser;
	}

	public void setTraceUser(TUtilisateur traceUser) {
		this.traceUser = traceUser;
	}

	public TypeTrace getTraceType() {
		return traceType;
	}

	public void setTraceType(TypeTrace traceType) {
		this.traceType = traceType;
	}

	public String getTraceDesc() {
		return traceDesc;
	}

	public void setTraceDesc(String traceDesc) {
		this.traceDesc = traceDesc;
	}

	@Override
	public String toString() {
		return "TTrace [traceId=" + traceId + ", traceType=" + traceType + ", traceDate=" + traceDate + ", traceDesc="
				+ traceDesc + ", traceUser=" + traceUser + "]";
	}

}
