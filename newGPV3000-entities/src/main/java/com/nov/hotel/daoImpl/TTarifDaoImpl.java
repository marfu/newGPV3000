/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TTarif;
import com.nov.hotel.dao.TTarifDao;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TTarifDaoImpl extends GenericDaoImpl<TTarif> implements TTarifDao {

    public TTarifDaoImpl() {
    }

    public TTarifDaoImpl(Class<TTarif> entityClass) {
        super(entityClass);
    }

    @Override
    public TTarif findTarifByCatAndOffre(long idOffre, long idCat) {
        TTarif results = (TTarif) em.createQuery("SELECT u FROM TTarif u where u.offre.offreId="+idOffre+" and u.chCategorie.catChambreId="+idCat).getSingleResult();

        return results;
    }

    
     @Override
    public TTarif createOrUpdate(TTarif u) {
        
        TTarif temp = getTTarif(u.getTarifId());
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
	public TTarif getTTarif(long id) {
		try {
			TTarif u = em.find(TTarif.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		}
	}
}
