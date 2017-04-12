//package ci.prosuma.prosumagpv.metier.util.app.jms;
//
//import java.io.Serializable;
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
//import ci.prosuma.prosumagpv.entity.dto.BonCommandeDTO;
//import ci.prosuma.prosumagpv.entity.dto.InventaireDTO;
//
//@Local(IMessageSender.class)
//@Stateless
//public class MessageSenderImpl implements IMessageSender, Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private static Logger log = Logger.getLogger(MessageSenderImpl.class);
//
//	@Resource(mappedName = "ConnectionFactory")
//	private ConnectionFactory connectionFactory;
//
//	
//
//	@Resource(mappedName = "queue/CommandeQueue")
//	private Queue queueCommande;
//	
//	@Resource(mappedName = "queue/GPV3000APPQueue")
//	private Queue queueGPV3000;
//
//	@Override
//	public boolean sendCommandeCentrale(BonCommandeDTO bcDTO) {
//		Connection connection =null;
//		try {
//
//			connection = connectionFactory.createConnection();
//			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//			MessageProducer producer = null;
//			producer = session.createProducer(queueCommande);
//			
//
//			ObjectMessage message = session.createObjectMessage();
//			message.setObject(bcDTO);
//			producer.send(message);
//			log.debug("message commande centrale sended");
//			
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}finally{
//			try {
//				connection.close();
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//	@Override
//	public boolean sendInventaireDetail(InventaireDTO invDTO) {
//		Connection connection =null;
//		try {
//			
//			connection = connectionFactory.createConnection();
//			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//			MessageProducer producer = null;
//			producer = session.createProducer(queueGPV3000);
//			
//			
//			ObjectMessage message = session.createObjectMessage();
//			message.setObject(invDTO);
//			producer.send(message);
//			
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}finally{
//			try {
//				connection.close();
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
//
//}
