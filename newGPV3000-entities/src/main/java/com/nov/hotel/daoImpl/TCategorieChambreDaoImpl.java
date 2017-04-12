/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;




import com.nov.hotel.entities.TCategorieChambre;
import com.nov.hotel.dao.TCategorieChambreDao;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TCategorieChambreDaoImpl extends GenericDaoImpl<TCategorieChambre> implements TCategorieChambreDao{
    
    public TCategorieChambreDaoImpl() {
    }
    public TCategorieChambreDaoImpl(Class<TCategorieChambre> entityClass) {
        super(entityClass);
    }

   
    
    @Override
    public TCategorieChambre createOrUpdateTCategorieChambre(TCategorieChambre u) {
        
        TCategorieChambre temp = getTCategorieChambre(u.getCatChambreId());
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
	public TCategorieChambre getTCategorieChambre(long id){
		try {
			TCategorieChambre u = em.find(TCategorieChambre.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
			return u;
		} catch (Exception e) {
			return null;
		}
	}
}
