/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TFacture;
import com.nov.hotel.dao.TFactureDao;
import com.nov.hotel.entities.TChambre;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TFactureDaoImpl extends GenericDaoImpl<TFacture> implements TFactureDao{
    
    public TFactureDaoImpl() {
    }
    public TFactureDaoImpl(Class<TFacture> entityClass) {
        super(entityClass);
    }

    @Override
    public TFacture findTFactureByChambre(TChambre tchambre) {
       TFacture tFacture =new TFacture();
       long result=0;
       try {

            Query q = em.createQuery("SELECT t.factureId FROM TOccupation t   WHERE t.chambre = ?1 ");
            q.setParameter(1, tchambre.getChId());
            
            
            result = (long) q.getSingleResult();
            Query q2 = em.createQuery("SELECT f FROM TFacture f   WHERE f.factId = ?2 ");
            q2.setParameter(1, result);
            
            tFacture= (TFacture) q.getSingleResult();
        } catch (NoResultException e) {
          System.out.println(e.getMessage());
        }

       return tFacture;
    }

    @Override
    public long findLastTFacture() {
 
       long result=0;
       try {

           result = (long) em.createQuery("SELECT t.factId FROM TFacture t ORDER BY t.factId DESC").setMaxResults(1).getSingleResult();
            
            
            
            
            
            
        } catch (NoResultException e) {
          System.out.println(e.getMessage());
        }

       return result;    }
}
