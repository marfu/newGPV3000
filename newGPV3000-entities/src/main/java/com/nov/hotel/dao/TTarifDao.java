/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TTarif;





/**
 *
 * @author manukey
 */
public interface TTarifDao extends GenericDao<TTarif>{
    
    public TTarif findTarifByCatAndOffre(long idOffre,long idCat);
}
