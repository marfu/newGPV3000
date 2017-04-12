/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.dao.TDetailFactureDao;
import javax.ejb.Stateless;

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
}
