/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

/**
 *
 * @author marfu
 */
public class ChambreDto {
    
    private long idChambre;
    private String libChambre;
    private String numeroChambre;
    private String catChambre;
    private String fofChambre;
    private Double prixFofChambre;
    private long idfofChambre;
    private long idfofTarif;

    public long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(long idChambre) {
        this.idChambre = idChambre;
    }

    public String getLibChambre() {
        return libChambre;
    }

    public void setLibChambre(String libChambre) {
        this.libChambre = libChambre;
    }

    public String getNumeroChambre() {
        return numeroChambre;
    }

    public void setNumeroChambre(String numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public String getCatChambre() {
        return catChambre;
    }

    public void setCatChambre(String catChambre) {
        this.catChambre = catChambre;
    }

    public String getFofChambre() {
        return fofChambre;
    }

    public void setFofChambre(String fofChambre) {
        this.fofChambre = fofChambre;
    }

    public long getIdfofChambre() {
        return idfofChambre;
    }

    public void setIdfofChambre(long idfofChambre) {
        this.idfofChambre = idfofChambre;
    }

    public Double getPrixFofChambre() {
        return prixFofChambre;
    }

    public void setPrixFofChambre(Double prixFofChambre) {
        this.prixFofChambre = prixFofChambre;
    }

    public long getIdfofTarif() {
        return idfofTarif;
    }

    public void setIdfofTarif(long idfofTarif) {
        this.idfofTarif = idfofTarif;
    }
    
    
    
}
