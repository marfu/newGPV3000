///**
// * 
// */
//package ci.prosuma.prosumagpv.entity.vente;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
///**
// * @author AKHDAR Zoul
// * 
// */
//@Entity
//@Table(name = "reglement")
//public class Reglement implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//
//	@ManyToOne
//	@JoinColumn(name = "compte_fk", referencedColumnName = "id")
//	private CompteClient compte;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateRglement;
//
//	@ManyToOne
//	@JoinColumn(name = "facture_fk", referencedColumnName = "id")
//	private FactureVente facture;
//
//	// @ManyToOne
//	// @JoinColumn(name = "mode_reglmement_fk", referencedColumnName = "id")
//	// private ModeReglement modeReglement;
//
//	private double montantRegler;
//
//	private double montantNonImputer;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateEcheance;
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateRemiseBordereau;
//
//	private String observations;
//
//	public Reglement() {
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
//		final Reglement other = (Reglement) obj;
//		if (getId() != other.getId()) {
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
//	public CompteClient getCompte() {
//		return compte;
//	}
//
//	public void setCompte(CompteClient compte) {
//		this.compte = compte;
//	}
//
//	public Date getDateRglement() {
//		return dateRglement;
//	}
//
//	public void setDateRglement(Date dateRglement) {
//		this.dateRglement = dateRglement;
//	}
//
//	public double getMontantRegler() {
//		return montantRegler;
//	}
//
//	public void setMontantRegler(double montantRegler) {
//		this.montantRegler = montantRegler;
//	}
//
//	public double getMontantNonImputer() {
//		return montantNonImputer;
//	}
//
//	public void setMontantNonImputer(double montantNonImputer) {
//		this.montantNonImputer = montantNonImputer;
//	}
//
//	public Date getDateEcheance() {
//		return dateEcheance;
//	}
//
//	public void setDateEcheance(Date dateEcheance) {
//		this.dateEcheance = dateEcheance;
//	}
//
//	public Date getDateRemiseBordereau() {
//		return dateRemiseBordereau;
//	}
//
//	public void setDateRemiseBordereau(Date dateRemiseBordereau) {
//		this.dateRemiseBordereau = dateRemiseBordereau;
//	}
//
//	public String getObservations() {
//		return observations;
//	}
//
//	public void setObservations(String observations) {
//		this.observations = observations;
//	}
//
//	public FactureVente getFacture() {
//		return facture;
//	}
//
//	public void setFacture(FactureVente facture) {
//		this.facture = facture;
//	}
//
//}
