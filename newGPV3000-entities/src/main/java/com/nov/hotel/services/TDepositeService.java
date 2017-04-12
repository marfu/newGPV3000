/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;


import com.nov.hotel.dao.TDepositeDao;

import com.nov.hotel.entities.TDeposite;

import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author marfu
 */

@Stateless
public class TDepositeService  {

    @EJB
    private TDepositeDao tDepositeDao;
    
    
    public Double findTDepositeByUser(long id) {
        
      return tDepositeDao.findDepositeByUser(id);
       
    }
    
    
    
    
}
