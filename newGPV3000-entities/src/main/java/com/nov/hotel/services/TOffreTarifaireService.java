/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TOffreTarifaireDao;
import com.nov.hotel.entities.TOffreTarifaire;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */

@Stateless
public class TOffreTarifaireService {
    
    @EJB
    private TOffreTarifaireDao tOffreTarifaireDao;
    
    
     public List<TOffreTarifaire> listOffreTarifaire() {
        return tOffreTarifaireDao.findAll();

    }
     
      public TOffreTarifaire findOffreTarifaire(long id) {
        return tOffreTarifaireDao.findById(id);

    }
     
      public List<TOffreTarifaire> listOffreTarifaireByCategorieChambre(long id) {
          
          System.out.println("IIIDXXXXXXXXXXXXXXXXXXXX"+id);
        return tOffreTarifaireDao.listOffreTarifaireByCategorie(id);

    }
    
     public TOffreTarifaire CreerOrUpdateTOffreTarifaire(TOffreTarifaire u) {

        return tOffreTarifaireDao.createOrUpdateTOffreTarifaire(u);
    } 
      
    
}
