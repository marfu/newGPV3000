//package ci.prosuma.prosumagpv.metier.dao.mysql.impl;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import ci.prosuma.prosumagpv.entity.Article;
//import ci.prosuma.prosumagpv.entity.client.Client;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IClientDAO;
//
//@Stateless
//@Local(IClientDAO.class)
//@SuppressWarnings("unchecked")
//public class ClientDAOImpl implements IClientDAO, Serializable {
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
//	public Client createOrUpdateClient(Client a) {
//		try{
//		Client temp = getClient(a.getId());
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
//	public Client getClient(long entityId) {
//		try {
//			Client a =  em.find(Client.class, entityId);
//			return a;	
//					
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@Override
//	public List<Client> getAllClients() {
//		Query query = em
//				.createQuery("SELECT a FROM Client a");
//		return  query.getResultList();
//		
//	}
//
//
//
//
//}
