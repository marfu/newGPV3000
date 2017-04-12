/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TypeTrace;
import com.nov.hotel.dao.TypeTraceDao;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TypeTraceDaoImpl extends GenericDaoImpl<TypeTrace> implements TypeTraceDao{
    
    public TypeTraceDaoImpl() {
    }
    public TypeTraceDaoImpl(Class<TypeTrace> entityClass) {
        super(entityClass);
    }
}
