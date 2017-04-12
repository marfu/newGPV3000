/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dto.DetailFactureDto;
import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TService;
import ci.prosuma.prosumagpv.entity.security.User;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TOffreTarifaireService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;

import javax.inject.Named;

/**
 *
 * @author manukey
 */
@Named(value = "tdetailFactureBean")
@SessionScoped
public class TDetailFactureBean implements Serializable {

    @EJB
    private TDetailFactureService tDetailFactureService;
    @EJB
    private TFactureService tFactureService;
    private DetailFactureDto dto;
    private TDetailFacture tDetailFacture;

    private Date dFactDateCreate;

    private TFacture facture;

    private TService service;

    private User userModif;

    private double dFactPrix;

    private long dFactQte;
    private List<DetailFactureDto> detail;

    public TDetailFacture gettDetailFacture() {
        return tDetailFacture;
    }

    public void settDetailFacture(TDetailFacture tDetailFacture) {
        this.tDetailFacture = tDetailFacture;
    }
    
    
    
    
    
    
    
    

    public DetailFactureDto getDto() {
        return dto;
    }

    public void setDto(DetailFactureDto dto) {
        this.dto = dto;
    }
    
    
    


    public TDetailFactureService gettDetailFactureService() {
        return tDetailFactureService;
    }

    public void settDetailFactureService(TDetailFactureService tDetailFactureService) {
        this.tDetailFactureService = tDetailFactureService;
    }

    public TFactureService gettFactureService() {
        return tFactureService;
    }

    public void settFactureService(TFactureService tFactureService) {
        this.tFactureService = tFactureService;
    }

    public Date getdFactDateCreate() {
        return dFactDateCreate;
    }

    public void setdFactDateCreate(Date dFactDateCreate) {
        this.dFactDateCreate = dFactDateCreate;
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

    public User getUserModif() {
        return userModif;
    }

    public void setUserModif(User userModif) {
        this.userModif = userModif;
    }

    public double getdFactPrix() {
        return dFactPrix;
    }

    public void setdFactPrix(double dFactPrix) {
        this.dFactPrix = dFactPrix;
    }

    public long getdFactQte() {
        return dFactQte;
    }

    public void setdFactQte(long dFactQte) {
        this.dFactQte = dFactQte;
    }

    public List<DetailFactureDto> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailFactureDto> detail) {
        this.detail = detail;
    }
//    
//    
     public void remplirList(ActionEvent actionEvent) {
         
       detail.add(dto);
       }
//     public void calcul(ActionEvent actionEvent) {
//        
//    }
//
//    public void viderList() {
//        
//        detail.clear();
//        
//    }

    
    
    
    
    
    
    
    
    
    // fonction et methode
    public String creerTDetailFacture() {
       Date today =new Date();
        tDetailFacture.setdFactDateCreate(dFactDateCreate);
        tDetailFactureService.CreerTDetailFacture(tDetailFacture);
        
        return "success";
    }

    public void supprimerTOffreTarifaire() {

    }

    public String modifierTOffreTarifaire() {

        //
        return "success";
    }

}
