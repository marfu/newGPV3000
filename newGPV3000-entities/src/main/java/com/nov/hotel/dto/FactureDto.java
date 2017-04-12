/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

import com.nov.hotel.entities.EtatFactureEnum;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TRemise;
import java.util.Date;

/**
 *
 * @author marfu
 */
public class FactureDto {

    private long factId;

    private Date factDateCreate;

    private Date factDateModif;
    private TClient client;

    private EtatFactureEnum statuId;

    private String userCreate;

    private String userModif;

    private TRemise remise;
    private double prix;
    private long qte;
    private String numFacture;
    

    public long getFactId() {
        return factId;
    }

    public void setFactId(long factId) {
        this.factId = factId;
    }

    public Date getFactDateCreate() {
        return factDateCreate;
    }

    public void setFactDateCreate(Date factDateCreate) {
        this.factDateCreate = factDateCreate;
    }

    public Date getFactDateModif() {
        return factDateModif;
    }

    public void setFactDateModif(Date factDateModif) {
        this.factDateModif = factDateModif;
    }

    public EtatFactureEnum getStatuId() {
        return statuId;
    }

    public void setStatuId(EtatFactureEnum statuId) {
        this.statuId = statuId;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }

    public TRemise getRemise() {
        return remise;
    }

    public void setRemise(TRemise remise) {
        this.remise = remise;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

  

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public long getQte() {
        return qte;
    }

    public void setQte(long qte) {
        this.qte = qte;
    }

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "FactureDto{" + "factId=" + factId + ", factDateCreate=" + factDateCreate + ", factDateModif=" + factDateModif + ", client=" + client + ", statuId=" + statuId + ", userCreate=" + userCreate + ", userModif=" + userModif + ", remise=" + remise + ", prix=" + prix + ", qte=" + qte + ", numFacture=" + numFacture + '}';
    }
    
    
    

}
