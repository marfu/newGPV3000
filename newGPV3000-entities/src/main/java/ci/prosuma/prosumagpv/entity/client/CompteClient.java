//package ci.prosuma.prosumagpv.entity.client;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//@Entity
//@Table(name = "COMPTE_CLIENT")
//public class CompteClient implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name = "COMPTE_PK",unique = true , nullable = false)
//	private long id;
//
//	@Column(name = "SOUMIS_ASDI")
//	private boolean soumisASDI;
//
//	@Column(name = "SOUMIS_TVA")
//	private boolean soumisTVA;
//
//	@Column(name = "SOLDE")
//	private float soldeDuCompte;
//	
//
//	@Column(name = "ENCOURS_MAXIMUM")
//	private long encourMaximum;
//
//	@Column(name = "TAUX_ESCOMPTE_SUR_TTC")
//	private float tauxEscompteSurTTC;
//
//
//
//
//	@OneToMany
//	@JoinTable(name = "TABLE_LIAISON_COMPTE_DOCUMENT", joinColumns = @JoinColumn(name = "COMPTE_FK", referencedColumnName = "COMPTE_PK"), inverseJoinColumns = @JoinColumn(name = "DOCUMENT_FK", referencedColumnName = "DOCUMENT_PK"))
//	private List<EnteteDocument> documents;
//
//	
//	@OneToOne
//	@JoinColumn(name = "CLIENT_PRI_FK", referencedColumnName = "CLIENT_PK", nullable = true)
//	private Client clientPrincipal;
//
//	@OneToOne
//	@JoinColumns(value = { @JoinColumn(name = "CODE_BANQUE_FK", referencedColumnName = "CODE_BANQUE_PK", nullable = true),
//			@JoinColumn(name = "CODE_GUICHET_FK", referencedColumnName = "CODE_GUICHET_PK", nullable = true),
//			@JoinColumn(name = "NUM_COMPTE_FK", referencedColumnName = "NUM_COMPTE_PK", nullable = true) })
//	private CompteBancaire compteBancaire;
//
//	@OneToMany
//	@JoinTable(name = "TABLE_LIAISON_COMPTE_CLIENTS", joinColumns = @JoinColumn(name = "COMPTE_FK", referencedColumnName = "COMPTE_PK"), inverseJoinColumns = @JoinColumn(name = "CLIENT_FK", referencedColumnName = "CLIENT_PK"))
//	private List<Client> clients;
//
//	@OneToMany
//	@JoinTable(name = "TABLE_LIAISON_COMPTE_INCIDENT", joinColumns = @JoinColumn(name = "COMPTE_FK", referencedColumnName = "COMPTE_PK"), inverseJoinColumns = @JoinColumn(name = "INCIDENT_FK", referencedColumnName = "INCIDENT_PK"))
//	private List<Incident> incidents;
//
//	@Column(name = "ACTIF")
//	private boolean enabled;
//
//	@Column(name = "INCIDENT_ENCOURS")
//	private boolean incidentEnCours;
//	
//	
//	@Column(name = "MAG_ORIGINE", length = 4, nullable = false)
//	private String magCreation;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "DATE_CREATION")
//	private Date dateCreation;
//
//	@Column(name = "UTILISATEUR_CREA", length = 100)
//	private String userCreation;
//	
//	@Column(name = "TYPE", length = 3)
//	private int type;
//
//	@Column(name = "NOTES")
//	private String notes;
//	
//	@Column(name = "TERMES")
//	private boolean paiementTermes;
//	
//	@Column(name = "CHEQUES")
//	private boolean paiementCheques;
//
//	public CompteClient() {
//	}
//
//	@Override
//	public int hashCode() {
//		final int PRIME = 31;
//		int result = 1;
//		result = PRIME * result + (int) getId();
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		final CompteClient other = (CompteClient) obj;
//		if (getId() != other.getId()) {
//			return false;
//		}
//		return true;
//	}
//
//	
//	
//	
//	public String getMagCreation() {
//		return magCreation;
//	}
//
//	public void setMagCreation(String magCreation) {
//		this.magCreation = magCreation;
//	}
//
//
//	public CompteBancaire getCompteBancaire() {
//		return compteBancaire;
//	}
//
//	public void setCompteBancaire(CompteBancaire compteBancaire) {
//		this.compteBancaire = compteBancaire;
//	}
//
//
//	public List<Client> getClients() {
//		return clients;
//	}
//
//	public void setClients(List<Client> clients) {
//		this.clients = clients;
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
//	public boolean isSoumisASDI() {
//		return soumisASDI;
//	}
//
//	public void setSoumisASDI(boolean soumisASDI) {
//		this.soumisASDI = soumisASDI;
//	}
//
//	public boolean isSoumisTVA() {
//		return soumisTVA;
//	}
//
//	public void setSoumisTVA(boolean soumisTVA) {
//		this.soumisTVA = soumisTVA;
//	}
//
//	public long getEncourMaximum() {
//		return encourMaximum;
//	}
//
//	public void setEncourMaximum(long encourMaximum) {
//		this.encourMaximum = encourMaximum;
//	}
//
//	public float getTauxEscompteSurTTC() {
//		return tauxEscompteSurTTC;
//	}
//
//	public void setTauxEscompteSurTTC(float tauxEscompteSurTTC) {
//		this.tauxEscompteSurTTC = tauxEscompteSurTTC;
//	}
//
//
//
//	public List<EnteteDocument> getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(List<EnteteDocument> documents) {
//		this.documents = documents;
//	}
//
//	public List<Incident> getIncidents() {
//		return incidents;
//	}
//
//	public void setIncidents(List<Incident> incidents) {
//		this.incidents = incidents;
//	}
//
//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	public boolean isIncidentEnCours() {
//		return incidentEnCours;
//	}
//
//	public void setIncidentEnCours(boolean incidentEnCours) {
//		this.incidentEnCours = incidentEnCours;
//	}
//
//	public String getNotes() {
//		return notes;
//	}
//
//	public void setNotes(String notes) {
//		this.notes = notes;
//	}
//
//	public float getSoldeDuCompte() {
//		return soldeDuCompte;
//	}
//
//	public void setSoldeDuCompte(float soldeDuCompte) {
//		this.soldeDuCompte = soldeDuCompte;
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
//	public Date getDateCreation() {
//		return dateCreation;
//	}
//
//	public void setDateCreation(Date dateCreation) {
//		this.dateCreation = dateCreation;
//	}
//
//	public int getType() {
//		return type;
//	}
//
//	public void setType(int type) {
//		this.type = type;
//	}
//
//	public Client getClientPrincipal() {
//		return clientPrincipal;
//	}
//
//	public void setClientPrincipal(Client clientPrincipal) {
//		this.clientPrincipal = clientPrincipal;
//	}
//
//
//	public boolean isPaiementTermes() {
//		return paiementTermes;
//	}
//
//	public void setPaiementTermes(boolean paiementTermes) {
//		this.paiementTermes = paiementTermes;
//	}
//
//	public boolean isPaiementCheques() {
//		return paiementCheques;
//	}
//
//	public void setPaiementCheques(boolean paiementCheques) {
//		this.paiementCheques = paiementCheques;
//	}
//	
//	
//
//}
