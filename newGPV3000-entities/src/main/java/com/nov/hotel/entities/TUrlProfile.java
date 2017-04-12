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
@Table(name = "T_URL_PROFILE")
public class TUrlProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    private TProfileUtilisateur profile;
	
	@ManyToOne
    private TUrlUtilisateur url;
	
	private Long nbreUrl;

	public TProfileUtilisateur getProfile() {
		return profile;
	}

	public void setProfile(TProfileUtilisateur profile) {
		this.profile = profile;
	}

	public TUrlUtilisateur getUrl() {
		return url;
	}

	public void setUrl(TUrlUtilisateur url) {
		this.url = url;
	}

	public Long getNbreUrl() {
		return nbreUrl;
	}

	public void setNbreUrl(Long nbreUrl) {
		this.nbreUrl = nbreUrl;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TUrlProfile [id=" + id + ", profile=" + profile + ", url=" + url + ", nbreUrl=" + nbreUrl + "]";
	}

}
