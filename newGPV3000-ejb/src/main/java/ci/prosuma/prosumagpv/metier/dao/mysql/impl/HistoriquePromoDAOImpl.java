package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.HistoriquePromo;
import ci.prosuma.prosumagpv.metier.dao.mysql.IHistoriquePromoDAO;

@Stateless
@Local(IHistoriquePromoDAO.class)
public class HistoriquePromoDAOImpl  implements IHistoriquePromoDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext
	protected EntityManager em;
	
	
	
	@Override
	public HistoriquePromo createOrUpdateHistoriquePromo(HistoriquePromo entity) {
		HistoriquePromo temp = getHistoriquePromo(entity);
		if (temp != null) {
			em.merge(entity);
			em.flush();
			return entity;
		} else {
			em.persist(entity);
			em.flush();
			return entity;
		}
	}

	@Override
	public HistoriquePromo getHistoriquePromo(HistoriquePromo entity) {
		try{
		HistoriquePromo p =  em.find(HistoriquePromo.class, entity.getId());
		
		
		return p;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeHistoriquePromo(HistoriquePromo entity) {
		HistoriquePromo a = getHistoriquePromo(entity);
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<HistoriquePromo> getAllHistoriquePromoForPromo(String pvtCode,String libelReduit) {
		Query query = em.createQuery("SELECT a FROM libelReduit a   WHERE a.codeMag=:pvtCode AND a.libelReduit=:libelReduit");
		query.setParameter("libelReduit", libelReduit);
		query.setParameter("pvtCode", pvtCode);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistoriquePromo> getAllHistoriquePromoForPromoAndDate(
			String pvtCode, String libelReduit, Date debutPromo, Date finPromo) {
		Query query = em.createQuery("SELECT a FROM HistoriquePromo a   WHERE a.codeMag=:pvtCode AND a.libelReduit=:libelReduit  AND a.dateDebutPromo=:debut AND a.dateFinPromo=:fin");
		query.setParameter("libelReduit", libelReduit);
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("debut", debutPromo);
		query.setParameter("fin", finPromo);
		return query.getResultList();
	}

	
}
