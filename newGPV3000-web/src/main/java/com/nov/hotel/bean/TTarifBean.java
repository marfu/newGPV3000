/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TTarif;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TTarifService;
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
@Named(value = "tarifBean")
@SessionScoped
public class TTarifBean implements Serializable {

    @EJB
    private TTarifService tTarifService;

    @EJB
    private TCategorieChambreService tCategorieChambreService;
    @EJB
    private TOffreTarifaireService toffreTarifaireService;

    private TTarif ttarif = new TTarif();
    private List<TTarif> listTtarif;

    private long idcategorie;
    private long idoffretarifaire;

    public TTarifService gettTarifService() {
        return tTarifService;
    }

    public void settTarifService(TTarifService tTarifService) {
        this.tTarifService = tTarifService;
    }

    public TCategorieChambreService gettCategorieChambreService() {
        return tCategorieChambreService;
    }

    public void settCategorieChambreService(TCategorieChambreService tCategorieChambreService) {
        this.tCategorieChambreService = tCategorieChambreService;
    }

    public TOffreTarifaireService getToffreTarifaireService() {
        return toffreTarifaireService;
    }

    public void setToffreTarifaireService(TOffreTarifaireService toffreTarifaireService) {
        this.toffreTarifaireService = toffreTarifaireService;
    }

    public TTarif getTtarif() {
        return ttarif;
    }

    public void setTtarif(TTarif ttarif) {
        this.ttarif = ttarif;
    }

   

    public List<TTarif> getListTtarif() {
        listTtarif=tTarifService.findAllOffreTarifaire();
        return listTtarif;
    }

    public void setListTtarif(List<TTarif> listTtarif) {
        this.listTtarif = listTtarif;
    }

    public long getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(long idcategorie) {
        this.idcategorie = idcategorie;
    }

    public long getIdoffretarifaire() {
        return idoffretarifaire;
    }

    public void setIdoffretarifaire(long idoffretarifaire) {
        this.idoffretarifaire = idoffretarifaire;
    }

    
    
    // fonction et methode
    public String creerUpdateTTarif() {
        //   Date today = new Date();

        ttarif.setChCategorie(tCategorieChambreService.finbyIDCategorieChambre(idcategorie));
        ttarif.setOffre(toffreTarifaireService.findOffreTarifaire(idoffretarifaire));
       


        tTarifService.CreerOrUpdate(ttarif);
        clearEntity();
        return "success";
    }

    

    public void detailTarif(ActionEvent actionEvent) {

        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");

        long idch = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {

            ttarif = tTarifService.findOffreTarifaireByID(idch);

        } else {
            //  return "";
        }
    }

    public void clearEntity() {
        ttarif = new TTarif();
        idcategorie = 0;
        idoffretarifaire = 0;
    }

}
