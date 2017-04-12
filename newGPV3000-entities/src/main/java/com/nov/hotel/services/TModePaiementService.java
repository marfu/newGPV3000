/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;



import com.nov.hotel.dao.TModePaiementDao;
import com.nov.hotel.entities.TArticle;
import com.nov.hotel.entities.TModePaiment;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TModePaiementService {

    @EJB
    private TModePaiementDao tModePaiementDao;


    public TModePaiment findTModePaimentById(long id) {
        return tModePaiementDao.findById(id);

    }

    public List<TModePaiment> listTModePaiment() {
        return tModePaiementDao.findAll();

    }

   
    
   

   
}
