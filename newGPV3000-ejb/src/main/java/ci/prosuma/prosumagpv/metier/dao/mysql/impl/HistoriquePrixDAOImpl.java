/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import ci.prosuma.prosumagpv.entity.HistoriquePrix;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriquePrixDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tagsergi
 */

@Stateless
@Local(IHistoriquePrixDAO.class)
public class HistoriquePrixDAOImpl implements IHistoriquePrixDAO,Serializable{

    @PersistenceContext
    protected EntityManager em;
    
    @Override
    public void createHistorique(HistoriquePrix hp) {
        em.persist(hp);
    }

    @Override
    public void updateHistorique(HistoriquePrix hp) {
        em.merge(hp);
    }

    @Override
    public List<HistoriquePrix> getAllHistorique() {
        return em.createQuery("SELECT hp from HistoriquePrix hp").getResultList();
    }
    
}
