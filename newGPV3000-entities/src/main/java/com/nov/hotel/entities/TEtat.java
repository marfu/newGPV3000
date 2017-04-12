/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ayepi
 */
@Entity
@Table(name = "T_ETAT")
public class TEtat implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "ETA_ID", length=3)
    private EtatEnum etaId;
    
    @Basic(optional = false)
    @Column(name = "ETA_LIBELLE")
    private String etaLibelle;
    
    @Basic(optional = false)
    @Column(name = "ETA_DESCRIPTION")
    private String etaDescription;

    public TEtat() {
    }

    public TEtat(EtatEnum etaId) {
        this.etaId = etaId;
    }

    public TEtat(EtatEnum etaId, String etaLibelle, String etaDescription) {
        this.etaId = etaId;
        this.etaLibelle = etaLibelle;
        this.etaDescription = etaDescription;
    }

    public EtatEnum getEtaId() {
        return etaId;
    }

    public void setEtaId(EtatEnum etaId) {
        this.etaId = etaId;
    }

    public String getEtaLibelle() {
        return etaLibelle;
    }

    public void setEtaLibelle(String etaLibelle) {
        this.etaLibelle = etaLibelle;
    }

    public String getEtaDescription() {
        return etaDescription;
    }

    public void setEtaDescription(String etaDescription) {
        this.etaDescription = etaDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etaId != null ? etaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEtat)) {
            return false;
        }
        TEtat other = (TEtat) object;
        if ((this.etaId == null && other.etaId != null) || (this.etaId != null && !this.etaId.equals(other.etaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.novatic.cadenas.entities.TEtat[ etaId=" + etaId + " ]";
    }
    
}
