/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.Beans;

import com.nov.hotel.entities.EtatFactureEnum;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TRemise;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TRemiseService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author manukey
 */
@Named(value = "tFactureBean")
@SessionScoped
public class TFactureBean implements Serializable {

    @EJB
    private TFactureService tFactureService;
        @EJB
    private TRemiseService tRemiseService;
    
      private TFacture factureVar;

    

    private Date factDateCreate;

    private List<TFacture> listTFacture;

    public Date getFactDateCreate() {
        return factDateCreate;
    }

    public void setFactDateCreate(Date factDateCreate) {
        this.factDateCreate = factDateCreate;
    }

    public List<TFacture> getListTFacture() {
        listTFacture=tFactureService.listTFacture();
        return listTFacture;
    }

    public void setListTFacture(List<TFacture> listTFacture) {
        this.listTFacture = listTFacture;
    }
    

    
    
    

    
    
    
    
    
    // fonction et methode
//    public String creerTFacture(long chambreid) {
//        Date today = new Date();
//        TRemise tremise = new TRemise();
//        if (chambreid == 0) {
//            tremise = tRemiseService.findRemiseTauxzero();
//            factureVar.setFactDateCreate(today);
//            factureVar.setStatuId(EtatFactureEnum.ENCOURS);
//            factureVar.setRemise(tremise);
//        tFactureService.CreerTFacture(factDateCreate);
//        
//        return "success";
//    }
//    }

    public void supprimerTFacture() {

    }

    public String modifierTFacture() {

        //
        return "success";
    }

}
