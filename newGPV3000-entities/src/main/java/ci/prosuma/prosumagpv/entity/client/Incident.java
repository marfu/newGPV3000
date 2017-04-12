//package ci.prosuma.prosumagpv.entity.client;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatIncident;
//
//
//
//@Entity
//@Table(name = "INCIDENT")
//public class Incident implements Serializable {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "INCIDENT_PK")
//	private long id;
//	
//	@NotNull
//	@Column(name = "OBSERVATION")
//	private String observation;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "DATE_INCIDENT")
//	private Date dateIncident;
//	
//	@NotNull
//	@Column(name = "UTILISATEUR")
//	private String userCreation;
//	
//	@NotNull
//	@Column(name = "MAG")
//	private String mag;
//	
//	@NotNull
//	@Enumerated(EnumType.STRING)
//	@Column(name = "ETAT_INCIDENT")
//	private EtatIncident etatIncident;
//	
//	public Incident() {
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getObservation() {
//		return observation;
//	}
//
//	public void setObservation(String observation) {
//		this.observation = observation;
//	}
//
//	public Date getDateIncident() {
//		return dateIncident;
//	}
//
//	public void setDateIncident(Date dateIncident) {
//		this.dateIncident = dateIncident;
//	}
//
//	public String getUserCreation() {
//		return userCreation;
//	}
//
//	public void setUserCreation(String userCreation) {
//		this.userCreation = userCreation;
//	}
//
//	public String getMag() {
//		return mag;
//	}
//
//	public void setMag(String mag) {
//		this.mag = mag;
//	}
//
//	public EtatIncident getEtatIncident() {
//		return etatIncident;
//	}
//
//	public void setEtatIncident(EtatIncident etatIncident) {
//		this.etatIncident = etatIncident;
//	}
//	
//	
//
//}
