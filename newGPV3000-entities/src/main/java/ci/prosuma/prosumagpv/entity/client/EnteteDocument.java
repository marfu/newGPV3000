//package ci.prosuma.prosumagpv.entity.client;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.validator.NotNull;
//
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDocument;
//
//@Entity
//@Table(name = "ENTETE_DOCUMENT")
//public class EnteteDocument implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "ENTETE_DOCUMENT_PK")
//	private long id;
//
//	@Column(name = "CODE_MAGASIN_PK", nullable = false)
//	private String codeMagasin;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "DATE_DOCUMENT")
//	private Date dateDocument;
//
//	@Enumerated(EnumType.STRING)
//	private TypeDocument typeDocument;
//
//	@Column(name = "OBSERVATION")
//	@NotNull
//	private String observation;
//
//	@Column(name = "VALEUR_COMMANDE_PF")
//	private int valeurPF;
//
//	@Column(name = "VALEUR_COMMANDE_PF_HT")
//	private int valeurPFHT;
//
//	@Column(name = "VALEUR_COMMANDE_PV")
//	private int valeurPV;
//
//	@Column(name = "VALEUR_COMMANDE_PV_HT")
//	private int valeurPVHT;
//
//	@Column(name = "UTILISATEUR_CREATION")
//	private String userCreation;
//
//	@Column(name = "UTILISATEUR_CLOTURE")
//	private String userCloture;
//
//	@Column(name = "CLOTURER")
//	private boolean cloturer;
//
//	@Column(name = "RESTANT_DU")
//	private int restantDu;
//
//	@OneToMany
//	@JoinTable(name = "TABLE_LIAISON_DETAIL_ENTETE_DOCUMENT", joinColumns = @JoinColumn(name = "ENTETE_DOCUMENT_FK", referencedColumnName = "ENTETE_DOCUMENT_PK"), inverseJoinColumns = @JoinColumn(name = "DETAIL_DOCUMENT_FK", referencedColumnName = "DETAIL_DOCUMENT_PK"))
//	private List<DetailDocument> detailDocument;
//
//	public EnteteDocument() {
//	}
//
//	@Override
//	public int hashCode() {
//		return (getId() + "").hashCode();
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
//		final EnteteDocument other = (EnteteDocument) obj;
//		if (getId() != other.getId() || !getCodeMagasin().equals(other.getCodeMagasin())) {
//			return false;
//		}
//		return true;
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
//	public String getCodeMagasin() {
//		return codeMagasin;
//	}
//
//	public void setCodeMagasin(String codeMagasin) {
//		this.codeMagasin = codeMagasin;
//	}
//
//	public Date getDateDocument() {
//		return dateDocument;
//	}
//
//	public void setDateDocument(Date dateDocument) {
//		this.dateDocument = dateDocument;
//	}
//
//	public TypeDocument getTypeDocument() {
//		return typeDocument;
//	}
//
//	public void setTypeDocument(TypeDocument typeDocument) {
//		this.typeDocument = typeDocument;
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
//	public int getValeurPF() {
//		return valeurPF;
//	}
//
//	public void setValeurPF(int valeurPF) {
//		this.valeurPF = valeurPF;
//	}
//
//	public int getValeurPFHT() {
//		return valeurPFHT;
//	}
//
//	public void setValeurPFHT(int valeurPFHT) {
//		this.valeurPFHT = valeurPFHT;
//	}
//
//	public int getValeurPV() {
//		return valeurPV;
//	}
//
//	public void setValeurPV(int valeurPV) {
//		this.valeurPV = valeurPV;
//	}
//
//	public int getValeurPVHT() {
//		return valeurPVHT;
//	}
//
//	public void setValeurPVHT(int valeurPVHT) {
//		this.valeurPVHT = valeurPVHT;
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
//	public String getUserCloture() {
//		return userCloture;
//	}
//
//	public void setUserCloture(String userCloture) {
//		this.userCloture = userCloture;
//	}
//
//	public boolean isCloturer() {
//		return cloturer;
//	}
//
//	public void setCloturer(boolean cloturer) {
//		this.cloturer = cloturer;
//	}
//
//	public int getRestantDu() {
//		return restantDu;
//	}
//
//	public void setRestantDu(int restantDu) {
//		this.restantDu = restantDu;
//	}
//
//	public List<DetailDocument> getDetailDocument() {
//		return detailDocument;
//	}
//
//	public void setDetailDocument(List<DetailDocument> detailDocument) {
//		this.detailDocument = detailDocument;
//	}
//
//}
