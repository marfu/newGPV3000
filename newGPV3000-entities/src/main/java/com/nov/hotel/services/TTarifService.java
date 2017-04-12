/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TTarifDao;
import com.nov.hotel.entities.TTarif;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */

@Stateless
public class TTarifService {
    
    @EJB
    private TTarifDao tTarifDao;
    
    
     public TTarif findOffreTarifaire(long idOffre,long idCat) {
        return tTarifDao.findTarifByCatAndOffre(idOffre, idCat);

    }
     
       public List<TTarif> findAllOffreTarifaire() {
        return tTarifDao.findAll();
    }
     
      public TTarif CreerOrUpdate(TTarif c) {
        
      return tTarifDao.createOrUpdate(c);
       
    }
       public TTarif findOffreTarifaireByID(long id) {
        return tTarifDao.findById(id);
    }
     
    
}
