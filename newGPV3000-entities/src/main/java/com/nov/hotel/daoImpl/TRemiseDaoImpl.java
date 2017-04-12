/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.dao.TRemiseDao;
import com.nov.hotel.entities.TRemise;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TRemiseDaoImpl extends GenericDaoImpl<TRemise> implements TRemiseDao{

    public TRemiseDaoImpl() {
    }

    public TRemiseDaoImpl(Class<TRemise> entityClass) {
        super(entityClass);
    }

   

    @Override
    public TRemise findTRemiseBytauxZ() {
        TRemise result= new TRemise();
        try {

            Query q = em.createQuery("SELECT r FROM TRemise r   WHERE r.remiseTaux = 0 ");

            
            result = (TRemise) q.getSingleResult();
        } catch (NoResultException e) {
          
        }

        return result;  }
}
