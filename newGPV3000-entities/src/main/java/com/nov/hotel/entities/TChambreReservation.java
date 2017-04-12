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
 * @author montan
 *
 */
@Entity
@Table(name = "T_CH_RESERVATION")
public class TChambreReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TCH_ID")
    private long chId;

    @ManyToOne
    @JoinColumn(name = "TCH_RES_CHAMBRE", referencedColumnName = "TCH_ID")
    private TChambre chambre;

    @ManyToOne
    @JoinColumn(name = "TCH_RES_RESERVATION", referencedColumnName = "TRES_ID")
    private TReservation reservation;

    @Column(name = "TCH_RES_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    
    @ManyToOne   	
    @JoinColumn(name = "TCH_RES_FORFAIT", referencedColumnName = "TOFF_ID")    
    private TOffreTarifaire forfait;      
    
    @Column(name = "TCH_RES_PRIX")	
    private double prix;

    
    @Column(name = "TCH_RES_DATE_DEBUT")	
    @Temporal(TemporalType.TIMESTAMP)	
    private Date dateDebut;        
    
    @Column(name = "TCH_RES_DATE_FIN")	
    @Temporal(TemporalType.TIMESTAMP)	
    private Date dateFin;
    
    
    
    
    public TChambre getChambre() {
        return chambre;
    }

    public void setChambre(TChambre chambre) {
        this.chambre = chambre;
    }

    public TReservation getReservation() {
        return reservation;
    }

    public void setReservation(TReservation reservation) {
        this.reservation = reservation;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public TOffreTarifaire getForfait() {
        return forfait;
    }

    public void setForfait(TOffreTarifaire forfait) {
        this.forfait = forfait;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    
    
    
    
    @Override
    public String toString() {
        return "TChambreReservation [chambre=" + chambre + ", reservation=" + reservation + ", dateCreate=" + dateCreate
                + "]";
    }

}
