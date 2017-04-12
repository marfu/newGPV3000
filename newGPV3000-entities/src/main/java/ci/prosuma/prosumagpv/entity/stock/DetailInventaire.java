/**
 *
 */
package ci.prosuma.prosumagpv.entity.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ci.prosuma.prosumagpv.entity.Article;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Serge AYEPI
 *
 */
//@Entity
//@Table(name = "INVENTAIRE_DETAIL")
public class DetailInventaire implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_INVENTAIRE_PK")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
        @JoinColumn(name = "CODE_ARTICLE_FK", referencedColumnName = "CODE_ARTICLE_PK"),
        @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"),})
    private Article article;

    @Column(name = "QTE_STOCK_THEORIQUE")
    private float qteStockTheorique;

    @Column(name = "QTE_MAGASIN")
    private float qteMagasin;

    @Column(name = "QTE_RESERVE")
    private float qteReserve;

    @Column(name = "A_GISER")
    private boolean giser;

    @Column(name = "PRIX_REVIENS_TTC_EN_COURS")
    private int prixReviensTTCEnCours;

    @Column(name = "PRIX_VENTE_TTC_EN_COURS")
    private int prixVenteTTCEnCours;

    @Column(name = "PRIX_REVIENS_HT_EN_COURS")
    private int prixReviensHTEnCours;

    @Column(name = "PRIX_VENTE_HT_EN_COURS")
    private int prixVenteHTEnCours;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTETE_INVENTAIRE_FK", referencedColumnName="ENTETE_INVENTAIRE_PK")
    private EnteteInventaire enteteInventaire;

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
        final DetailInventaire other = (DetailInventaire) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    public float getQteStockTheorique() {
        return qteStockTheorique;
    }

    public void setQteStockTheorique(float qteStockTheorique) {
        this.qteStockTheorique = qteStockTheorique;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public float getQteMagasin() {
        return qteMagasin;
    }

    public void setQteMagasin(float qteMagasin) {
        this.qteMagasin = qteMagasin;
    }

    public float getQteReserve() {
        return qteReserve;
    }

    public void setQteReserve(float qteReserve) {
        this.qteReserve = qteReserve;
    }

    public boolean isGiser() {
        return giser;
    }

    public void setGiser(boolean giser) {
        this.giser = giser;
    }

    public int getPrixReviensTTCEnCours() {
        return prixReviensTTCEnCours;
    }

    public void setPrixReviensTTCEnCours(int prixReviensTTCEnCours) {
        this.prixReviensTTCEnCours = prixReviensTTCEnCours;
    }

    public int getPrixVenteTTCEnCours() {
        return prixVenteTTCEnCours;
    }

    public void setPrixVenteTTCEnCours(int prixVenteTTCEnCours) {
        this.prixVenteTTCEnCours = prixVenteTTCEnCours;
    }

    public int getPrixReviensHTEnCours() {
        return prixReviensHTEnCours;
    }

    public void setPrixReviensHTEnCours(int prixReviensHTEnCours) {
        this.prixReviensHTEnCours = prixReviensHTEnCours;
    }

    public int getPrixVenteHTEnCours() {
        return prixVenteHTEnCours;
    }

    public void setPrixVenteHTEnCours(int prixVenteHTEnCours) {
        this.prixVenteHTEnCours = prixVenteHTEnCours;
    }

    public EnteteInventaire getEnteteInventaire() {
        return enteteInventaire;
    }

    public void setEnteteInventaire(EnteteInventaire enteteInventaire) {
        this.enteteInventaire = enteteInventaire;
    }
    
    

    public String mytoString() {
        return "DetailInventaire [id=" + id + ", article=" + article
                + ", qteStockTheorique=" + qteStockTheorique + ", qteMagasin="
                + qteMagasin + ", qteReserve=" + qteReserve + ", giser="
                + giser + ", prixReviensTTCEnCours=" + prixReviensTTCEnCours
                + ", prixVenteTTCEnCours=" + prixVenteTTCEnCours
                + ", prixReviensHTEnCours=" + prixReviensHTEnCours
                + ", prixVenteHTEnCours=" + prixVenteHTEnCours + "]";
    }

}
