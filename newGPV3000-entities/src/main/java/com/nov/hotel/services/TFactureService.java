/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TFactureDao;
import com.nov.hotel.dao.TServiceDao;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TFacture;

import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TService;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TFactureService {

    @EJB
    private TFactureDao tFactureDao;

	
    public TFacture CreerTFacture(TFacture tfacture) {

    //    TFacture s = new TFacture();
       
       

        return tFactureDao.create(tfacture);
    }
    public TFacture UpdateTFacture(TFacture tfacture) {

        return tFactureDao.update(tfacture);
    }

    public List<TFacture> listTFacture() {
        return tFactureDao.findAll();

    }
    public TFacture findTFactureByChambre(TChambre tChambre) {
        return tFactureDao.findTFactureByChambre(tChambre);

    }

    public TFacture findByIDTFacture(long ID) {
        return tFactureDao.findById(ID);
    }
    
    public long findLastTFacture() {
        return tFactureDao.findLastTFacture();
    }
    
    

    

    
}
