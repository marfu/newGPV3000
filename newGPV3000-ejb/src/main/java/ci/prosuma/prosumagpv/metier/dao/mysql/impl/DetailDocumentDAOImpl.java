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
//import ci.prosuma.prosumagpv.entity.client.DetailDocument;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailDocumentDAO;
//
//@Stateless
//@Local(IDetailDocumentDAO.class)
//@SuppressWarnings("unchecked")
//public class DetailDocumentDAOImpl implements IDetailDocumentDAO, Serializable {
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
//	public DetailDocument createOrUpdateDetailDocument(DetailDocument a) {
//		try{
//		DetailDocument temp = getDetailDocument(a.getId());
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
//	public DetailDocument getDetailDocument(long entityId) {
//		try {
//			DetailDocument a =  em.find(DetailDocument.class, entityId);
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
