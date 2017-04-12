/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity.scheduler;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author tagsergi
 */

//@Entity
public class JobHistory implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    private JobInfo job;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    private boolean status;
    
    @Column(columnDefinition = "TEXT")
    private String errorMessage; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JobInfo getJob() {
        return job;
    }

    public void setJob(JobInfo job) {
        this.job = job;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
