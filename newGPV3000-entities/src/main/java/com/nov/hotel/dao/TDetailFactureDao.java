/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TDetailFacture;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TDetailFactureDao extends GenericDao<TDetailFacture>{
    public List<TDetailFacture> findTDetailFactureByFacture(long id);
}
