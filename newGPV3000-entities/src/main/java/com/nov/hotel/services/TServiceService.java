/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TServiceDao;

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
public class TServiceService {

    @EJB
    private TServiceDao tServiceDao;


    public TService CreerTService(String sceNom,Boolean isService,Date sceDateCreate, double scePrix,TService tService) {

        TService s = new TService();
        s.setIsService(isService);
        s.setSceDateCreate(sceDateCreate);
        s.setSceNom(sceNom);
        
        s.setScePrix(scePrix);
        if(tService!= null){
          s.setSceParentId(tService);
        }

        return s = tServiceDao.create(s);
    }

    public List<TService> listTService() {
        return tServiceDao.findAll();

    }

    public TService findByIDService(long ID) {
        return tServiceDao.findById(ID);
    }
    
    
    
    public List<TService> listTServiceParent() {
        return tServiceDao.findServiceParent();
    }
    public List<TService> listTServiceIntermediaire(long ID) {
        return tServiceDao.findServiceIntermediaire(ID);
    }
    public List<TService> listTServiceTerminal(long ID) {
        return tServiceDao.findServiceTerminal(ID);
    }

    public void deleteTService() {

    }

    public void updateTService() {

    }
    
     public TService findByLibService(String lib) {
        return tServiceDao.findServiceBylib(lib);
    }
     
     public List<TService> listAllTServiceIntermediaire() {
        return tServiceDao.findAllServiceIntermediaire();
    }
     
      public TService CreerOrUpdate(TService c) {
        
      return tServiceDao.createOrUpdate(c);
       
    }
      
}
