/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TCategorieChambreDao;
import com.nov.hotel.dao.TChambreReservationDao;

import com.nov.hotel.entities.TChambreReservation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */

@Stateless
public class TChambreReservationService {
    
    @EJB
    private TChambreReservationDao tChambreReservationDao;
    
     public TChambreReservation CreerOrUpdateTCategorieChambre(TChambreReservation u) {
        
    
        return tChambreReservationDao.create(u);
    }
     
     
     
     public List<TChambreReservation> listOffreTarifaireByCategorie(long id) {
        
    
        return tChambreReservationDao.getAllChambreReservationByIdResev(id);
    }
     
     public TChambreReservation findChambreReser(long idre,long idCh) {
        
    
        return tChambreReservationDao.findChambreReser(idre,idCh);
    }
     
     
     public TChambreReservation findChambreOccReser(String numReservation,long idCh) {
        
    
        return tChambreReservationDao.findChambreOccReser(numReservation,idCh);
    }
     
     
     public void delete(long idre,long idCh) {
        
    
         tChambreReservationDao.deleteChambreReservation(idre, idCh);
    }
    
}
