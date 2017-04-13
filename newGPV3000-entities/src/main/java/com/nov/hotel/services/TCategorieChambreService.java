/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TCategorieChambreDao;
import com.nov.hotel.entities.TCategorieChambre;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TCategorieChambreService {

    @EJB
    private TCategorieChambreDao tCategorieChambreDao;

 
     public TCategorieChambre CreerTCategorieChambre(String catChambreLib, String catChambreDesc, Date catChambreDateCrea) {
        TCategorieChambre cc = new TCategorieChambre();
        cc.setCatChambreDateCrea(catChambreDateCrea);
        
        cc.setCatChambreDesc(catChambreDesc);
        cc.setCatChambreLib(catChambreLib);
        
        return cc=tCategorieChambreDao.create(cc);
    }


    public TCategorieChambre CreerOrUpdateTCategorieChambre(TCategorieChambre u) {
        
    
        return tCategorieChambreDao.createOrUpdateTCategorieChambre(u);
    }

    public List<TCategorieChambre> listTCategorieChambre() {
        return tCategorieChambreDao.findAll();

    }
    
    public List<TCategorieChambre> categorieChambreById(long id) {
        List<TCategorieChambre> listCat=new ArrayList<TCategorieChambre>();
        listCat.add(tCategorieChambreDao.findById(id));
        return listCat;

    }
    
        public TCategorieChambre finbyIDCategorieChambre(long ID){
        return tCategorieChambreDao.findById(ID);
    }

        public TCategorieChambre finbyCategorieChambreByLib(String libelle){
        return tCategorieChambreDao.findCategorieByLib(libelle);
    }
}
