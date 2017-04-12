/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TClientDao;
import com.nov.hotel.entities.TClient;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author marfu
 */

@Stateless
public class TClientService  {

    @EJB
    private TClientDao tClientDao;
    
    
    public TClient CreerTClient(TClient c) {
        
      return tClientDao.createOrUpdateTClient(c);
       
    }
    
    public TClient listCLientByChambreEtReservation(long id) {
        
      return tClientDao.listTclient(id);
       
    }
    
     public TClient findById(long id) {
        
      return tClientDao.findById(id);
       
    }
    
    
}
