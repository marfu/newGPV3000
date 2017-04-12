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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author montan
 *
 */

@Entity
@Table(name = "T_PROFILE_UTILISATEUR")
public class TProfileUtilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateupdate;
	
	@ManyToOne
    private TStatu statut;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public Date getDateupdate() {
		return dateupdate;
	}
	public void setDateupdate(Date dateupdate) {
		this.dateupdate = dateupdate;
	}
	public TStatu getStatut() {
		return statut;
	}
	public void setStatut(TStatu statut) {
		this.statut = statut;
	}
	@Override
	public String toString() {
		return "TProfileUtilisateur [id=" + id + ", nom=" + nom + ", description=" + description + ", datecreation="
				+ datecreation + ", dateupdate=" + dateupdate + ", statut=" + statut + "]";
	}
	
}
