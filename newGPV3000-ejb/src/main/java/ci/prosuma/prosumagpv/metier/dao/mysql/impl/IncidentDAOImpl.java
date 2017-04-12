//package ci.prosuma.prosumagpv.metier.dao.mysql.impl;
//
//import java.io.Serializable;
//
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import ci.prosuma.prosumagpv.entity.client.Incident;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IIncidentDAO;
//
//@Stateless
//@Local(IIncidentDAO.class)
//@SuppressWarnings("unchecked")
//public class IncidentDAOImpl implements IIncidentDAO, Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@PersistenceContext(unitName = "GPV3000Pu")
//	protected EntityManager em;
//	
//
//	@Override
//	public Incident createOrUpdateIncident(Incident a) {
//		try{
//		Incident temp = getIncident(a.getId());
//		if (temp != null) {
//			em.merge(a);
//			em.flush();
//			return a;
//		} else {
//			em.persist(a);
//			em.flush();
//			return a;
//		}
//		}catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	@Override
//	public Incident getIncident(long entityId) {
//		try {
//			Incident a =  em.find(Incident.class, entityId);
//			return a;	
//					
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//
//
//
//
//}
