/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TService;
import com.nov.hotel.dao.TServiceDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TServiceDaoImpl extends GenericDaoImpl<TService> implements TServiceDao {

    public TServiceDaoImpl() {
    }

    public TServiceDaoImpl(Class<TService> entityClass) {
        super(entityClass);
    }

    @Override
    public List<TService> findServiceParent() {
        List result = null;

        try {
//            Query q = em.createQuery("SELECT s FROM T_SERVICE s WHERE s.TSCE_PARENT_ID is null");

            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId is null");

            result = q.getResultList();
        } catch (NoResultException e) {
            // log.debug("No result forund for... ");        }        return result;    }
        }

        return result;
    }

    @Override
    public List<TService> findServiceIntermediaire(long id) {

        List result = null;

        try {
//            Query q = em.createQuery("SELECT s FROM T_SERVICE s WHERE s.TSCE_PARENT_ID is null");
//Query q = em.createQuery("SELECT b FROM Bureau b Where b.hierachie_id= ?2");
            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId = "+ id);
//            q.setParameter(2, id);
            
            result = q.getResultList();
        } catch (NoResultException e) {
            // log.debug("No result forund for... ");        }        return result;    }
        }

        return result;
    }

   

    @Override
    public TService findServiceBylib(String lib) {
        TService result =new TService();
         try {

            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceVerification =:code ");
            q.setParameter("code", lib).setMaxResults(1);
            
            result = (TService) q.getSingleResult();
        } catch (NoResultException e) {
          
        }

        return result;

    }

    @Override
    public List<TService> findServiceTerminal(long id) {
         List result = null;

        try {

            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId = "+ id);

            
            result = q.getResultList();
        } catch (NoResultException e) {
          
        }

        return result;
      }

    @Override
    public TService createOrUpdate(TService u) {
     TService temp = getTService(u.getSceId());
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
    public TService getTService(long id) {
   try {
			TService u = em.find(TService.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		} }

    @Override
    public List<TService> findAllServiceIntermediaire() {
     List result = null;

        try {
            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId is not null");

            
            result = q.getResultList();
        } catch (NoResultException e) {
          
        }

        return result;}
}
