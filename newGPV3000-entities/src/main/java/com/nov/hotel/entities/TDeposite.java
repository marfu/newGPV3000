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
 * @author user
 *
 */
@Entity
@Table(name = "T_DEPOSITE")
public class TDeposite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "T_DEP_ID")
    private long depId;

    @Column(name = "T_DEP_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "T_DEP_DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;

    @Column(name = "T_DEP_USER_CREATE")
    private String userCreate;

    @Column(name = "T_DEP_USER_MODIF")
    private String userModif;

    @Column(name = "T_DEP_MONTANT")
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "T_DEP_CLIENT", referencedColumnName = "TCLI_ID")
    private TClient client;

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
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

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "TDeposite [depId=" + depId + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", userCreate="
                + userCreate + ", userModif=" + userModif + ", montant=" + montant + ", client=" + client + "]";
    }

}
