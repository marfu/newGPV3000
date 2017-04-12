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
//import javax.persistence.Lob;
//import javax.persistence.SecondaryTable;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.validator.Email;
//
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypePieceIdentite;
//
//@Entity
//@Table(name = "CLIENT")
//@SecondaryTable(name = "PHOTO")
//public class Client implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "CLIENT_PK")
//	private long id;
//
//	@Email
//	@Column(name = "EMAIL", length = 50)
//	private String email;
//
//	@Column(name = "NUM_TEL", length = 8)
//	private String numTel;
//	
//	@Column(name = "SEXE", length = 8)
//	private String sexe;
//
//	
//	
//
//	@Column(name = "CODE_MAG", length = 4)
//	private String magClient;
//
//	@Column(name = "NUM_CEL", length = 8)
//	private String numCell;
//
//	@Column(name = "NUM_FAX", length = 8)
//	private String numFax;
//
//	@Column(name = "NOTES", length = 2000)
//	private String notes;
//
//	@Column(name = "ADRESSE", length = 2000)
//	private String adresse;
//
//	@Column(name = "BP", length = 50)
//	private String bp;
//
//	@Column(name = "VILLE", length = 50)
//	private String ville;
//
//	@Column(name = "PAYS", length = 25)
//	private String pays;
//
//	@Column(name = "ACTIF")
//	private boolean enable;
//
//	@Column(name = "BLOQUER")
//	private boolean bloquer;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "DATE_CREATION")
//	private Date dateCreation;
//
//	@Column(name = "UTILISATEUR_CREA", length = 100)
//	private String userCreation;
//
//	@Column(name = "NOM_RAISON_SOCIAL")
//	private String nom;
//
//	@Column(name = "PRENOM", length = 100)
//	private String prenom;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "DATE_NAISSANCE")
//	private Date dateDeNaissance;
//
//	@Column(name = "LIEU_NAISSANCE", length = 100)
//	private String lieuDeNaissance;
//
//	@Column(name = "PROFESSION", length = 255)
//	private String profession;
//
//	@Lob
//	@Column(name = "PHOTO")
//	private byte[] photo;
//
//	@Column(name = "NATIONALITE", length = 100)
//	private String nationalite;
//
//	@Column(name = "NUM_PIECE", length = 100)
//	private String pieceIdentiter;
//
//	
//	@Column(name = "TYPE_PIECE")
//	private String typePiece;
//
//	@Column(name = "EMPLOYEUR", length = 255)
//	private String employeur;
//
//	@Column(name = "TEL_BUREAU", length = 8)
//	private String telBureau;
//
//	@Column(name = "ADRESSE_EMPLOYEUR", length = 255)
//	private String adresseEmployeur;
//
//	
//
//	@Column(name = "NUM_CC", length = 50)
//	private String numContribuable;
//
//	@Column(name = "NUM_RC", length = 50)
//	private String registreDeCommerce;
//
//	public Client() {
//	}
//
//	public String getNom() {
//		return nom;
//	}
//
//	public void setNom(String nom) {
//		this.nom = nom;
//	}
//
//	public String getPrenom() {
//		return prenom;
//	}
//
//	public void setPrenom(String prenom) {
//		this.prenom = prenom;
//	}
//
//	public Date getDateDeNaissance() {
//		return dateDeNaissance;
//	}
//
//	public void setDateDeNaissance(Date dateDeNaissance) {
//		this.dateDeNaissance = dateDeNaissance;
//	}
//
//	public String getLieuDeNaissance() {
//		return lieuDeNaissance;
//	}
//
//	public void setLieuDeNaissance(String lieuDeNaissance) {
//		this.lieuDeNaissance = lieuDeNaissance;
//	}
//
//	public String getProfession() {
//		return profession;
//	}
//
//	public void setProfession(String profession) {
//		this.profession = profession;
//	}
//
//	public byte[] getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(byte[] photo) {
//		this.photo = photo;
//	}
//
//	public String getNationalite() {
//		return nationalite;
//	}
//
//	public void setNationalite(String nationalite) {
//		this.nationalite = nationalite;
//	}
//
//	public String getPieceIdentiter() {
//		return pieceIdentiter;
//	}
//
//	public void setPieceIdentiter(String pieceIdentiter) {
//		this.pieceIdentiter = pieceIdentiter;
//	}
//
//	public String getTypePiece() {
//		return typePiece;
//	}
//
//	public void setTypePiece(String typePiece) {
//		this.typePiece = typePiece;
//	}
//
//	public String getEmployeur() {
//		return employeur;
//	}
//
//	public void setEmployeur(String employeur) {
//		this.employeur = employeur;
//	}
//
//	public String getTelBureau() {
//		return telBureau;
//	}
//
//	public void setTelBureau(String telBureau) {
//		this.telBureau = telBureau;
//	}
//
//	public String getAdresseEmployeur() {
//		return adresseEmployeur;
//	}
//
//	public void setAdresseEmployeur(String adresseEmployeur) {
//		this.adresseEmployeur = adresseEmployeur;
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
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getNumTel() {
//		return numTel;
//	}
//
//	public void setNumTel(String numTel) {
//		this.numTel = numTel;
//	}
//
//	public String getNumCell() {
//		return numCell;
//	}
//
//	public void setNumCell(String numCell) {
//		this.numCell = numCell;
//	}
//
//	public String getNumFax() {
//		return numFax;
//	}
//
//	public void setNumFax(String numFax) {
//		this.numFax = numFax;
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
//	public String getAdresse() {
//		return adresse;
//	}
//
//	public void setAdresse(String adresse) {
//		this.adresse = adresse;
//	}
//
//	public String getBp() {
//		return bp;
//	}
//
//	public void setBp(String bp) {
//		this.bp = bp;
//	}
//
//	public String getVille() {
//		return ville;
//	}
//
//	public void setVille(String ville) {
//		this.ville = ville;
//	}
//
//	public String getPays() {
//		return pays;
//	}
//
//	public void setPays(String pays) {
//		this.pays = pays;
//	}
//
//	public boolean isEnable() {
//		return enable;
//	}
//
//	public void setEnable(boolean enable) {
//		this.enable = enable;
//	}
//
//	public boolean isBloquer() {
//		return bloquer;
//	}
//
//	public void setBloquer(boolean bloquer) {
//		this.bloquer = bloquer;
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
//	public String getUserCreation() {
//		return userCreation;
//	}
//
//	public void setUserCreation(String userCreation) {
//		this.userCreation = userCreation;
//	}
//
//
//	public String getNumContribuable() {
//		return numContribuable;
//	}
//
//	public void setNumContribuable(String numContribuable) {
//		this.numContribuable = numContribuable;
//	}
//
//	public String getRegistreDeCommerce() {
//		return registreDeCommerce;
//	}
//
//	public void setRegistreDeCommerce(String registreDeCommerce) {
//		this.registreDeCommerce = registreDeCommerce;
//	}
//
//	
//
//	public String getSexe() {
//		return sexe;
//	}
//
//	public void setSexe(String sexe) {
//		this.sexe = sexe;
//	}
//
//	public String getMagClient() {
//		return magClient;
//	}
//
//	public void setMagClient(String magClient) {
//		this.magClient = magClient;
//	}
//
//	
//	
//
//}
