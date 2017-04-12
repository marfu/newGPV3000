/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.dao.TDepositeDao;
import com.nov.hotel.dao.TModePaiementDao;
import com.nov.hotel.entities.TDeposite;
import com.nov.hotel.entities.TModePaiment;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author manukey
 */
@Stateless
public class TModePaiementDaoImpl extends GenericDaoImpl<TModePaiment> implements TModePaiementDao {

    public TModePaiementDaoImpl() {
    }

    public TModePaiementDaoImpl(Class<TModePaiment> entityClass) {
        super(entityClass);
    }

}
