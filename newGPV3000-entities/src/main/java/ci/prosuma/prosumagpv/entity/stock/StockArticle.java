/**
 * 
 */
package ci.prosuma.prosumagpv.entity.stock;

import ci.prosuma.prosumagpv.entity.Article;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author AKHDAR Zoul
 * 
 */
//@Entity
//@Table(name = "STOCK_ARTICLE")
public class StockArticle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCK_ARTICLE_PK")
	private long id;

	@ManyToOne
        @Fetch(FetchMode.JOIN)
	private Depot depot;

	@Column(name = "QTE_COMPTABLE")
	private float qteComptable;

	@Column(name = "DATE_DERNIERE_SORTIE")
	@Temporal(TemporalType.DATE)
	private Date dateDerniereSortie;

	@Column(name = "DATE_DERNIERE_ENTRER")
	@Temporal(TemporalType.DATE)
	private Date dateDerniereEntrer;

	@Column(name = "DATE_DERNIER_INVENTAIRE")
	@Temporal(TemporalType.DATE)
	private Date dateDernierInventaire;

	@Column(name = "SEUIL_MINIMUM")
	private float seuilMin;

	@Column(name = "SEUIL_MAXIMUM")
	private float seuilMax;
        
        @OneToOne(fetch=FetchType.LAZY, mappedBy="stock")
        private Article article;

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) getId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StockArticle other = (StockArticle) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}

	
	public float getSeuilMin() {
		return seuilMin;
	}

	public void setSeuilMin(float seuilMin) {
		this.seuilMin = seuilMin;
	}

	public float getSeuilMax() {
		return seuilMax;
	}

	public void setSeuilMax(float seuilMax) {
		this.seuilMax = seuilMax;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}



	public float getQteComptable() {
		return qteComptable;
	}

	public void setQteComptable(float qteComptable) {
		this.qteComptable = qteComptable;
	}

	

	public Date getDateDerniereSortie() {
		return dateDerniereSortie;
	}

	public void setDateDerniereSortie(Date dateDerniereSortie) {
		this.dateDerniereSortie = dateDerniereSortie;
	}

	public Date getDateDerniereEntrer() {
		return dateDerniereEntrer;
	}

	public void setDateDerniereEntrer(Date dateDerniereEntrer) {
		this.dateDerniereEntrer = dateDerniereEntrer;
	}

	public Date getDateDernierInventaire() {
		return dateDernierInventaire;
	}

	public void setDateDernierInventaire(Date dateDernierInventaire) {
		this.dateDernierInventaire = dateDernierInventaire;
	}

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

	public String MyToString() {
		return "StockArticle [id=" + id + ", depot=" + depot
				+ ", qteComptable=" + qteComptable + ", dateDerniereSortie="
				+ dateDerniereSortie + ", dateDerniereEntrer="
				+ dateDerniereEntrer + ", dateDernierInventaire="
				+ dateDernierInventaire + ", seuilMin=" + seuilMin
				+ ", seuilMax=" + seuilMax + "]";
	}

	
}
