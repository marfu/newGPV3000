/**
 *
 */
package ci.prosuma.prosumagpv.entity.commande;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author AKHDAR Zoul  
 * @author Serge AYEPI
 *
 */
//@Entity
//@Table(name = "SUGGESTION_COMMANDE_DETAIL")
public class DetailSuggestionCommande implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_SUGGESTION_PK")
    private long id;

    @Column(name = "CODE_ARTICLE_FK")
    private String codeArticle;

    @Column(name = "COLISAGE")
    private int colisage;

    @Column(name = "DELAIS_VENTE_JOUR")
    private int delaisVenteJours;

    @Column(name = "PF_UNITAIRE_TTC")
    private float pfUnitaireTTC;
    @Column(name = "PV_UNITAIRE_TTC")
    private float pvUnitaireTTC;

    @Column(name = "QTE_UC_COMMANDER")
    private float qteUCCommander;
    @Column(name = "QTE_UC_SUGGERER")
    private float qteUCSuggerer;
    @Column(name = "QTE_VL_COMMANDER")
    private float qteVLCommander;
    @Column(name = "QTE_VL_SUGGERER")
    private float qteVLSuggerer;

    @Column(name = "SI_POIDS_VARIABLE")
    private boolean poidsVariable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"),
        @JoinColumn(name = "NUM_DOSSIER_FK", referencedColumnName = "NUM_DOSSIER_PK"),})
    private EnteteSuggestionCommande enteteSuggestion;

    public DetailSuggestionCommande() {
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
        final DetailSuggestionCommande other = (DetailSuggestionCommande) obj;
        if (this.getId() != other.getId()) {
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

    public int getColisage() {
        return colisage;
    }

    public void setColisage(int colisage) {
        this.colisage = colisage;
    }

    public int getDelaisVenteJours() {
        return delaisVenteJours;
    }

    public void setDelaisVenteJours(int delaisVenteJours) {
        this.delaisVenteJours = delaisVenteJours;
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

    public float getQteUCCommander() {
        return qteUCCommander;
    }

    public void setQteUCCommander(float qteUCCommander) {
        if (qteUCCommander != 0) {
            this.qteUCCommander = qteUCCommander;
            this.qteVLCommander = this.qteUCCommander / colisage;
        } else {
            this.qteUCCommander = 0;
            this.qteVLCommander = 0;
        }

    }

    public float getQteUCSuggerer() {
        return qteUCSuggerer;
    }

    public void setQteUCSuggerer(float qteUCSuggerer) {
        this.qteUCSuggerer = qteUCSuggerer;
    }

    public float getQteVLCommander() {
        return qteVLCommander;
    }

    public void setQteVLCommander(float qteVLCommander) {
        if (qteVLCommander != 0) {
            this.qteVLCommander = qteVLCommander;
            this.qteUCCommander = this.qteVLCommander * colisage;
        } else {
            this.qteUCCommander = 0;
            this.qteVLCommander = 0;
        }
    }

    public float getQteVLSuggerer() {
        return qteVLSuggerer;
    }

    public void setQteVLSuggerer(float qteVLSuggerer) {
        this.qteVLSuggerer = qteVLSuggerer;
    }

    public boolean isPoidsVariable() {
        return poidsVariable;
    }

    public void setPoidsVariable(boolean poidsVariable) {
        this.poidsVariable = poidsVariable;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }
    
    
    /*
	@Override
	public String toString() {
		return "DetailSuggestionCommande [id=" + id + ", codeArticle="
				+ codeArticle + ", colisage=" + colisage
				+ ", delaisVenteJours=" + delaisVenteJours + ", pfUnitaireTTC="
				+ pfUnitaireTTC + ", pvUnitaireTTC=" + pvUnitaireTTC
				+ ", qteUCCommander=" + qteUCCommander + ", qteUCSuggerer="
				+ qteUCSuggerer + ", qteVLCommander=" + qteVLCommander
				+ ", qteVLSuggerer=" + qteVLSuggerer + ", poidsVariable="
				+ poidsVariable + "]";
	}

     */

    public EnteteSuggestionCommande getEnteteSuggestion() {
        return enteteSuggestion;
    }

    public void setEnteteSuggestion(EnteteSuggestionCommande enteteSuggestion) {
        this.enteteSuggestion = enteteSuggestion;
    }

}
