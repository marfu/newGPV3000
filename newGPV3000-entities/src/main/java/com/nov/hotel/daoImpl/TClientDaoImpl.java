/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TClient;
import com.nov.hotel.dao.TClientDao;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TClientDaoImpl extends GenericDaoImpl<TClient> implements TClientDao {

    public TClientDaoImpl() {
    }

    public TClientDaoImpl(Class<TClient> entityClass) {
        super(entityClass);
    }

    @Override
    public TClient createOrUpdateTClient(TClient u) {
        
        TClient temp = getUser(u.getCliId());
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
	public TClient getUser(long id) {
		try {
			TClient u = em.find(TClient.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		}
	}

    @Override
    public TClient listTclient(long id) {
       TClient results =  (TClient) em.createQuery("SELECT cr FROM TClient cr where cr.cliId="+id).getSingleResult();
       return results;
    }
}
