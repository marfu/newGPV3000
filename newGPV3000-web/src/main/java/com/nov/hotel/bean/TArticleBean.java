/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dto.ServiceFactureDto;
import com.nov.hotel.entities.TArticle;
import com.nov.hotel.entities.TService;
import com.nov.hotel.services.TArticleService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TRemiseService;
import com.nov.hotel.services.TServiceService;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "articleBean")
@SessionScoped
public class TArticleBean implements Serializable {

    @EJB
    private TServiceService tServiceService;
    @EJB
    private TArticleService tArticleService;
    private List<TArticle> listTArticle  ;

    private long idservice;
    private TArticle tarticle=new TArticle();

    public long getIdservice() {
        return idservice;
    }

    public void setIdservice(long idservice) {
        this.idservice = idservice;
    }

    public TArticle getTarticle() {
        return tarticle;
    }

    public void setTarticle(TArticle tarticle) {
        this.tarticle = tarticle;
    }

    public List<TArticle> getListTArticle() {
        listTArticle=tArticleService.listTArticle();
        return listTArticle;
    }

    public void setListTArticle(List<TArticle> listTArticle) {
        
        this.listTArticle = listTArticle;
    }



    
    
    
    
    
    // fonction et methode
    public String creerArticle() {
        Date today = new Date();

        if (idservice != 0) {
            TService tserviceparent = new TService();
            tserviceparent = tServiceService.findByIDService(idservice);
            tarticle.setArtService(tserviceparent);
        }
        if (tarticle.getArtId()!= 0) {
            tarticle.setArtDateModif(today);
        } else {
            tarticle.setArtDateCreate(today);
        }

        tArticleService.CreerOrUpdate(tarticle);
        clearEntity();
        return "success";
    }

    public void supprimerTService() {

    }

  

    public void detailArticle(ActionEvent actionEvent) {

        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userIds");

        long ids = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {

            tarticle = tArticleService.findArticlebyID(ids);

        } else {
            //  return "";
        }
    }

    public void clearEntity() {
        tarticle = new TArticle();
        idservice=0;
        System.out.println("dsdfgfdgfgfdfgf");
        
    }

}
