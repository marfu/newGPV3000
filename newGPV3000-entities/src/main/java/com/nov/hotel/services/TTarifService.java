/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TTarifDao;
import com.nov.hotel.entities.TTarif;
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
    
}
