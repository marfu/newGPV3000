/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.TModePaiment;
import com.nov.hotel.services.TModePaiementService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author marfu
 */
@Named(value = "tModePaiement")
@SessionScoped
public class TModePaiementBean implements Serializable {

    @EJB
    private TModePaiementService tModePaiementService;

    private List<TModePaiment> listModePaiement;

    public List<TModePaiment> getListModePaiement() {
        listModePaiement=tModePaiementService.listTModePaiment();
        return listModePaiement;
    }

    public void setListModePaiement(List<TModePaiment> listModePaiement) {
        this.listModePaiement = listModePaiement;
    }
    
    
    

}
