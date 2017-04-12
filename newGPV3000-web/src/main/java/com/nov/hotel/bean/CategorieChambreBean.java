/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.TCategorieChambre;
import com.nov.hotel.services.TCategorieChambreService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.inject.Named;


/**
 *
 * @author manukey
 */
@Named(value = "categorieChambreBean")
@SessionScoped
public class CategorieChambreBean implements Serializable {

    @EJB
    private TCategorieChambreService tCategorieChambreService;


    
    private TCategorieChambre ocategorieChambre=new TCategorieChambre();
    private List<TCategorieChambre> listTCategorieChambre;

    public List<TCategorieChambre> getListTCategorieChambre() {
        listTCategorieChambre=tCategorieChambreService.listTCategorieChambre();
        return listTCategorieChambre;
    }

    public void setListTCategorieChambre(List<TCategorieChambre> listTCategorieChambre) {
        this.listTCategorieChambre = listTCategorieChambre;
    }

    

    public TCategorieChambre getOcategorieChambre() {
        return ocategorieChambre;
    }

    public void setOcategorieChambre(TCategorieChambre ocategorieChambre) {
        this.ocategorieChambre = ocategorieChambre;
    }


    
    
    
    
    // fonction et methode
    public String creerCategorieChambre() {
        //Date today = new Date();

        tCategorieChambreService.CreerOrUpdateTCategorieChambre(ocategorieChambre);
        clearEntity();
        return "success";
    }

    public void supprimerChambre() {

    }

    public String modifierChambre() {

        //
        return "success";
    }
    
    public void detailCategorie(ActionEvent actionEvent) {
        
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
       
       long idCat = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {
            System.out.println("USER ID : " + id);
            
            ocategorieChambre = tCategorieChambreService.finbyIDCategorieChambre(idCat);
    
        } else {
          //  return "";
        }
    }
    
    
     public void clearEntity() {
         
        ocategorieChambre = new TCategorieChambre();
        
    }

}