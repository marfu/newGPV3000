/**
 *
 */
package ci.prosuma.prosumagpv.entity.stock;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author AKHDAR Zoul
 *
 */
//@Entity
//@Table(name = "DETAIL_MOUVEMENT")
public class DetailMouvement implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DETAIL_MOUVEMENT_PK")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "TYPE_MOUVEMENT_FK", referencedColumnName = "TYPE_MOUVEMENT_PK")
    private TypeMouvementStock typeMouvement;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "DEPOT_FK", referencedColumnName = "DEPOT_PK")
    private Depot depot;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
        @JoinColumn(name = "CODE_ARTICLE_FK", referencedColumnName = "CODE_ARTICLE_PK"),
        @JoinColumn(name = "CODE_MAGASIN_FK", referencedColumnName = "CODE_MAGASIN_PK"),})
    private Article article;

    @Column(name = "GENCODE_FK", nullable = true)
    private String gcCode;

    @Column(name = "QTE_MOUVEMENTE_UC")
    private float qteMvtUc;

    @Column(name = "QTE_MOUVEMENTE")
    private float qteMvt;

    @Column(name = "QTE_PHYS_AVT_MOUV")
    private float qtePhysiqueAvantMouvement;

    @Column(name = "OBSERVATION")
    private String observations;

    private long artPrixReviens;

    private long artPrixReviensHT;

    private long artPrixVente;

    private long artPrixVenteHT;
    
    private long montantTotal;

    @Index(name = "secteur_dm")
    private String secteur;

    @Index(name = "raton_dm")
    private String rayon;

    @Index(name = "famille_dm")
    private String famille;

    @Index(name = "sousfamille_dm")
    private String sousFamille;

    private String designationArt;

    @Index(name = "analytique_dm")
    private String codeAnal;

    @Index(name = "promo_dm")
    private String themePromo;

    private int sens;

    @Temporal(TemporalType.TIMESTAMP)
    @Index(name = "datemouv_dm")
    private Date dateMouvement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTETE_MOUVEMENT_FK", referencedColumnName = "ENTETE_MOUVEMENT_PK")
    private EnteteMouvement enteteMouvement;

    public DetailMouvement() {
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
        final DetailMouvement other = (DetailMouvement) obj;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    public TypeMouvementStock getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(TypeMouvementStock typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getQteMvt() {
        return qteMvt;
    }

    public void setQteMvt(float qteMvt) {
        this.qteMvt = qteMvt;
    }

    public float getQtePhysiqueAvantMouvement() {
        return qtePhysiqueAvantMouvement;
    }

    public void setQtePhysiqueAvantMouvement(float qtePhysiqueAvantMouvement) {
        this.qtePhysiqueAvantMouvement = qtePhysiqueAvantMouvement;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public long getArtPrixReviens() {
        return Math.abs(artPrixReviens);
    }

    public void setArtPrixReviens(long artPrixReviens) {
        this.artPrixReviens = artPrixReviens;
    }

    public long getArtPrixVente() {
        return Math.abs(artPrixVente);
    }

    public void setArtPrixVente(long artPrixVente) {
        this.artPrixVente = artPrixVente;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public String getThemePromo() {
        return themePromo;
    }

    public void setThemePromo(String themePromo) {
        this.themePromo = themePromo;
    }

    public long getArtPrixReviensHT() {
        return Math.abs(artPrixReviensHT);
    }

    public void setArtPrixReviensHT(long artPrixReviensHT) {
        this.artPrixReviensHT = artPrixReviensHT;
    }

    public long getArtPrixVenteHT() {
        return Math.abs(artPrixVenteHT);
    }

    public void setArtPrixVenteHT(long artPrixVenteHT) {
        this.artPrixVenteHT = artPrixVenteHT;
    }

    public int getSens() {
        return sens;
    }

    public void setSens(int sens) {
        this.sens = sens;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getSousFamille() {
        return sousFamille;
    }

    public void setSousFamille(String sousFamille) {
        this.sousFamille = sousFamille;
    }

    public String getDesignationArt() {
        return designationArt;
    }

    public void setDesignationArt(String designationArt) {
        this.designationArt = designationArt;
    }

    public String getCodeAnal() {
        return codeAnal;
    }

    public void setCodeAnal(String codeAnal) {
        this.codeAnal = codeAnal;
    }

    public String getGcCode() {
        return gcCode;
    }

    public void setGcCode(String gcCode) {
        this.gcCode = gcCode;
    }

    public float getQteMvtUc() {
        return qteMvtUc;
    }

    public void setQteMvtUc(float qteMvtUc) {
        this.qteMvtUc = qteMvtUc;
    }

    public EnteteMouvement getEnteteMouvement() {
        return enteteMouvement;
    }

    public void setEnteteMouvement(EnteteMouvement enteteMouvement) {
        this.enteteMouvement = enteteMouvement;
    }

    public long getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(long montantTotal) {
        this.montantTotal = montantTotal;
    }
    
    

    public String myToString() {
        return "DetailMouvement [id=" + id + ", typeMouvement=" + typeMouvement
                + ", depot=" + depot + ", article=" + article + ", gcCode="
                + gcCode + ", qteMvtUc=" + qteMvtUc + ", qteMvt=" + qteMvt
                + ", qtePhysiqueAvantMouvement=" + qtePhysiqueAvantMouvement
                + ", observations=" + observations + ", artPrixReviens="
                + artPrixReviens + ", artPrixReviensHT=" + artPrixReviensHT
                + ", artPrixVente=" + artPrixVente + ", artPrixVenteHT="
                + artPrixVenteHT + ", secteur=" + secteur + ", rayon=" + rayon
                + ", famille=" + famille + ", sousFamille=" + sousFamille
                + ", designationArt=" + designationArt + ", codeAnal="
                + codeAnal + ", themePromo=" + themePromo + ", sens=" + sens
                + ", dateMouvement=" + dateMouvement + "]";
    }

}
