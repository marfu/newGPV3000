/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.dao.TDepositeDao;
import com.nov.hotel.entities.TDeposite;
import javax.ejb.Stateless;



/**
 *
 * @author manukey
 */
@Stateless
public class TDepositeDaoImpl extends GenericDaoImpl<TDeposite> implements TDepositeDao {

    public TDepositeDaoImpl() {
    }

    public TDepositeDaoImpl(Class<TDeposite> entityClass) {
        super(entityClass);
    }

    @Override
    public Double findDepositeByUser(long id) {
  

        Double results =  (Double) em.createQuery("SELECT d.montant FROM TDeposite d where d.client.cliId= "+id).getSingleResult();

        return results;    
    }

    
}
