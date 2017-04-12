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
public class CommandeDTO {
    
    private String gisement;
    
    private String codeArticle;
    
    private String designation;
    
    private Integer pvUnitaireTTC;
    
    private Integer colisage;
    
    private float qteCommande;

    public String getGisement() {
        return gisement;
    }

    public void setGisement(String gisement) {
        this.gisement = gisement;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getPvUnitaireTTC() {
        return pvUnitaireTTC;
    }

    public void setPvUnitaireTTC(Integer pvUnitaireTTC) {
        this.pvUnitaireTTC = pvUnitaireTTC;
    }

    public Integer getColisage() {
        return colisage;
    }

    public void setColisage(Integer colisage) {
        this.colisage = colisage;
    }

    public float getQteCommande() {
        return qteCommande;
    }

    public void setQteCommande(float qteCommande) {
        this.qteCommande = qteCommande;
    }
    
    
    
}
