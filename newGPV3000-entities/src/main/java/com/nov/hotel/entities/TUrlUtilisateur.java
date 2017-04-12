/**
 * 
 */
package com.nov.hotel.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author montan
 *
 */

@Entity
@Table(name = "T_URL_UTILISATEUR")
public class TUrlUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String description;
	
	@ManyToOne
    private TApplicationUrl application;
	
	@ManyToOne
    private TStatu statut;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TStatu getStatut() {
		return statut;
	}
	public void setStatut(TStatu statut) {
		this.statut = statut;
	}
	public TApplicationUrl getApplication() {
		return application;
	}
	public void setApplication(TApplicationUrl application) {
		this.application = application;
	}
	@Override
	public String toString() {
		return "TUrlUtilisateur [id=" + id + ", url=" + url + ", description=" + description + ", application="
				+ application + ", statut=" + statut + "]";
	}
	
}
