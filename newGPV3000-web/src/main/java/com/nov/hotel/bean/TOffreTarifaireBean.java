/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.services.TOffreTarifaireService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author marfu
 */
@Named(value = "tOffreTarifaireBean")
@SessionScoped
public class TOffreTarifaireBean implements Serializable {

    @EJB
    private TOffreTarifaireService tOffreTarifaireService;

    private TOffreTarifaire toffreTarifaire=new TOffreTarifaire();
    private List<TOffreTarifaire> listTOffreTarifaire;

    public TOffreTarifaire getToffreTarifaire() {
        return toffreTarifaire;
    }

    public void setToffreTarifaire(TOffreTarifaire toffreTarifaire) {
        this.toffreTarifaire = toffreTarifaire;
    }

    
    public List<TOffreTarifaire> getListTOffreTarifaire() {
        listTOffreTarifaire=tOffreTarifaireService.listOffreTarifaire();
        return listTOffreTarifaire;
    }

    public void setListTOffreTarifaire(List<TOffreTarifaire> listTOffreTarifaire) {
        this.listTOffreTarifaire = listTOffreTarifaire;
    }

    // fonction et methode
    public String creerUpdateTOffreTarifaire() {
        Date today = new Date();
        if (toffreTarifaire.getOffreId()!= 0) {
            System.out.println(String.valueOf(toffreTarifaire.getOffreId()));
            toffreTarifaire.setOffDateModif(today);
        } else {
         System.out.println(String.valueOf(toffreTarifaire.getOffreId()));
            toffreTarifaire.setOffDateCreate(today);
        }

        tOffreTarifaireService.CreerOrUpdateTOffreTarifaire(toffreTarifaire);
        clearEntity();
        return "success";
    }

    public void supprimerTOffreTarifaire() {

    }

    public String modifierTOffreTarifaire() {

        //
        return "success";
    }
    
     public void detailOffre(ActionEvent actionEvent) {
        
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
       
       long idof = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {
            System.out.println("USER ID : " + id);
            
            toffreTarifaire = tOffreTarifaireService.findOffreTarifaire(idof);
            
           

        } else {
          //  return "";
        }
    }
     
       public void clearEntity() {
        toffreTarifaire = new TOffreTarifaire();
    }
   
}