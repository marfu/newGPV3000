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
//import ci.prosuma.prosumagpv.entity.client.Client;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.SensDocument;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeVente;
//import ci.prosuma.prosumagpv.entity.util.ModeReglement;
//import ci.prosuma.prosumagpv.entity.util.TypeDepot;
//
///**
// * @author AKHDAR Zoul
// * 
// */
//@Entity
//@Table(name = "entete_bon_livraison_vente")
//public class EnteteBonLivraisonVente implements Serializable {
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
//	@JoinColumn(name = "client_fk", referencedColumnName = "CLIENT_PK")
//	private Client client;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateCommande;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateLivraison;
//
//	private TypeDepot depot;
//
//	private SensDocument sensDocument;
//
//	private boolean mouvementFinancier;
//
//	private String  vendeur;
//
//	private ModeReglement modeReglement;
//
//	private boolean soumisTVA;
//
//	private boolean soumisASDI;
//
//	private float tauxRemiseSurHT;
//
//	private TypeVente typeVente;
//
//	private boolean facturer;
//
//	private FactureVente factureVente;
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
//		final EnteteBonLivraisonVente other = (EnteteBonLivraisonVente) obj;
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
//	public Client getClient() {
//		return client;
//	}
//
//	public void setClient(Client client) {
//		this.client = client;
//	}
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
//	public TypeDepot getDepot() {
//		return depot;
//	}
//
//	public void setDepot(TypeDepot depot) {
//		this.depot = depot;
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
//	public boolean isMouvementFinancier() {
//		return mouvementFinancier;
//	}
//
//	public void setMouvementFinancier(boolean mouvementFinancier) {
//		this.mouvementFinancier = mouvementFinancier;
//	}
//
//	public String getVendeur() {
//		return vendeur;
//	}
//
//	public void setVendeur(String vendeur) {
//		this.vendeur = vendeur;
//	}
//
//	public ModeReglement getModeReglement() {
//		return modeReglement;
//	}
//
//	public void setModeReglement(ModeReglement modeReglement) {
//		this.modeReglement = modeReglement;
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
//	public float getTauxRemiseSurHT() {
//		return tauxRemiseSurHT;
//	}
//
//	public void setTauxRemiseSurHT(float tauxRemiseSurHT) {
//		this.tauxRemiseSurHT = tauxRemiseSurHT;
//	}
//
//	public TypeVente getTypeVente() {
//		return typeVente;
//	}
//
//	public void setTypeVente(TypeVente typeVente) {
//		this.typeVente = typeVente;
//	}
//
//	public boolean isFacturer() {
//		return facturer;
//	}
//
//	public void setFacturer(boolean facturer) {
//		this.facturer = facturer;
//	}
//
//	public FactureVente getFactureVente() {
//		return factureVente;
//	}
//
//	public void setFactureVente(FactureVente factureVente) {
//		this.factureVente = factureVente;
//	}
//
//}
