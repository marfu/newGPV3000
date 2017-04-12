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
@Table(name = "T_UTILISATEUR")
public class TUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	private Long tentative;
	
	@Column(unique=true)
	private String msisdn;
	
	private String avatar;
	
	private String sexe;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateupdate;
	
	@ManyToOne
    private TProfileUtilisateur profile;

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTentative() {
		return tentative;
	}

	public void setTentative(Long tentative) {
		this.tentative = tentative;
	}

	public TProfileUtilisateur getProfile() {
		return profile;
	}

	public void setProfile(TProfileUtilisateur profile) {
		this.profile = profile;
	}

	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
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


	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "TUtilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password="
				+ password + ", tentative=" + tentative + ", msisdn=" + msisdn + ", avatar=" + avatar + ", sexe=" + sexe
				+ ", datecreation=" + datecreation + ", dateupdate=" + dateupdate + ", profile=" + profile + ", statut="
				+ statut + "]";
	}

}
