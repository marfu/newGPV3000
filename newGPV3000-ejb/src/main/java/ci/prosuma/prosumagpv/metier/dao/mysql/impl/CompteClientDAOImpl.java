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
//import ci.prosuma.prosumagpv.entity.client.Client;
//import ci.prosuma.prosumagpv.entity.client.CompteClient;
//import ci.prosuma.prosumagpv.entity.client.EnteteDocument;
//import ci.prosuma.prosumagpv.entity.client.Incident;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDocument;
//import ci.prosuma.prosumagpv.metier.dao.mysql.ICompteClientDAO;
//
//@Stateless
//@Local(ICompteClientDAO.class)
//@SuppressWarnings("unchecked")
//public class CompteClientDAOImpl implements ICompteClientDAO, Serializable {
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
//	public CompteClient createOrUpdateCompteClient(CompteClient a) {
//		try{
//			CompteClient temp = getCompteClient(a.getId());
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
//	public CompteClient getCompteClient(long entityId) {
//		try {
//			CompteClient a =  em.find(CompteClient.class, entityId);
//			return a;	
//					
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@Override
//	public List<CompteClient> getAllCompteClients() {
//		Query query = em
//				.createQuery("SELECT a FROM CompteClient a");
//		return  query.getResultList();
//		
//	}
//
//	@Override
//	public List<Client> getAllAyantDroitForClient(long id) {
//		Query query = em.createQuery("SELECT d FROM CompteClient  a  JOIN a.clients  d WHERE a.id=:id");
//		query.setParameter("id", id);
//		return query.getResultList();
//	}
//	@Override
//	public List<EnteteDocument> getAllDocumentsForClient(long id) {
//		Query query = em.createQuery("SELECT d FROM CompteClient  a  JOIN a.documents  d WHERE a.id=:id");
//		query.setParameter("id", id);
//		return query.getResultList();
//	}
//	public List<EnteteDocument> getAllDocumentsForClientAndType(long id , TypeDocument docType) {
//		Query query = em.createQuery("SELECT d FROM CompteClient  a  JOIN a.factures  d WHERE a.id=:id AND d.typeDocument=:docType");
//		query.setParameter("id", id);
//		query.setParameter("docType", docType);
//		return query.getResultList();
//	}
//	
//
//	@Override
//	public List<Incident> getAllIncidentsForClient(long id) {
//		Query query = em.createQuery("SELECT d FROM CompteClient  a  JOIN a.incidents  d WHERE a.id=:id");
//		query.setParameter("id", id);
//		return query.getResultList();
//	}
//
//	
//	@Override
//	public CompteClient getCompteClientByNumClient(long num) {
//		Query query = em
//				.createQuery("SELECT a FROM CompteClient a WHERE a.clientPrincipal.id=:num");
//		query.setParameter("num", num);
//		
//		CompteClient c =   (CompteClient) query.getSingleResult();
//		if(null != c){
//			if(null != c.getClients())
//				c.getClients().size();
//			if(null != c.getDocuments())
//				c.getDocuments().size();
//		}
//		
//		return c;
//	}
//
//	
//
//
//
//
//
//
//}
