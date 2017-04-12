/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dao.TRemiseDao;
import com.nov.hotel.entities.TRemise;
import com.nov.hotel.services.TRemiseService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author marfu
 */


@Named(value = "remiseBean")
@SessionScoped
public class RemiseBean  implements Serializable{
    
    @EJB
    private TRemiseService tRemiseService;
    
    private List<TRemise>listRemise;

    public List<TRemise> getListRemise() {
        return listRemise=tRemiseService.findAllRemise();
    }

    public void setListRemise(List<TRemise> listRemise) {
        this.listRemise = listRemise;
    }
    
    
    
}
