/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;

import com.nov.hotel.entities.TService;
import java.util.List;

/**
 *
 * @author manukey
 */
public interface TServiceDao extends GenericDao<TService> {

    public List<TService> findServiceParent();

    public List<TService> findServiceIntermediaire(long id);

    public List<TService> findServiceTerminal(long id);

    public TService findServiceBylib(String lib);

    public List<TService> findAllServiceIntermediaire();

    public TService createOrUpdate(TService u);

    public TService getTService(long id);

}
