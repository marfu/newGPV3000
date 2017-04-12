/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.TClient;
import com.nov.hotel.services.TClientService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author marfu
 */

@Named(value = "tClientBean")
@SessionScoped
public class TClientBean implements Serializable {
    
     @EJB
    private TClientService tClientService;
     
    private List<TClient> listClient;

    public List<TClient> getListClient() {
        return listClient;
    }

    public void setListClient(List<TClient> listClient) {
        this.listClient = listClient;
    }
    
    
    
    
}
