/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TReservationDao;
import com.nov.hotel.dto.ReservationDto;
import com.nov.hotel.entities.TReservation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */
@Stateless
public class TReservationService {
    
    
    @EJB
    private TReservationDao tReservationDao;
    
     public TReservation CreerTReservation(TReservation c) {
        
      return tReservationDao.createOrUpdateTReservation(c);
       
    }
     
     public TReservation findById(long id) {
        
      return tReservationDao.findById(id);
       
    }
     
     
      public List<ReservationDto> getAllReservation() {
        
      return tReservationDao.getAllReservation();
       
    }
      
       public TReservation  getLastReservation() {
        
      return tReservationDao.getLastReservation();
       
    }
    
}
