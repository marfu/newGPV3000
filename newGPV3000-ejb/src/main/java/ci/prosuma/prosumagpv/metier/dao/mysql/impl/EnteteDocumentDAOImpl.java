//package ci.prosuma.prosumagpv.metier.dao.mysql.impl;
//
//import java.io.Serializable;
//
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import ci.prosuma.prosumagpv.entity.client.EnteteDocument;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteDocumentDAO;
//
//@Stateless
//@Local(IEnteteDocumentDAO.class)
//@SuppressWarnings("unchecked")
//public class EnteteDocumentDAOImpl implements IEnteteDocumentDAO, Serializable {
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
//	public EnteteDocument createOrUpdateEnteteDocument(EnteteDocument a) {
//		try{
//		EnteteDocument temp = getEnteteDocument(a.getId());
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
//	public EnteteDocument getEnteteDocument(long entityId) {
//		try {
//			EnteteDocument a =  em.find(EnteteDocument.class, entityId);
//			if(null != a){
//				if(null != a.getDetailDocument() )
//					a.getDetailDocument().size();
//			}
//				
//				
//				return a;	
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
