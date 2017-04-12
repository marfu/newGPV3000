package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.PromoHistoDateDTO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;

@Stateless
@Local(IPromoDAO.class)
public class PromoDAOImpl  implements IPromoDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext
	protected EntityManager em;
	
	
	
	@Override
	public Promo createOrUpdatePromo(Promo entity) {
		Promo temp = getPromo(entity.getLibelReduit());
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
	public Promo getPromo(String entity) {
		try{
		Promo p =  em.find(Promo.class, entity);
		if(p != null && p.getPointDeVentes() != null)
			p.getPointDeVentes().size();
		
		return p;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removePromo(Promo entity) {
		Promo a = getPromo(entity.getLibelReduit());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getAllPromoForPVT(String pvt) {
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE pvts.pvtCode=:pvtCode ORDER BY a.libelReduit");
		query.setParameter("pvtCode", pvt);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> findAllPromoByMagAndStatus(String pvt){
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE pvts.pvtCode=:pvtCode  ORDER BY a.libelReduit");
		//query.setParameter("status", true);
		query.setParameter("pvtCode", pvt);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getAllPromoActiveForPVT(String pvt) {
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE pvts.pvtCode=:pvtCode AND a.actif = TRUE ORDER BY a.libelReduit");
		query.setParameter("pvtCode", pvt);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getAllPromoEnVenteForPVT(String pvt) {
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE pvts.pvtCode=:pvtCode AND a.enVente = TRUE");
		query.setParameter("pvtCode", pvt);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getAllPromoForDateRangeAndMag(Date dateDebut,
			Date dateFin, String pvtCode) {
		Query query = em.createQuery("SELECT p FROM Promo p JOIN p.pointDeVentes pvts WHERE p.dateDebutPromo >=:dateDebut    AND  p.dateFinPromo <=:dateFin AND pvts.pvtCode=:pvtCode ORDER BY p.dateDebutPromo DESC");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin",dateFin);
		return query.getResultList();
				
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getAllPromoForDateAndMag(Date date, String pvtCode) {
		Query query = em.createQuery("SELECT p FROM Promo p JOIN p.pointDeVentes pvts WHERE ( :dateJour BETWEEN p.dateDebutPromo AND  p.dateFinPromo ) AND pvts.pvtCode=:pvtCode");
		query.setParameter("pvtCode", pvtCode);
		query.setParameter("dateJour", date);
		return query.getResultList();
				
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PromoHistoDateDTO> getAllPastPromoDate(String codePromo, String codeMag) {
//		Query query = em.createQuery("SELECT NEW ci.prosuma.prosumagpv.entity.dto.PromoHistoDateDTO(hp.dateDebutPromo, hp.dateDebutFacturation, hp.dateFinFacturation, hp.dateFinPromo) FROM HistoriquePromo hp WHERE hp.libelReduit=:codemag AND hp.codeMag=: mag");
//		query.setParameter("libelReduit", codePromo);
//		query.setParameter("mag", codeMag);
//		return query.getResultList();
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PromoHistoDateDTO> getAllPastPromoDate(String codePromo, String codeMag) {
		Query query = em.createQuery("SELECT DISTINCT hp.dateDebutPromo, hp.dateDebutFacturation, hp.dateFinFacturation, hp.dateFinPromo FROM HistoriquePromo hp WHERE hp.libelReduit=:codepromo AND hp.codeMag=:mag");
		query.setParameter("codepromo", codePromo);
		query.setParameter("mag", codeMag);
		List<PromoHistoDateDTO> result = new ArrayList<>();
		List<Object[]> resultList = query.getResultList();
		for(Object[] obj : resultList){
			PromoHistoDateDTO ph = new PromoHistoDateDTO();
			ph.setDateDebutPromo((Date) obj[0]);
			ph.setDateDebutFacturation((Date) obj[1]);
			ph.setDateFinFacturation((Date) obj[2]);
			ph.setDateFinPromo((Date) obj[3]);
			result.add(ph);
		}
		return result;
	}

	@Override
	
	public Promo getPromoByCodePromoAndDateDebutFacturation(String entity,Date dateDebutPromo) {
		try{
			Promo p =  em.find(Promo.class, entity);
			if(p != null && p.getPointDeVentes() != null)
				p.getPointDeVentes().size();
			
			return p;
			} catch (Exception e) {
				return null;
			}	
	}

	@Override
	public Promo getPromoByMag(String codePromo, String Mag) {
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE a.libelReduit=:codePromo AND pvts.pvtCode=:pvtCode");
		query.setParameter("pvtCode", Mag);
		query.setParameter("codePromo", codePromo);
		try{
			return (Promo) query.getSingleResult();
		}catch(Exception ex){
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getPromoByMagAndThemePromoLike(String entity, String Mag) {
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE a.libelReduit like :codePromo AND pvts.pvtCode=:pvtCode");
		query.setParameter("pvtCode", Mag);
		query.setParameter("codePromo", entity+"%");
		try{
			return  query.getResultList();
		}catch(Exception ex){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promo> getAllPromoEnFacturationForPVT(String pvt) {
		Query query = em.createQuery("SELECT a FROM Promo a JOIN a.pointDeVentes pvts  WHERE pvts.pvtCode=:pvtCode AND a.enFacturation = TRUE");
		query.setParameter("pvtCode", pvt);
		return query.getResultList();
	}
	

	
}
