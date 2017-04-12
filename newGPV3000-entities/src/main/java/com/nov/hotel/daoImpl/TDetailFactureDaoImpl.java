/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.dao.TDetailFactureDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TDetailFactureDaoImpl extends GenericDaoImpl<TDetailFacture> implements TDetailFactureDao{
    
    public TDetailFactureDaoImpl() {
    }
    public TDetailFactureDaoImpl(Class<TDetailFacture> entityClass) {
        super(entityClass);
    }

    
    @Override
   public List<TDetailFacture> findTDetailFactureByFacture(long id) {
         List result = null;

        try {

            Query q = em.createQuery("SELECT d FROM TDetailFacture d WHERE d.facture = "+ id);

            
//            result = (List) (TDetailFacture) q.getResultList();
            result =  q.getResultList();
        } catch (NoResultException e) {
          System.out.println(e.getMessage());
        }

        return result;
      }
}
