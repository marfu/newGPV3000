/**
 *
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Serge AYEPI
 *
 */
//@Entity
//@Table(name = "LIEN_ARTICLE_PROMO")
public class LienArtPromo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CODE_ARTICLE_FK", length = 11)
    private String article;

    @Column(name = "PROMO_FK", length = 7)
    private String promo;

    @Column(name = "SI_ACTIF", columnDefinition = "BIT(1) DEFAULT 1")
    private boolean actif;

    @Column(name = "DATE_CREATION", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    
    @Column(name = "PRIX_REVIENS_TTC")
    private int prixReviensTTC;

    @Column(name = "PRIX_VENTE_TTC")
    private int prixVenteTTC;

    @Column(name = "CODE_DU_LOT", columnDefinition = "int default 0")
    private Integer codeLot = 0;

    @Column(name = "QUANTITE_DANS_LOT", columnDefinition = "int default 0")
    private Integer quantiteLot = 0;

    @Column(name = "PRIX_DU_LOT", columnDefinition = "int default 0")
    private Integer prixDuLot = 0;

    @Column(name = "TYPE_PROMO", columnDefinition = "int default 0")
    private Integer typePromo = 0;

    @Column(name = "PRIX_VENTE_PROMO_TTC")
    private int prixVentePromoTTC;

    @Column(name = "PRIX_REVIENS_PROMO_TTC")
    private int prixReviensPromoTTC;

    public LienArtPromo() {
    }

    @PrePersist
    protected void onCreate() {
        dateCreation = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = new Date();
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
        final GenCode other = (GenCode) obj;
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean isActif() {
        return actif;
    }

    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * @return the dateModification
     */
    public Date getDateModification() {
        return dateModification;
    }

    /**
     * @param dateModification the dateModification to set
     */
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean needUpdate(LienArtPromo laf) {
        if (laf == null) {
            return true;
        }
        if (getArticle() != null && laf.getArticle() != null
                && !getArticle().equals(laf.getArticle())) {
            return true;
        }
        if (getPromo() != null && laf.getPromo() != null
                && !getPromo().equals(laf.getPromo())) {
            return true;
        }
        if (isActif() != laf.isActif()) {
            return true;
        }

        return false;
    }

    public Integer getCodeLot() {
        return codeLot;
    }

    public void setCodeLot(Integer codeLot) {
        this.codeLot = codeLot;
    }

    public Integer getQuantiteLot() {
        return quantiteLot;
    }

    public void setQuantiteLot(Integer quantiteLot) {
        this.quantiteLot = quantiteLot;
    }

    public Integer getPrixDuLot() {
        return prixDuLot;
    }

    public void setPrixDuLot(Integer prixDuLot) {
        this.prixDuLot = prixDuLot;
    }

    public Integer getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(Integer typePromo) {
        this.typePromo = typePromo;
    }

    public int getPrixVentePromoTTC() {
        return prixVentePromoTTC;
    }

    public void setPrixVentePromoTTC(int prixVentePromoTTC) {
        this.prixVentePromoTTC = prixVentePromoTTC;
    }

    public int getPrixReviensPromoTTC() {
        return prixReviensPromoTTC;
    }

    public int getPrixReviensTTC() {
        return prixReviensTTC;
    }

    public void setPrixReviensTTC(int prixReviensTTC) {
        this.prixReviensTTC = prixReviensTTC;
    }

    public int getPrixVenteTTC() {
        return prixVenteTTC;
    }

    public void setPrixVenteTTC(int prixVenteTTC) {
        this.prixVenteTTC = prixVenteTTC;
    }
    
    

    public void setPrixReviensPromoTTC(int prixReviensPromoTTC) {
        this.prixReviensPromoTTC = prixReviensPromoTTC;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LienArtPromo [id=" + id + ", article=" + article + ", promo="
                + promo + ", actif=" + actif + ", dateCreation=" + dateCreation
                + ", dateModification=" + dateModification + "]";
    }

}
