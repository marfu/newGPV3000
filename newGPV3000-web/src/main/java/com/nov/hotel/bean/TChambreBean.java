/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreService;
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
 * @author manukey
 */
@Named(value = "chambreBean")
@SessionScoped
public class TChambreBean implements Serializable {

    @EJB
    private TChambreService tChambreService;
    @EJB
    private TCategorieChambreService tCategorieChambreService;


    private TChambre tchambre= new TChambre();
    private List<TChambre> listTChambre;

    private long idcategorie;
    
    
    

    public TChambre getTchambre() {
        return tchambre;
    }

    public void setTchambre(TChambre tchambre) {
        this.tchambre = tchambre;
    }

    public long getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(long idcategorie) {
        this.idcategorie = idcategorie;
    }


    public List<TChambre> getListTChambre() {
        listTChambre = tChambreService.listChambre();
        return listTChambre;
    }

    public void setListTChambre(List<TChambre> listTChambre) {
        this.listTChambre = listTChambre;
    }

    
    
    
    
    
    
    
    // fonction et methode
    public String creerUpdateChambre() {
        Date today = new Date();

        tchambre.setChCategorie(tCategorieChambreService.finbyIDCategorieChambre(idcategorie));
        System.out.println("getChLib "+ tchambre.getChLib());
        System.out.println("getChNumeroChambre "+ tchambre.getChNumeroChambre());

        if (tchambre.getChId() != 0) {
            tchambre.setChDateModif(today);
        } else {
            tchambre.setEtat(EtatChambreEnum.LIBRE);
            tchambre.setChDateCreate(today);
        }

        tChambreService.CreerOrUpdate(tchambre);
        clearEntity();
        return "success";
    }
    
    
    
    public void supprimerChambre() {

    }

    
    
    
    public void detailChambre(ActionEvent actionEvent) {

        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");

        long idch = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {

            tchambre = tChambreService.findChambreById(idch);

        } else {
            //  return "";
        }
    }
    
    
      public void clearEntity() {
        tchambre = new TChambre();
        idcategorie=0;
    }

}