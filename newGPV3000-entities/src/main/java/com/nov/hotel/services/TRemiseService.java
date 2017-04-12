/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TRemiseDao;
import com.nov.hotel.entities.TRemise;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TRemiseService {

    @EJB
    private TRemiseDao tRemiseDao;
    
    
    public TRemise findRemiseTauxzero (){
        return tRemiseDao.findTRemiseBytauxZ();
}


   public List<TRemise> findAllRemise (){
        return tRemiseDao.findAll();
}


  

   
}
