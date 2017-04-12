/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TStatu;
import com.nov.hotel.dao.TStatuDao;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TStatuDaoImpl extends GenericDaoImpl<TStatu> implements TStatuDao{
    
    public TStatuDaoImpl() {
    }
    public TStatuDaoImpl(Class<TStatu> entityClass) {
        super(entityClass);
    }
}
