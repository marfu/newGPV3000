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
@Table(name = "T_ARTICLE")
public class TArticle implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TART_ID")
    private long artId;
    
    @Column(name = "TART_NOM")
    private String artNom;
    
    
    @Column(name = "TART_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date artDateCreate;
    
    @Column(name = "TART_DATE_MODIF")
	@Temporal(TemporalType.TIMESTAMP)
	private Date artDateModif;
    
    @ManyToOne
   	@JoinColumn(name = "TART_SERVICE", referencedColumnName = "TSCE_ID")
    private TService artService;
    
    @Column(name = "TART_PRIX")
    private double artPrix;

  
    @Column(name = "TART_TAUX")
    private int artTaux;

    public long getArtId() {
        return artId;
    }

    public void setArtId(long artId) {
        this.artId = artId;
    }

    public String getArtNom() {
        return artNom;
    }

    public void setArtNom(String artNom) {
        this.artNom = artNom;
    }

    public Date getArtDateCreate() {
        return artDateCreate;
    }

    public void setArtDateCreate(Date artDateCreate) {
        this.artDateCreate = artDateCreate;
    }

    public Date getArtDateModif() {
        return artDateModif;
    }

    public void setArtDateModif(Date artDateModif) {
        this.artDateModif = artDateModif;
    }

    public TService getArtService() {
        return artService;
    }

    public void setArtService(TService artService) {
        this.artService = artService;
    }

    public double getArtPrix() {
        return artPrix;
    }

    public void setArtPrix(double artPrix) {
        this.artPrix = artPrix;
    }
      public int getArtTaux() {
        return artTaux;
    }

    public void setArtTaux(int artTaux) {
        this.artTaux = artTaux;
    }

    @Override
    public String toString() {
        return "TArticle{" + "artId=" + artId + ", artNom=" + artNom + ", artDateCreate=" + artDateCreate + ", artDateModif=" + artDateModif + ", artService=" + artService + ", artPrix=" + artPrix + ", artTaux=" + artTaux + '}';
    }
    

    
    


}