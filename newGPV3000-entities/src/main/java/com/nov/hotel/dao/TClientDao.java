/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TClient;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TClientDao extends GenericDao<TClient>{
    public TClient createOrUpdateTClient(TClient u);
    
    public TClient getUser(long id);
    
     public TClient listTclient(long id);
}
