/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TService;

/**
 *
 * @author manukey
 */
public class DetailFactureDto {

    private TFacture facture;
    

    private TService service;
    private long idservice;
    private TService serviceInt;
    private TService serviceTerm;
    private double qte;
    private double prix;
    private double prixUnitaire;
    
    

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public TFacture getFacture() {
        return facture;
    }

    public void setFacture(TFacture facture) {
        this.facture = facture;
    }

    public TService getService() {
        return service;
    }

    public void setService(TService service) {
        this.service = service;
    }

    public long getIdservice() {
        return idservice;
    }

    public void setIdservice(long idservice) {
        this.idservice = idservice;
    }

    public TService getServiceInt() {
        return serviceInt;
    }

    public void setServiceInt(TService serviceInt) {
        this.serviceInt = serviceInt;
    }

    public TService getServiceTerm() {
        return serviceTerm;
    }

    public void setServiceTerm(TService serviceTerm) {
        this.serviceTerm = serviceTerm;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
