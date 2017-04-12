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
public class DetailsChambreOccupantsDto {

    private long idChambre;
    private String libChambre;
    private String numeroChambre;
    private String catChambre;
    private String fofChambre;
    private Double prixFofChambre;
    private long idfofChambre;
    private long idfofTarif;
    private String nom;
    private String prenom;
    private String contact;
    private String typePiece;
    private String numPiece;
    
    private String occupant;

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

    public Double getPrixFofChambre() {
        return prixFofChambre;
    }

    public void setPrixFofChambre(Double prixFofChambre) {
        this.prixFofChambre = prixFofChambre;
    }

    public long getIdfofChambre() {
        return idfofChambre;
    }

    public void setIdfofChambre(long idfofChambre) {
        this.idfofChambre = idfofChambre;
    }

    public long getIdfofTarif() {
        return idfofTarif;
    }

    public void setIdfofTarif(long idfofTarif) {
        this.idfofTarif = idfofTarif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public String getOccupant() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    
}
