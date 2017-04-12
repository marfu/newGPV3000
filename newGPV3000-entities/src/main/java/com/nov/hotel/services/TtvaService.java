/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TClientDao;
import com.nov.hotel.dao.TtvaDao;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.Ttva;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author marfu
 */

@Stateless
public class TtvaService  {

    @EJB
    private TtvaDao ttvaDao;
    
    
    
    
    public Ttva getTva() {
        
      return ttvaDao.getTva();
       
    }
    
    
}
