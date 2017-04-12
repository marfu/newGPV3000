/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.dao.TArticleDao;
import com.nov.hotel.entities.TArticle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TArticleDaoImpl extends GenericDaoImpl<TArticle> implements TArticleDao {

    public TArticleDaoImpl() {
    }

    public TArticleDaoImpl(Class<TArticle> entityClass) {
        super(entityClass);
    }

    @Override
    public List<TArticle> findServiceParent() {
        List result = null;

        try {


            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId is null");

            result = q.getResultList();
        } catch (NoResultException e) {
            // log.debug("No result forund for... ");        }        return result;    }
        }

        return result;
    }

    @Override
    public List<TArticle> findServiceIntermediaire(long id) {

        List result = null;

        try {

            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId = "+ id);

            
            result = q.getResultList();
        } catch (NoResultException e) {
            // log.debug("No result forund for... ");        }        return result;    }
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<TArticle> findServiceTerminal(long id) {
         List result = null;

        try {

            Query q = em.createQuery("SELECT s FROM TService s WHERE s.sceParentId = "+ id);

            
            result = q.getResultList();
        } catch (NoResultException e) {
          System.out.println(e.getMessage());
        }

        return result;
      }

    @Override
    public TArticle createOrUpdate(TArticle u) {
     TArticle temp = getTArticle(u.getArtId());
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
    public TArticle getTArticle(long id) {
   try {
			TArticle u = em.find(TArticle.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		} }

    @Override
    public TArticle findByLibArticle(String lib) {
     TArticle result =new TArticle();
         try {

            Query q = em.createQuery("SELECT s FROM TArticle s WHERE s.artNom =:code ");
            q.setParameter("code", lib).setMaxResults(1);
            
            result = (TArticle) q.getSingleResult();
        } catch (NoResultException e) {
          
        }

        return result;
}}
