/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marfu
 */
@Entity
@Table(name = "TMODE_PAIEMENT")
public class TModePaiment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TModePaiment_ID")
    private long id;

    @Column(name = "TModePaiment_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    
    @Column(name = "TModePaiment_libelle")
   
    private String libelleMode;

//    @ManyToOne
//   	@JoinColumn(name = "TFACT_CH_ID", referencedColumnName = "TCH_ID")
//    private TChambre chambre;
    @Column(name = "TModePaiment_USER_CREATE")
    private String userCreate;

    @Column(name = "TModePaiment_USER_MOD")
    private String userModif;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelleMode() {
        return libelleMode;
    }

    public void setLibelleMode(String libelleMode) {
        this.libelleMode = libelleMode;
    }

   

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }
    
    
    

}
