package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.LienPromoPvt;
import ci.prosuma.prosumagpv.entity.pk.PromoPvtRefPK;
import ci.prosuma.prosumagpv.metier.dao.mysql.ILienPromoPvtDAO;

@Stateless
@Local(ILienPromoPvtDAO.class)
public class LienPromoPvtDAOImpl implements ILienPromoPvtDAO , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public LienPromoPvt createOrUpdateLPP(LienPromoPvt lpp) {
		LienPromoPvt temp = getLPP(new PromoPvtRefPK(lpp.getPvt(),lpp.getPromo()));
		if (temp != null) {
			em.merge(lpp);
			em.flush();
			return lpp;
		} else {
			em.persist(lpp);
			em.flush();
			return lpp;
		}
	}

	@Override
	public LienPromoPvt getLPP(PromoPvtRefPK id) {
		return em.find(LienPromoPvt.class, id);
	}

	@Override
	public boolean removeLPP(LienPromoPvt lpp) {
		LienPromoPvt a = getLPP(new PromoPvtRefPK(lpp.getPvt(),lpp.getPromo()));
		if(a != null){
			em.remove(a);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public LienPromoPvt getLienPromoPvtByPromoAndPvt(String codePvt,String promo) {
		try{
		Query  query =  em.createQuery("SELECT a FROM LienPromoPvt a WHERE a.pvt=:codePvt AND a.promo=:promo ");
		query.setParameter("codePvt", codePvt);
		query.setParameter("promo", promo);
		return  (LienPromoPvt) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllPromoForPvt(String codePvt) {
		Query  query =  em.createQuery("SELECT a.promo FROM LienPromoPvt a WHERE a.pvt=:codePvt ");
		query.setParameter("codePvt", codePvt);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllPvtForPromo(String promo) {
		Query  query =  em.createQuery("SELECT a.article FROM LienPromoPvt a WHERE a.promo=:promo ");
		query.setParameter("promo", promo);
		return query.getResultList();
	}

}
