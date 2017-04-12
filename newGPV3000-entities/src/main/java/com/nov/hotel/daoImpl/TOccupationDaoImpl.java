/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TOccupation;
import com.nov.hotel.dao.TOccupationDao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author manukey
 */
@Stateless
public class TOccupationDaoImpl extends GenericDaoImpl<TOccupation> implements TOccupationDao {

    public TOccupationDaoImpl() {
    }

    public TOccupationDaoImpl(Class<TOccupation> entityClass) {
        super(entityClass);
    }

    @Override
    public TOccupation createOrUpdate(TOccupation u) {

          TOccupation temp = getOccupation(u.getOccId());
        if (temp != null) {
            em.merge(u);
            em.flush();
            return u;
        } else {
            em.persist(u);
            return u;
        }

    }

    @Override
    public TOccupation getOccupation(long id) {
        try {
            TOccupation u = em.find(TOccupation.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TOccupation> listTOccupation() {
    List<TOccupation> result=new ArrayList<>();
       try {

           result =  em.createQuery("SELECT t FROM TOccupation t Where t.chambre.etat='OCCUPEE'").getResultList();
            
            
            
            
            
            
        } catch (NoResultException e) {
          
        }

       return result;    }

}
