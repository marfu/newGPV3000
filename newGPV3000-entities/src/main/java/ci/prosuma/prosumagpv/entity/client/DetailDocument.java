//package ci.prosuma.prosumagpv.entity.client;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.NotFound;
//import org.hibernate.annotations.NotFoundAction;
//
//import ci.prosuma.prosumagpv.entity.Article;
//
//@Entity
//@Table(name = "DETAIL_ACHAT")
//public class DetailDocument implements Serializable{
//	
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "DETAIL_ACHAT_PK")
//	private long id;
//	
//	@ManyToOne
//	@NotFound(action=NotFoundAction.IGNORE)
//	@JoinColumns(value= {
//			@JoinColumn(name = "CODE_ARTICLE_FK", referencedColumnName = "CODE_ARTICLE_PK"),			
//			@JoinColumn(name = "CODE_MAGASIN_PK", referencedColumnName = "CODE_MAGASIN_PK")			
//	})
//	private Article article;
//	
//	@Column(name = "QTE")
//	private float qte;
//	
//	@Column(name = "TAUX_REMISE")
//	private float tauxremise;
//	
//	@Column(name = "PRIX_VENTE")
//	private int prixVente;
//	
//	@Column(name = "PRIX_REVIENS")
//	private int prixReviens;
//
//	@Column(name = "PRIX_VENTE_HT")
//	private int prixVenteHT;
//	
//	@Column(name = "PRIX_REVIENS_HT")
//	private int prixReviensHT;
//	
//	@Column(name = "TYPE_LIGNE")
//	private int typeLigne;
//	
//	public DetailDocument() {
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
//		final DetailDocument other = (DetailDocument) obj;
//		if (getId() != other.getId()) {
//			return false;
//		}
//		return true;
//	}
//	
//	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Article getArticle() {
//		return article;
//	}
//
//	public void setArticle(Article article) {
//		this.article = article;
//	}
//
//	public float getQte() {
//		return qte;
//	}
//
//	public void setQte(float qte) {
//		this.qte = qte;
//	}
//
//	public float getTauxremise() {
//		return tauxremise;
//	}
//
//	public void setTauxremise(float tauxremise) {
//		this.tauxremise = tauxremise;
//	}
//
//	public int getPrixVente() {
//		return prixVente;
//	}
//
//	public void setPrixVente(int prixVente) {
//		this.prixVente = prixVente;
//	}
//
//	public int getPrixReviens() {
//		return prixReviens;
//	}
//
//	public void setPrixReviens(int prixReviens) {
//		this.prixReviens = prixReviens;
//	}
//
//	public int getPrixVenteHT() {
//		return prixVenteHT;
//	}
//
//	public void setPrixVenteHT(int prixVenteHT) {
//		this.prixVenteHT = prixVenteHT;
//	}
//
//	public int getPrixReviensHT() {
//		return prixReviensHT;
//	}
//
//	public void setPrixReviensHT(int prixReviensHT) {
//		this.prixReviensHT = prixReviensHT;
//	}
//
//	public int getTypeLigne() {
//		return typeLigne;
//	}
//
//	public void setTypeLigne(int typeLigne) {
//		this.typeLigne = typeLigne;
//	}
//	
//	
//
//}
