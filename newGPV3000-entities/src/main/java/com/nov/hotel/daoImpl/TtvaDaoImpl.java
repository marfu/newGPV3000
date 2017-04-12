/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TOffreTarifaire;

import com.nov.hotel.dao.TtvaDao;
import com.nov.hotel.entities.Ttva;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TtvaDaoImpl extends GenericDaoImpl<Ttva> implements TtvaDao {

    public TtvaDaoImpl() {
    }

    public TtvaDaoImpl(Class<Ttva> entityClass) {
        super(entityClass);
    }

    @Override
    public Ttva getTva() {
       Ttva results = (Ttva) em.createQuery("SELECT cr FROM Ttva cr ").setMaxResults(1).getSingleResult();
        return results;
    }

}
