/**
 *
 */
package ci.prosuma.prosumagpv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeArticle;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author AKHDAR Zoul and Serge AYEPI
 *
 */
//@Entity
//@Table(name = "ARTICLE")
//@IdClass(value = ArticleMagRefPK.class)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer PROMO_ORDINAIRE = 1;

    public static final Integer PROMO_LOT_HOMOGENE = 2;

    public static final Integer PROMO_LOT_HETEROGENE = 3;

    public static final Integer NO_PROMO = 0;

    @Id
    @Column(name = "CODE_MAGASIN_PK", length = 3)
    private String pvtCode;

    @Id
    @Column(name = "CODE_ARTICLE_PK", length = 15)
    private String codeArticle;

    @Column(name = "DESIGNATION", length = 35)
    private String designation;

    @Column(name = "LIBELLE_REDUIT", length = 20)
    private String libelReduit;

    @Column(name = "MOT_DIRECTEUR", length = 20)
    private String motDirecteur;

    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "SECTEUR_FK", length = 2)
    private String secteur;

    @Column(name = "RAYON_FK", length = 5)
    private String rayon;

    @Column(name = "FAMILLE_FK", length = 8)
    private String famille;

    @Column(name = "SOUS_FAMILLE_FK", length = 12)
    private String sousFamille;

    @Column(name = "SI_ACTIF")
    private boolean actif;

    @Column(name = "SI_COMMANDABLE_CENTRALE")
    private boolean commandableCentrale;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_ARTICLE", nullable = false)
    private TypeArticle typeArticle;

    @OneToMany(mappedBy = "article", cascade ={CascadeType.REMOVE,CascadeType.REFRESH})
    private List<GenCode> listGenCode;

    @Column(name = "ARTICLE_CONSIGNE", length = 11)
    private String articeConsigne;

    @Column(name = "CODE_ARTICLE_PRINCIPAL_FK", length = 11)
    private String articlePrincipal;

    @Column(name = "CODE_ANALYTIQUE_FK", length = 7)
    private String codeAnalytique;

    @Column(name = "CODE_GISEMENT", length = 8)
    private long codeGisement;

    @Column(name = "COLISAGE", length = 7)
    private String colisage;

    @Column(name = "TAUX_TVA_FK", length = 2)
    private String tauxTVA;

    @Column(name = "TAUX_ASDI_FK", length = 2)
    private String tauxASDI;

    @Column(name = "SI_TARIF_CENTRALE")
    private boolean tarifCentrale;

    @Column(name = "PRIX_REVIENS_TTC_EN_COURS")
    private int prixReviensTTCEnCours;

    @Column(name = "PRIX_REVIENS_TTC_PRECEDENT")
    private int prixReviensTTCPrecedent;

    @Column(name = "PRIX_VENTE_TTC_EN_COURS")
    private int prixVenteTTCEnCours;

    @Column(name = "PRIX_VENTE_TTC_PRECEDENT")
    private int prixVenteTTCPrecedent;

    @Column(name = "PRIX_VENTE_PROMO_TTC")
    private int prixVentePromoTTC;

    @Column(name = "PRIX_REVIENS_PROMO_TTC")
    private int prixReviensPromoTTC;

    @Column(name = "SI_LIVRAISON_DIRECTE")
    private boolean livraisonDirecte;

    @Column(name = "SI_LIVRAISON_CENTRALE")
    private boolean livraisonCentrale;

    @Column(name = "SI_LIVRAISON_ECLATEMENT")
    private boolean livraisonEclatement;

    @Column(name = "SI_PAS_DE_RUPTURE")
    private boolean pasDeRupture;

    @Column(name = "SI_PRESENTOIRE_CAISSE")
    private boolean presentoireCaisse;

    @Column(name = "PROMO_FK")
    private String promo;

    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;

    @Column(name = "SI_PRIX_VERT")
    private boolean prixVert;

    @Column(name = "SI_REACTIF")
    private boolean reactiver;

    @Column(name = "SI_SUPPRIMER")
    private boolean supprimer;

    @Column(name = "SI_MODIFIER")
    private boolean modifier;

    @Column(name = "SI_NOUVEAU")
    private boolean nouvelArticle;

    @Column(name = "SI_INVENTAIRE")
    private boolean inventaire;

    @Column(name = "ATTR1")
    private boolean attr1;

    @Column(name = "SI_PRIX_MODIFIER")
    private boolean prixModifier;

    /*modification Serge Ayepi pour la prise en compte des promos Lots*/
    @Column(name = "CODE_DU_LOT", columnDefinition = "int default 0")
    private Integer codeLot = 0;

    @Column(name = "QUANTITE_DANS_LOT", columnDefinition = "int default 0")
    private Integer quantiteLot = 0;

    @Column(name = "PRIX_DU_LOT", columnDefinition = "int default 0")
    private Integer prixDuLot = 0;

    @Column(name = "TYPE_PROMO", columnDefinition = "int default 0")
    private Integer typePromo = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STOCK_ARTICLE_PK")
    private StockArticle stock;

    public Article() {
    }

    @Override
    public int hashCode() {
        return (getCodeArticle() + getPvtCode()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getCodeArticle() == null) {
            return false;
        }
        if (this.getPvtCode() == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;

        if (!getCodeArticle().equals(other.getCodeArticle())
                || !getPvtCode().equals(other.getPvtCode())) {
            return false;
        }
        return true;
    }

    public boolean needUpdatePrice(Article a) {
        if (getPrixReviensTTCEnCours() != a.getPrixReviensTTCEnCours()) {
            return true;
        }
        if (getPrixVenteTTCEnCours() != a.getPrixVenteTTCEnCours()) {
            return true;
        }
        if (getPrixVentePromoTTC() != a.getPrixVentePromoTTC()) {
            return true;
        }
        if (getPrixReviensPromoTTC() != a.getPrixReviensPromoTTC()) {
            return true;
        }
        if (getPrixDuLot() != a.getPrixDuLot()) {
            return true;
        }

        return false;
    }

    public boolean needUpdateInfo(Article a) {
        if (getDesignation() != null && a.getDesignation() != null && !getDesignation().equals(a.getDesignation())) {
            return true;
        }
        if (getArticeConsigne() != null && a.getArticeConsigne() != null && !getArticeConsigne().equals(a.getArticeConsigne())) {
            return true;
        }
        if (getCodeAnalytique() != null && a.getCodeAnalytique() != null && !getCodeAnalytique().equals(a.getCodeAnalytique())) {
            return true;
        }
        if (getColisage() != null && a.getColisage() != null && !getColisage().equals(a.getColisage())) {
            return true;
        }
        if (null != getPromo() && null != a.getPromo() && !getPromo().equals(a.getPromo())) {
            return true;
        }
        if (getFamille() != null && a.getFamille() != null && !getFamille().equals(a.getFamille())) {
            return true;
        }
        if (getRayon() != null && a.getRayon() != null && !getRayon().equals(a.getRayon())) {
            return true;
        }
        if (getSecteur() != null && a.getSecteur() != null && !getSecteur().equals(a.getSecteur())) {
            return true;
        }
        if (getSousFamille() != null && a.getSousFamille() != null && !getSousFamille().equals(a.getSousFamille())) {
            return true;
        }
        if (getTauxASDI() != null && a.getTauxASDI() != null && !getTauxASDI().equals(a.getTauxASDI())) {
            return true;
        }
        if (getTauxTVA() != null && a.getTauxTVA() != null && !getTauxTVA().equals(a.getTauxTVA())) {
            return true;
        }
        if (getTypeArticle() != null && a.getTypeArticle() != null && !getTypeArticle().equals(a.getTypeArticle())) {
            return true;
        }

        if (isCommandableCentrale() != a.isCommandableCentrale()) {
            return true;
        }
        if (isLivraisonCentrale() != a.isLivraisonCentrale()) {
            return true;
        }
        if (isLivraisonDirecte() != a.isLivraisonDirecte()) {
            return true;
        }
        if (isLivraisonEclatement() != a.isLivraisonEclatement()) {
            return true;
        }
        if (isPasDeRupture() != a.isPasDeRupture()) {
            return true;
        }
        if (isPresentoireCaisse() != a.isPresentoireCaisse()) {
            return true;
        }
        if (isTarifCentrale() != a.isTarifCentrale()) {
            return true;
        }
        if (getQuantiteLot() != a.getQuantiteLot()) {
            return true;
        }
        if (getCodeLot() != a.getCodeLot()) {
            return true;
        }
        if (getTypePromo() != a.getTypePromo()) {
            return true;
        }
        return false;
    }

    public boolean isAttr1() {
        return attr1;
    }

    public void setAttr1(boolean attr1) {
        this.attr1 = attr1;
    }

    public boolean isReactiver() {
        return reactiver;
    }

    public void setReactiver(boolean reactiver) {
        this.reactiver = reactiver;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public StockArticle getStock() {
        return stock;
    }

    public void setStock(StockArticle stock) {
        this.stock = stock;
    }

    public boolean isPrixModifier() {
        return prixModifier;
    }

    public void setPrixModifier(boolean prixModifier) {
        this.prixModifier = prixModifier;
    }

    public boolean isModifier() {
        return modifier;
    }

    public void setModifier(boolean modifier) {
        this.modifier = modifier;
    }

    public boolean isNouvelArticle() {
        return nouvelArticle;
    }

    public void setNouvelArticle(boolean nouvelArticle) {
        this.nouvelArticle = nouvelArticle;
    }

    // public List<GenCode> getListGenCode() {
    // return listGenCode;
    // }
    //
    // public void setListGenCode(List<GenCode> listGenCode) {
    // this.listGenCode = listGenCode;
    // }
    public TypeArticle getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(TypeArticle typeArticle) {
        this.typeArticle = typeArticle;
    }

    public String getPvtCode() {
        return pvtCode;
    }

    public void setPvtCode(String pvtCode) {
        this.pvtCode = pvtCode;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getArticeConsigne() {
        return articeConsigne;
    }

    public void setArticeConsigne(String articeConsigne) {
        this.articeConsigne = articeConsigne;
    }

    public long getCodeGisement() {
        return codeGisement;
    }

    public void setCodeGisement(long codeGisement) {
        this.codeGisement = codeGisement;
    }

    public String getColisage() {
        return colisage;
    }

    public void setColisage(String colisage) {
        this.colisage = colisage;
    }

    public boolean isTarifCentrale() {
        return tarifCentrale;
    }

    public void setTarifCentrale(boolean tarifCentrale) {
        this.tarifCentrale = tarifCentrale;
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

    public int getPrixReviensTTCPrecedent() {
        return prixReviensTTCPrecedent;
    }

    public void setPrixReviensTTCPrecedent(int prixReviensTTCPrecedent) {
        this.prixReviensTTCPrecedent = prixReviensTTCPrecedent;
    }

    public int getPrixVenteTTCPrecedent() {
        return prixVenteTTCPrecedent;
    }

    public void setPrixVenteTTCPrecedent(int prixVenteTTCPrecedent) {
        this.prixVenteTTCPrecedent = prixVenteTTCPrecedent;
    }

    public boolean isLivraisonDirecte() {
        return livraisonDirecte;
    }

    public void setLivraisonDirecte(boolean livraisonDirecte) {
        this.livraisonDirecte = livraisonDirecte;
    }

    public boolean isLivraisonCentrale() {
        return livraisonCentrale;
    }

    public void setLivraisonCentrale(boolean livraisonCentrale) {
        this.livraisonCentrale = livraisonCentrale;
    }

    public boolean isLivraisonEclatement() {
        return livraisonEclatement;
    }

    public void setLivraisonEclatement(boolean livraisonEclatement) {
        this.livraisonEclatement = livraisonEclatement;
    }

    public boolean isCommandableCentrale() {
        return commandableCentrale;
    }

    public void setCommandableCentrale(boolean commandableCentrale) {
        this.commandableCentrale = commandableCentrale;
    }

    public boolean isPasDeRupture() {
        return pasDeRupture;
    }

    public void setPasDeRupture(boolean pasDeRupture) {
        this.pasDeRupture = pasDeRupture;
    }

    public boolean isPresentoireCaisse() {
        return presentoireCaisse;
    }

    public void setPresentoireCaisse(boolean presentoireCaisse) {
        this.presentoireCaisse = presentoireCaisse;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLibelReduit() {
        return libelReduit;
    }

    public void setLibelReduit(String libelReduit) {
        this.libelReduit = libelReduit;
    }

    public String getMotDirecteur() {
        return motDirecteur;
    }

    public void setMotDirecteur(String motDirecteur) {
        this.motDirecteur = motDirecteur;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getCodeAnalytique() {
        return codeAnalytique;
    }

    public void setCodeAnalytique(String codeAnalytique) {
        this.codeAnalytique = codeAnalytique;
    }

    public String getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(String tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public String getTauxASDI() {
        return tauxASDI;
    }

    public void setTauxASDI(String tauxASDI) {
        this.tauxASDI = tauxASDI;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean isPrixVert() {
        return prixVert;
    }

    public void setPrixVert(boolean prixVert) {
        this.prixVert = prixVert;
    }

    public String getArticlePrincipal() {
        return articlePrincipal;
    }

    public void setArticlePrincipal(String articlePrincipal) {
        this.articlePrincipal = articlePrincipal;
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

    public void setPrixReviensPromoTTC(int prixReviensPromoTTC) {
        this.prixReviensPromoTTC = prixReviensPromoTTC;
    }

    public boolean isInventaire() {
        return inventaire;
    }

    public void setInventaire(boolean inventaire) {
        this.inventaire = inventaire;
    }


    public boolean isPrincipal() {
        return this.typeArticle.equals(TypeArticle.PRINCIPAL);
    }

    public Integer getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(Integer typePromo) {
        this.typePromo = typePromo;
    }

    public String myToString() {
        return "Article [pvtCode=" + pvtCode + ", codeArticle=" + codeArticle
                + ", designation=" + designation + ", libelReduit="
                + libelReduit + ", motDirecteur=" + motDirecteur
                + ", dateCreation=" + dateCreation + ", secteur=" + secteur
                + ", rayon=" + rayon + ", famille="
                + famille + ", sousFamille=" + sousFamille + ", actif=" + actif
                + ", commandableCentrale=" + commandableCentrale
                + ", typeArticle=" + typeArticle + ", articeConsigne="
                + articeConsigne + ", articlePrincipal=" + articlePrincipal
                + ", codeAnalytique=" + codeAnalytique + ", codeGisement="
                + codeGisement + ", colisage=" + colisage + ", tauxTVA="
                + tauxTVA + ", tauxASDI=" + tauxASDI + ", tarifCentrale="
                + tarifCentrale + ", prixReviensTTCEnCours="
                + prixReviensTTCEnCours + ", prixReviensTTCPrecedent="
                + prixReviensTTCPrecedent + ", prixVenteTTCEnCours="
                + prixVenteTTCEnCours + ", prixVenteTTCPrecedent="
                + prixVenteTTCPrecedent + ", prixVentePromoTTC="
                + prixVentePromoTTC + ", prixReviensPromoTTC="
                + prixReviensPromoTTC + ", livraisonDirecte="
                + livraisonDirecte + ", livraisonCentrale=" + livraisonCentrale
                + ", livraisonEclatement=" + livraisonEclatement
                + ", pasDeRupture=" + pasDeRupture + ", presentoireCaisse="
                + presentoireCaisse + ", promo=" + promo
                + ", dateModification=" + dateModification + ", prixVert="
                + prixVert + ", reactiver=" + reactiver + ", supprimer="
                + supprimer + ", modifier=" + modifier + ", nouvelArticle="
                + nouvelArticle + ", inventaire=" + inventaire + ", attr1="
                + attr1 + ", prixModifier=" + prixModifier + ", stock=" + stock
                + "]";
    }

    public int getCodeLot() {
        return codeLot;
    }

    public void setCodeLot(int codeLot) {
        this.codeLot = codeLot;
    }

    public int getQuantiteLot() {
        return quantiteLot;
    }

    public void setQuantiteLot(int quantiteLot) {
        this.quantiteLot = quantiteLot;
    }

    public int getPrixDuLot() {
        return prixDuLot;
    }

    public void setPrixDuLot(int prixDuLot) {
        this.prixDuLot = prixDuLot;
    }

    public List<GenCode> getListGenCode() {
        return listGenCode;
    }

    public void setListGenCode(List<GenCode> listGenCode) {
        this.listGenCode = listGenCode;
    }

    @Override
    public String toString() {
        return "Article [pvtCode=" + pvtCode + ", codeArticle=" + codeArticle
                + ", designation=" + designation + ", libelReduit="
                + libelReduit + ", motDirecteur=" + motDirecteur
                + ", dateCreation=" + dateCreation + ", secteur=" + secteur
                + ", rayon=" + rayon + ", famille="
                + famille + ", sousFamille=" + sousFamille + ", actif=" + actif
                + ", commandableCentrale=" + commandableCentrale
                + ", typeArticle=" + typeArticle + ", articeConsigne="
                + articeConsigne + ", articlePrincipal=" + articlePrincipal
                + ", codeAnalytique=" + codeAnalytique + ", codeGisement="
                + codeGisement + ", colisage=" + colisage + ", tauxTVA="
                + tauxTVA + ", tauxASDI=" + tauxASDI + ", tarifCentrale="
                + tarifCentrale + ", prixReviensTTCEnCours="
                + prixReviensTTCEnCours + ", prixReviensTTCPrecedent="
                + prixReviensTTCPrecedent + ", prixVenteTTCEnCours="
                + prixVenteTTCEnCours + ", prixVenteTTCPrecedent="
                + prixVenteTTCPrecedent + ", prixVentePromoTTC="
                + prixVentePromoTTC + ", prixReviensPromoTTC="
                + prixReviensPromoTTC + ", livraisonDirecte="
                + livraisonDirecte + ", livraisonCentrale=" + livraisonCentrale
                + ", livraisonEclatement=" + livraisonEclatement
                + ", pasDeRupture=" + pasDeRupture + ", presentoireCaisse="
                + presentoireCaisse + ", promo=" + promo
                + ", dateModification=" + dateModification + ", prixVert="
                + prixVert + ", reactiver=" + reactiver + ", supprimer="
                + supprimer + ", modifier=" + modifier + ", nouvelArticle="
                + nouvelArticle + ", inventaire=" + inventaire + ", attr1="
                + attr1 + ", prixModifier=" + prixModifier + ", codeLot="
                + codeLot + ", quantiteLot=" + quantiteLot + ", prixDuLot="
                + prixDuLot + ", typePromo=" + typePromo 
                + "]";
    }

}
