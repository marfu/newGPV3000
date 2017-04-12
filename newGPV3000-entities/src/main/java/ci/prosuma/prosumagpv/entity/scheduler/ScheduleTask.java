/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity.scheduler;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.BeforeCompletion;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;

/**
 *
 * @author tagsergi
 */

//@Entity
public class ScheduleTask implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    private String jndiName;
    
    private String description;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @PrePersist
    public void processing(){
        this.createDate = new Date();
    }
    
}
