/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TCategorieChambreDao;
import com.nov.hotel.dao.TChambreDao;
import com.nov.hotel.dto.InfoChambreDto;
import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.TCategorieChambre;
import com.nov.hotel.entities.TChambre;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TChambreService {

    @EJB
    private TChambreDao tChambreDao;

    @EJB
    private TCategorieChambreDao tCategorieChambreDao;

    private String chLib;

    private String chNumeroChambre;

    private Date chDateCreate;

    private Date chDateModif;

    private EtatChambreEnum etat;

    private TCategorieChambre chCategorie;

    public TChambre CreerTChambreDao(String chLib, String chNumeroChambre, TCategorieChambre chCategorie,Date chDateCreate,EtatChambreEnum etat) {
      
        TChambre c = new TChambre();
        c.setEtat(etat);
        c.setChCategorie(chCategorie);
        c.setChDateCreate(chDateCreate);
        c.setChLib(chLib);
        c.setChNumeroChambre(chNumeroChambre);
        return c = tChambreDao.create(c);
    }
    
    
     public TChambre CreerOrUpdate(TChambre c) {
        
      return tChambreDao.createOrUpdate(c);
       
    }
    
     public InfoChambreDto findDetailsChambre(long id,Date dateDebut,Date dateFin) {
      
        return  tChambreDao.findDetailsChambre(id,dateDebut,dateFin);
    }

    public List<TChambre> listChambre() {
        return tChambreDao.findAll();

    }
    
    public List<TChambre> listChambreByEtat(EtatChambreEnum etat) {
        return tChambreDao. findDetailsChambreByEtatLibre(etat);

    }
    
     public TChambre findChambreById(long id) {
        return tChambreDao.findById(id);

    }

    public void deleteChambre(Long selectCandidat) {

    }

    public void updateChambre(Long selectParti, String code, String libelle, String logo) {

    }
    
     public TChambre finbyIDChambre(long ID) {
        return tChambreDao.findById(ID);

    }
}
