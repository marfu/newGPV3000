/**
 *
 */
package ci.prosuma.prosumagpv.entity.commande;

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
//@Table(name = "BON_COMMANDE_FOURNISSSEUR_DETAIL")
public class DetailBonCommandeFournisseur implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DETAIL_COMMANDE_PK")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
        @JoinColumn(name = "CODE_ARTICLE_FK", referencedColumnName = "CODE_ARTICLE_PK"),
        @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"),})
    private Article article;

    @Column(name = "QTE_COMMANDER")
    private float qteCommande;

    @Column(name = "QTE_RECEPTIONNER")
    private float qteReceptionner;

    @Column(name = "QTE_FACTURER")
    private float qteFacture;

    @Column(name = "PF_UNITAIRE_TTC")
    private float pfUnitaireTTC;

    @Column(name = "PV_UNITAIRE_TTC")
    private float pvUnitaireTTC;

    @Column(name = "VALEUR_MARGE")
    private double valeurMarge;

    @Column(name = "VALEUR_MARGE_POURCENT")
    private double valeurMargePourcent;

    @Column(name = "SI_LITIGE")
    private boolean litige;

    @Column(name = "COLISAGE")
    private int colisage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTETE_COMMANDE_FK", referencedColumnName="ENTETE_COMMANDE_PK")
    private EnteteBonCommandeFournisseur enteteBCF;

    public DetailBonCommandeFournisseur() {
    }

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
        final DetailBonCommandeFournisseur other = (DetailBonCommandeFournisseur) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
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

    public float getQteCommande() {
        return qteCommande;
    }

    public void setQteCommande(float qteCommande) {
        if (qteCommande % colisage != 0) {
            this.qteCommande = 0;
        } else {
            this.qteCommande = qteCommande;
        }

    }

    public float getQteReceptionner() {
        return qteReceptionner;
    }

    public float getPfUnitaireTTC() {
        return pfUnitaireTTC;
    }

    public void setPfUnitaireTTC(float pfUnitaireTTC) {
        this.pfUnitaireTTC = pfUnitaireTTC;
    }

    public float getPvUnitaireTTC() {
        return pvUnitaireTTC;
    }

    public void setPvUnitaireTTC(float pvUnitaireTTC) {
        this.pvUnitaireTTC = pvUnitaireTTC;
    }

    public void setQteReceptionner(float qteReceptionner) {
        this.qteReceptionner = qteReceptionner;
    }

    public float getQteFacture() {
        return qteFacture;
    }

    public void setQteFacture(float qteFacture) {
        this.qteFacture = qteFacture;
    }

    public boolean isLitige() {
        return litige;
    }

    public void setLitige(boolean litige) {
        this.litige = litige;
    }

    public int getColisage() {
        return colisage;
    }

    public void setColisage(int colisage) {
        this.colisage = colisage;
    }

    public double getValeurMarge() {
        return valeurMarge;
    }

    public void setValeurMarge(double valeurMarge) {
        this.valeurMarge = valeurMarge;
    }

    public double getValeurMargePourcent() {
        return valeurMargePourcent;
    }

    public void setValeurMargePourcent(double valeurMargePourcent) {
        this.valeurMargePourcent = valeurMargePourcent;
    }
    
    
    /*
	@Override
	public String toString() {
		return "DetailBonCommandeFournisseur [id=" + id + ", article="
				+ article + ", qteCommande=" + qteCommande
				+ ", qteReceptionner=" + qteReceptionner + ", qteFacture="
				+ qteFacture + ", pfUnitaireTTC=" + pfUnitaireTTC
				+ ", pvUnitaireTTC=" + pvUnitaireTTC + ", valeurMarge="
				+ valeurMarge + ", valeurMargePourcent=" + valeurMargePourcent
				+ ", litige=" + litige + ", colisage=" + colisage + "]";
	}
     */

    public EnteteBonCommandeFournisseur getEnteteBCF() {
        return enteteBCF;
    }

    public void setEnteteBCF(EnteteBonCommandeFournisseur enteteBCF) {
        this.enteteBCF = enteteBCF;
    }

}
