/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TOccupationDao;
import com.nov.hotel.entities.TOccupation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */

@Stateless
public class TOccupationService {
    
    @EJB
    private TOccupationDao tOccupationDao;
    
    public TOccupation CreerTOccupation(TOccupation u) {
       
        
        return tOccupationDao.create(u);
    }
    
     public TOccupation CreerOrUpdateTOccupation(TOccupation u) {
       
        
        return tOccupationDao.createOrUpdate(u);
    }
    
    public List<TOccupation> ListTOccupation() {
       
        
        return tOccupationDao.listTOccupation();
    }
    
}
