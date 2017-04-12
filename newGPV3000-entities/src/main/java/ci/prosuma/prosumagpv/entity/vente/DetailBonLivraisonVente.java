///**
// * 
// */
//package ci.prosuma.prosumagpv.entity.vente;
//
//import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// * @author AKHDAR Zoul
// * 
// */
//
//@Entity
//@Table(name = "detail_bon_livraison_vente")
//public class DetailBonLivraisonVente implements Serializable {
//
//	public enum TypeVente {
//		VENTE_A_TERME, DEMI_GROS, COMMERCE_ELECTRONIQUE
//	}
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
//	private EnteteBonLivraisonVente bonLlivraisonVente;
//
//	// private GenCode genCode;
//
//	private float qteLivrer;
//
//	private float prixReviensUnitaireTTC;
//
//	private float prixVenteUnitaireTTC;
//
//	private float tauxTVAAppliquer;
//	private float tauxASDIAppliquer;
//
//	private double prixVenteTheorique;
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
//		final DetailBonLivraisonVente other = (DetailBonLivraisonVente) obj;
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
//	public EnteteBonLivraisonVente getBonLlivraisonVente() {
//		return bonLlivraisonVente;
//	}
//
//	public void setBonLlivraisonVente(EnteteBonLivraisonVente bonLlivraisonVente) {
//		this.bonLlivraisonVente = bonLlivraisonVente;
//	}
//
//	//
//	// public GenCode getGenCode() {
//	// return genCode;
//	// }
//	//
//	// public void setGenCode(GenCode genCode) {
//	// this.genCode = genCode;
//	// }
//
//	public float getQteLivrer() {
//		return qteLivrer;
//	}
//
//	public void setQteLivrer(float qteLivrer) {
//		this.qteLivrer = qteLivrer;
//	}
//
//	public float getPrixReviensUnitaireTTC() {
//		return prixReviensUnitaireTTC;
//	}
//
//	public void setPrixReviensUnitaireTTC(float prixReviensUnitaireTTC) {
//		this.prixReviensUnitaireTTC = prixReviensUnitaireTTC;
//	}
//
//	public float getPrixVenteUnitaireTTC() {
//		return prixVenteUnitaireTTC;
//	}
//
//	public void setPrixVenteUnitaireTTC(float prixVenteUnitaireTTC) {
//		this.prixVenteUnitaireTTC = prixVenteUnitaireTTC;
//	}
//
//	public float getTauxTVAAppliquer() {
//		return tauxTVAAppliquer;
//	}
//
//	public void setTauxTVAAppliquer(float tauxTVAAppliquer) {
//		this.tauxTVAAppliquer = tauxTVAAppliquer;
//	}
//
//	public float getTauxASDIAppliquer() {
//		return tauxASDIAppliquer;
//	}
//
//	public void setTauxASDIAppliquer(float tauxASDIAppliquer) {
//		this.tauxASDIAppliquer = tauxASDIAppliquer;
//	}
//
//	public double getPrixVenteTheorique() {
//		return prixVenteTheorique;
//	}
//
//	public void setPrixVenteTheorique(double prixVenteTheorique) {
//		this.prixVenteTheorique = prixVenteTheorique;
//	}
//
//}
