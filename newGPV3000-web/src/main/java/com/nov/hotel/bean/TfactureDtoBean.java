/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dto.FactureDto;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TTarif;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TTarifService;
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
@Named(value = "facturedtoBean")
@SessionScoped
public class TfactureDtoBean implements Serializable {

    @EJB
    private TFactureService tfactureService;

    @EJB
    private TDetailFactureService tDetailFactureService;

    private TFacture tfacture = new TFacture();
    private TDetailFacture tdetailFacture = new TDetailFacture();
    private FactureDto facturedo = new FactureDto();
    private List<TFacture> listTfacture = new ArrayList<>();
    private List<TDetailFacture> listTdetailFacture = new ArrayList<>();
    private List<FactureDto> listfatureDto = new ArrayList<>();

    private long idcategorie;
    private long idoffretarifaire;

    public TFacture getTfacture() {
        return tfacture;
    }

    public void setTfacture(TFacture tfacture) {
        this.tfacture = tfacture;
    }

    public TDetailFacture getTdetailFacture() {
        return tdetailFacture;
    }

    public void setTdetailFacture(TDetailFacture tdetailFacture) {
        this.tdetailFacture = tdetailFacture;
    }

    public FactureDto getFacturedo() {
        return facturedo;
    }

    public void setFacturedo(FactureDto facturedo) {
        this.facturedo = facturedo;
    }

    public List<TFacture> getListTfacture() {
        listTfacture = tfactureService.listTFacture();
        return listTfacture;
    }

    public void setListTfacture(List<TFacture> listTfacture) {
        this.listTfacture = listTfacture;
    }

    public List<TDetailFacture> getListTdetailFacture() {
        return listTdetailFacture;
    }

    public void setListTdetailFacture(List<TDetailFacture> listTdetailFacture) {
        this.listTdetailFacture = listTdetailFacture;
    }

    public List<FactureDto> getListfatureDto() {
        remplirlist();
        return listfatureDto;
    }

    public void setListfatureDto(List<FactureDto> listfatureDto) {
        this.listfatureDto = listfatureDto;
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

    public void remplirlist() {
        listfatureDto.clear();
        listTfacture = tfactureService.listTFacture();
       // System.out.println(listTfacture.size());
        for (TFacture fact : listTfacture) {
            FactureDto vfact = new FactureDto();
            vfact.setFactDateCreate(fact.getFactDateCreate());
            vfact.setFactDateModif(fact.getFactDateModif());
            vfact.setNumFacture(fact.getNumFacture());
            vfact.setRemise(fact.getRemise());
            vfact.setStatuId(fact.getStatuId());
            vfact.setUserCreate(fact.getUserCreate());
            vfact.setUserModif(fact.getUserModif());
            vfact.setFactId(fact.getFactId());
            vfact.setClient(fact.getClient());
            double vprix = 0;
            long vqte = 0;
            listTdetailFacture = tDetailFactureService.listTDetailFactureByFacture(vfact.getFactId());
            listTdetailFacture.size();
            for (TDetailFacture detfac : listTdetailFacture) {
                detfac.toString();
                vprix = vprix+detfac.getDfactPrix();
                vqte = vqte+detfac.getDfactQte();
                
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println(vprix);
                System.out.println(detfac.getDfactPrix());
                
            }
            vfact.setPrix(vprix);
            vfact.setQte(vqte);

           // System.out.println(vfact.toString());
            listfatureDto.add(vfact);

        }

    }

    // fonction et methode
    public String creerUpdateTTarif() {
        //   Date today = new Date();
        return "";
    }

    public void detailFacturef() {
        System.out.println("jdjdejfffrfrf");
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("factureID");

        long idch = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {

            listTdetailFacture = tDetailFactureService.listTDetailFactureByFacture(idch);

            for (TDetailFacture tdet : listTdetailFacture) {
                System.out.println("aaaaaaa" + tdet.toString());
            }

        } else {

            System.out.println("xxxxx");
        }
    }

    
     
    
    
}
