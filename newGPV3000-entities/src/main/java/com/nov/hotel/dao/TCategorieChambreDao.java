/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TCategorieChambre;





/**
 *
 * @author manukey
 */
public interface TCategorieChambreDao extends GenericDao<TCategorieChambre>{
    
  public TCategorieChambre createOrUpdateTCategorieChambre(TCategorieChambre u);         
  public TCategorieChambre getTCategorieChambre(long id);
  public TCategorieChambre findCategorieByLib(String libelle);
    
}
