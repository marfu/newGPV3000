/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TOffreTarifaire;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TOffreTarifaireDao extends GenericDao<TOffreTarifaire>{
    
    public List<TOffreTarifaire> listOffreTarifaireByCategorie(long id); 
    
      public TOffreTarifaire createOrUpdateTOffreTarifaire(TOffreTarifaire u);         
  public TOffreTarifaire getTOffreTarifaire(long id);
  public TOffreTarifaire findOffreTarifaireByLib(String libelle);
}
