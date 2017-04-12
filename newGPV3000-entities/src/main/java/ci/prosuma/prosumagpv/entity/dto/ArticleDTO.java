/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity.dto;

/**
 *
 * @author tagsergi
 */
public class ArticleDTO {
    
    private String pvtCode;
    
    private String codeArticle;
    
    private String secteur;
    
    private String rayon;
    
    private String famille;
    
    private String sousFamille;
    
    private Integer typePromo;
    
    private Integer prixDuLot;
    
    private Integer quantiteLot;
    
    private String promo;
    
    private int prixVentePromoTTC;
    
    private int prixVenteTTCEnCours;
    
    private String articeConsigne;
    
    private String tauxTVA;
    
    private String libelReduit;

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

    public Integer getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(Integer typePromo) {
        this.typePromo = typePromo;
    }

    public Integer getPrixDuLot() {
        return prixDuLot;
    }

    public void setPrixDuLot(Integer prixDuLot) {
        this.prixDuLot = prixDuLot;
    }

    public Integer getQuantiteLot() {
        return quantiteLot;
    }

    public void setQuantiteLot(Integer quantiteLot) {
        this.quantiteLot = quantiteLot;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public int getPrixVentePromoTTC() {
        return prixVentePromoTTC;
    }

    public void setPrixVentePromoTTC(int prixVentePromoTTC) {
        this.prixVentePromoTTC = prixVentePromoTTC;
    }

    public int getPrixVenteTTCEnCours() {
        return prixVenteTTCEnCours;
    }

    public void setPrixVenteTTCEnCours(int prixVenteTTCEnCours) {
        this.prixVenteTTCEnCours = prixVenteTTCEnCours;
    }

    public String getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(String tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public String getArticeConsigne() {
        return articeConsigne;
    }

    public void setArticeConsigne(String articeConsigne) {
        this.articeConsigne = articeConsigne;
    }

    public String getLibelReduit() {
        return libelReduit;
    }

    public void setLibelReduit(String libelReduit) {
        this.libelReduit = libelReduit;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" + "pvtCode=" + pvtCode + ", codeArticle=" + codeArticle + ", secteur=" + secteur + ", rayon=" + rayon + ", prixVenteTTCEnCours=" + prixVenteTTCEnCours + ", tauxTVA=" + tauxTVA + ", libelReduit=" + libelReduit + '}';
    }
    
    
    
}
