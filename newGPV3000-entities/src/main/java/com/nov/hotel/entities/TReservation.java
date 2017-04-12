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
@Table(name = "T_RESERVATION")
public class TReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRES_ID")
    private long resId;

    @Column(name = "TRES_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resDateCreate;

    @Column(name = "TRES_DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resDateModif;

    @Column(name = "TRES_NBRE_CHAMBRE")
    private long resNbreChambre;

    @Column(name = "TRES_MONTANT")
    private double resMontant;

    @Column(name = "TRES_DESCR", columnDefinition = "TEXT")
    private String resDescr;

    @Column(name = "TRES_NUM_RESERVATION")
    private String resNumReservation;

    @ManyToOne
    @JoinColumn(name = "TRES_CLIENT_ID", referencedColumnName = "TCLI_ID")
    private TClient resClient;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRES_ETAT", length = 50)
    private EtatReservationEnum etat;

    private String userCreation;

    private String userModif;

    private String userConfir;
    
    private String userCrloture;

    @Column(name = "TCH_RES_DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrive;

    @Column(name = "TCH_RES_DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepart;

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public Date getResDateCreate() {
        return resDateCreate;
    }

    public void setResDateCreate(Date resDateCreate) {
        this.resDateCreate = resDateCreate;
    }

    public Date getResDateModif() {
        return resDateModif;
    }

    public void setResDateModif(Date resDateModif) {
        this.resDateModif = resDateModif;
    }

    public long getResNbreChambre() {
        return resNbreChambre;
    }

    public void setResNbreChambre(long resNbreChambre) {
        this.resNbreChambre = resNbreChambre;
    }

    public double getResMontant() {
        return resMontant;
    }

    public void setResMontant(double resMontant) {
        this.resMontant = resMontant;
    }

    public String getResDescr() {
        return resDescr;
    }

    public void setResDescr(String resDescr) {
        this.resDescr = resDescr;
    }

    public String getResNumReservation() {
        return resNumReservation;
    }

    public void setResNumReservation(String resNumReservation) {
        this.resNumReservation = resNumReservation;
    }

    public TClient getResClient() {
        return resClient;
    }

    public void setResClient(TClient resClient) {
        this.resClient = resClient;
    }

    public EtatReservationEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatReservationEnum etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "TReservation [resId=" + resId + ", resDateCreate=" + resDateCreate + ", resDateModif=" + resDateModif
                + ", resNbreChambre=" + resNbreChambre + ", resMontant=" + resMontant + ", resDescr=" + resDescr
                + ", resNumReservation=" + resNumReservation + ", resClient=" + resClient + ", etat=" + etat + "]";
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }

    public String getUserCrloture() {
        return userCrloture;
    }

    public void setUserCrloture(String userCrloture) {
        this.userCrloture = userCrloture;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getUserConfir() {
        return userConfir;
    }

    public void setUserConfir(String userConfir) {
        this.userConfir = userConfir;
    }

    
    
}
