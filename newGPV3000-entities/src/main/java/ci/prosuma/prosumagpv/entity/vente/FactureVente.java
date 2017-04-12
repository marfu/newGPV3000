///**
// * 
// */
//package ci.prosuma.prosumagpv.entity.vente;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.SensDocument;
//
///**
// * @author AKHDAR Zoul
// * 
// */
//@Entity
//@Table(name = "facturevente")
//public class FactureVente implements Serializable {
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
//
//	@Temporal(TemporalType.DATE)
//	private Date dateCommande;
//
//	@Temporal(TemporalType.DATE)
//	private Date dateLivraison;
//
//	@Temporal(TemporalType.DATE)
//	private Date dateFacture;
//
//	private boolean mouvementFinancier;
//
//	@Enumerated(EnumType.STRING)
//	private SensDocument sensDocument;
//
//	@Temporal(TemporalType.DATE)
//	private Date debutValiditer;
//
//	@Temporal(TemporalType.DATE)
//	private Date finValiditer;
//
//	private boolean soumisTVA;
//
//	private boolean soumisASDI;
//
//	private double montantBrutHT;
//
//	private double montantRemise;
//
//	private double montantNetHT;
//
//	private double montantTVA;
//
//	private double montantEscompte;
//
//	private double montantNetTTCBaseASDI;
//
//	private double montantASDI;
//
//	private double montantNetAPayer;
//
//	private double montantHTauPR;
//
//	private boolean solder;
//
//	private boolean actif;
//
//	private boolean deleted;
//
//	@OneToMany(mappedBy = "facture")
//	private List<Reglement> reglements;
//
//	public FactureVente() {
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
//		final FactureVente other = (FactureVente) obj;
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
//
//	public Date getDateCommande() {
//		return dateCommande;
//	}
//
//	public void setDateCommande(Date dateCommande) {
//		this.dateCommande = dateCommande;
//	}
//
//	public Date getDateLivraison() {
//		return dateLivraison;
//	}
//
//	public void setDateLivraison(Date dateLivraison) {
//		this.dateLivraison = dateLivraison;
//	}
//
//	public Date getDateFacture() {
//		return dateFacture;
//	}
//
//	public void setDateFacture(Date dateFacture) {
//		this.dateFacture = dateFacture;
//	}
//
//	public boolean isMouvementFinancier() {
//		return mouvementFinancier;
//	}
//
//	public void setMouvementFinancier(boolean mouvementFinancier) {
//		this.mouvementFinancier = mouvementFinancier;
//	}
//
//	public SensDocument getSensDocument() {
//		return sensDocument;
//	}
//
//	public void setSensDocument(SensDocument sensDocument) {
//		this.sensDocument = sensDocument;
//	}
//
//	public Date getDebutValiditer() {
//		return debutValiditer;
//	}
//
//	public void setDebutValiditer(Date debutValiditer) {
//		this.debutValiditer = debutValiditer;
//	}
//
//	public Date getFinValiditer() {
//		return finValiditer;
//	}
//
//	public void setFinValiditer(Date finValiditer) {
//		this.finValiditer = finValiditer;
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
//	public boolean isSoumisASDI() {
//		return soumisASDI;
//	}
//
//	public void setSoumisASDI(boolean soumisASDI) {
//		this.soumisASDI = soumisASDI;
//	}
//
//	public double getMontantBrutHT() {
//		return montantBrutHT;
//	}
//
//	public void setMontantBrutHT(double montantBrutHT) {
//		this.montantBrutHT = montantBrutHT;
//	}
//
//	public double getMontantRemise() {
//		return montantRemise;
//	}
//
//	public void setMontantRemise(double montantRemise) {
//		this.montantRemise = montantRemise;
//	}
//
//	public double getMontantNetHT() {
//		return montantNetHT;
//	}
//
//	public void setMontantNetHT(double montantNetHT) {
//		this.montantNetHT = montantNetHT;
//	}
//
//	public double getMontantTVA() {
//		return montantTVA;
//	}
//
//	public void setMontantTVA(double montantTVA) {
//		this.montantTVA = montantTVA;
//	}
//
//	public double getMontantEscompte() {
//		return montantEscompte;
//	}
//
//	public void setMontantEscompte(double montantEscompte) {
//		this.montantEscompte = montantEscompte;
//	}
//
//	public double getMontantNetTTCBaseASDI() {
//		return montantNetTTCBaseASDI;
//	}
//
//	public void setMontantNetTTCBaseASDI(double montantNetTTCBaseASDI) {
//		this.montantNetTTCBaseASDI = montantNetTTCBaseASDI;
//	}
//
//	public double getMontantASDI() {
//		return montantASDI;
//	}
//
//	public void setMontantASDI(double montantASDI) {
//		this.montantASDI = montantASDI;
//	}
//
//	public double getMontantNetAPayer() {
//		return montantNetAPayer;
//	}
//
//	public void setMontantNetAPayer(double montantNetAPayer) {
//		this.montantNetAPayer = montantNetAPayer;
//	}
//
//	public double getMontantHTauPR() {
//		return montantHTauPR;
//	}
//
//	public void setMontantHTauPR(double montantHTauPR) {
//		this.montantHTauPR = montantHTauPR;
//	}
//
//	public boolean isSolder() {
//		return solder;
//	}
//
//	public void setSolder(boolean solder) {
//		this.solder = solder;
//	}
//
//	public boolean isActif() {
//		return actif;
//	}
//
//	public void setActif(boolean actif) {
//		this.actif = actif;
//	}
//
//	public boolean isDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(boolean deleted) {
//		this.deleted = deleted;
//	}
//
//	public List<Reglement> getReglements() {
//		return reglements;
//	}
//
//	public void setReglements(List<Reglement> reglements) {
//		this.reglements = reglements;
//	}
//
//}
