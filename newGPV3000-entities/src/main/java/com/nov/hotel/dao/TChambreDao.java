/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.dto.InfoChambreDto;
import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.TChambre;
import java.util.Date;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TChambreDao extends GenericDao<TChambre>{
    
    public InfoChambreDto findDetailsChambre(long id,Date deb,Date fin ) ;
    
    public List<TChambre> findDetailsChambreByEtatLibre(EtatChambreEnum etat) ;
    
     public TChambre createOrUpdate(TChambre u);         
     public TChambre getChambre(long id);
     public TChambre findChambreByLib(String libelle);
}
