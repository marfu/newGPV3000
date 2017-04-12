//package ci.prosuma.metier.dataexchange.jms;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.ObjectMessage;
//import javax.jms.Queue;
//import javax.jms.Session;
//
//import org.jboss.logging.Logger;
//
//import ci.prosuma.prosumagpv.entity.dto.VersionDTO;
//
//@Local(IGPV3000APPJMSSender.class)
//@Stateless
//public class GPV3000APPJMSSenderImpl implements IGPV3000APPJMSSender,
//		Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private static Logger log = Logger.getLogger(GPV3000APPJMSSenderImpl.class);
//
//	@Resource(mappedName = "ConnectionFactory")
//	private ConnectionFactory connectionFactory;
//
//	@Resource(mappedName = "queue/GPV3000APPQueue")
//	private Queue gpv3000APPQueue;
//
//	@Override
//	public boolean sendEntity(List<Serializable> listObject) {
//		Connection connection = null;
//		try {
//
//			connection = connectionFactory.createConnection();
//			Session session = connection.createSession(false,Session.DUPS_OK_ACKNOWLEDGE);
//			MessageProducer producer = null;
//			producer = session.createProducer(gpv3000APPQueue);
//			
//			for (Serializable o : listObject) {
//				ObjectMessage message = session.createObjectMessage();
//				message.setObject(o);
//				setJMSPriority(message, o);
//				producer.send(message);
//			}
//
//			log.debug("message  sended");
//
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			try {
//				connection.close();
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	private void setJMSPriority(ObjectMessage message, Serializable o) {
//		try {
//			
//			if(o instanceof VersionDTO){
//				if(((VersionDTO) o).getTYPE().equals("SEC"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("RAY"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("FAM"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("SFA"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("IFL"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("TTV"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("TTT"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("OMV"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("CMV"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("CAC"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("RGL"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("DEP"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("BF"))
//					message.setJMSPriority(7);
//				if(((VersionDTO) o).getTYPE().equals("BT"))
//					message.setJMSPriority(9);
//				if(((VersionDTO) o).getTYPE().equals("BAA"))
//					message.setJMSPriority(8);
//				if(((VersionDTO) o).getTYPE().equals("BAB"))
//					message.setJMSPriority(5);
//				if(((VersionDTO) o).getTYPE().equals("BAC"))
//					message.setJMSPriority(6);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	@Override
//	public boolean sendUniqueEntity(Serializable object) {
//		Connection connection = null;
//		try {
//			
//			connection = connectionFactory.createConnection();
//			Session session = connection.createSession(false,
//					Session.DUPS_OK_ACKNOWLEDGE);
//			MessageProducer producer = null;
//			producer = session.createProducer(gpv3000APPQueue);
//			
//			
//				ObjectMessage message = session.createObjectMessage();
//				message.setObject(object);
//				producer.send(message);
//			
//			
//			log.debug("message  sended");
//			
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			try {
//				connection.close();
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
//	
//	
//
//}
