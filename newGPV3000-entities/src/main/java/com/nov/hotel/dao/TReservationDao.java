/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.dto.ReservationDto;
import com.nov.hotel.entities.TReservation;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TReservationDao extends GenericDao<TReservation>{
    
    
    public TReservation createOrUpdateTReservation(TReservation u);
    
     public TReservation getTReservation(long id);
     
      public TReservation getLastReservation();
     
      public List<ReservationDto> getAllReservation();
    
}
