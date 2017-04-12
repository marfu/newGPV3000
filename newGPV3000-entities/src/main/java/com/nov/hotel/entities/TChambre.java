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
@Table(name = "T_CHAMBRE")
public class TChambre implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TCH_ID")
    private long chId;
    
    @Column(name = "TCH_LIB")
    private String chLib;
    
    @Column(name = "TCH_NUMERO_CHAMBRE")
    private String chNumeroChambre;
    
    @Column(name = "TCH_DATE_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date chDateCreate;
    
    @Column(name = "TCH_DATE_MODIF")
	@Temporal(TemporalType.TIMESTAMP)
	private Date chDateModif;
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TCH_ETAT", length=10)
    private EtatChambreEnum etat;
    
    @ManyToOne
   	@JoinColumn(name = "TCH_CATEGORIE", referencedColumnName = "TCAT_CHAMBRE_ID")
    private TCategorieChambre chCategorie;

	public long getChId() {
		return chId;
	}

	public void setChId(long chId) {
		this.chId = chId;
	}

	public String getChLib() {
		return chLib;
	}

	public void setChLib(String chLib) {
		this.chLib = chLib;
	}

	public Date getChDateCreate() {
		return chDateCreate;
	}

	public void setChDateCreate(Date chDateCreate) {
		this.chDateCreate = chDateCreate;
	}

	public Date getChDateModif() {
		return chDateModif;
	}

	public void setChDateModif(Date chDateModif) {
		this.chDateModif = chDateModif;
	}

	

	public EtatChambreEnum getEtat() {
		return etat;
	}

	public void setEtat(EtatChambreEnum etat) {
		this.etat = etat;
	}

	public TCategorieChambre getChCategorie() {
		return chCategorie;
	}

	public void setChCategorie(TCategorieChambre chCategorie) {
		this.chCategorie = chCategorie;
	}

	
	public String getChNumeroChambre() {
		return chNumeroChambre;
	}

	public void setChNumeroChambre(String chNumeroChambre) {
		this.chNumeroChambre = chNumeroChambre;
	}

	@Override
	public String toString() {
		return "TChambre [chId=" + chId + ", chLib=" + chLib + ", chNumeroChambre=" + chNumeroChambre
				+ ", chDateCreate=" + chDateCreate + ", chDateModif=" + chDateModif + ", etat=" + etat
				+ ", chCategorie=" + chCategorie + "]";
	}

}
