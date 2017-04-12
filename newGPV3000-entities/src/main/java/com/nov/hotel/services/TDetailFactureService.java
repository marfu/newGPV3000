package com.nov.hotel.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.nov.hotel.dao.TDetailFactureDao;
import com.nov.hotel.dao.TFactureDao;
import com.nov.hotel.dao.TServiceDao;
import com.nov.hotel.entities.TDetailFacture;
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
public class TDetailFactureService {

    @EJB
    private TDetailFactureDao tDetailFactureDao;
    @EJB
    private TFactureDao tFactureDao;

	

    public TDetailFacture CreerTDetailFacture(TDetailFacture tdetailFacture) {

        

        return tDetailFactureDao.create(tdetailFacture);
    }

    public List<TDetailFacture> listTDetailFacture() {
        return tDetailFactureDao.findAll();

    }

    public TDetailFacture findByIDTDetailFacture(long ID) {
        return tDetailFactureDao.findById(ID);
    }
    
    

    

    
}
