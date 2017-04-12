/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TTrace;
import com.nov.hotel.dao.TTraceDao;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TTraceDaoImpl extends GenericDaoImpl<TTrace> implements TTraceDao{
    
    public TTraceDaoImpl() {
    }
    public TTraceDaoImpl(Class<TTrace> entityClass) {
        super(entityClass);
    }
}
