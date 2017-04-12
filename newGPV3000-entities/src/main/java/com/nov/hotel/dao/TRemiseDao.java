/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TRemise;
import com.nov.hotel.entities.TService;





/**
 *
 * @author manukey
 */
public interface TRemiseDao extends GenericDao<TRemise>{
    public TRemise findTRemiseBytauxZ();
   
    
}
