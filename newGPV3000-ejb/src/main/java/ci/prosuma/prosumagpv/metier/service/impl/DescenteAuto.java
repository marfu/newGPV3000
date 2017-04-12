/**
 * 
 */
package ci.prosuma.prosumagpv.metier.service.impl;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import ci.prosuma.prosumagpv.metier.service.IDescenteAuto;
import ci.prosuma.prosumagpv.metier.service.IPromoService;
import ci.prosuma.prosumagpv.metier.service.IUtilService;

/**
 * @author tagsergi
 *
 */

@Stateless
@Local(IDescenteAuto.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DescenteAuto implements IDescenteAuto {

	
	
	public static Logger logger = Logger.getLogger(DescenteAuto.class);
	
	
	@EJB
	IUtilService utilService;
	
	@EJB
	private IPromoService promoService;
	
	/* (non-Javadoc)
	 * @see ci.prosuma.prosumagpv.metier.service.IDescenteAuto#descenteAuto(java.lang.String)
	 */
	@Asynchronous
	@Override
	public Future<Boolean> descenteAuto(String mag) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public void descenteQuotidienne(String mag) {
//
//		String ip = getSecurityBean().getPvtTemp().getCodeIP();
//
//		// si il n'y a pas de descente en cours
//		if (getStatusDescente(mag) == 0) {
//			// mise a jour status descente
//			try {
//
//				updateStatus(mag, "descente", 1);
//				logger.info("Début de la descente quotidienne vers SA");	
//				
//				promoService.calculArticlePromo(mag);//desactive article dont promo echue
//				promoService.calculPromoAuto(getSecurityBean().getMag());// active les articles avec nouvelle prom
//
//				//promoService.UpdatePromoZero(); //AJoutée par Ferdinand
//				
//				saService.beginToTPVUpdate(ip, mag);// debut creation du fichier de descente (article)
//				
////				List<Promo> list = promoService.getAllPromoForDateAndMag(new Date(System.currentTimeMillis()), getSecurityBean().getMag());
////				if (list != null)
////					saService.uploadToTPVListPromo(list, mag);// debut creation du fichier de descente (promo)
//
//				logger.info("Descente quotidienne effectuée avec succès");
//
//			} catch (Exception e) {
//				SecurityBean.addExceptionMessage();
//			} finally {
//				// remise a zero du marqueur
//				updateStatus(mag, "descente", 0);
//			}
//		} else {
//			SecurityBean
//					.addInfoMessage("Descente quotidienne vers les caisses en cours, veuillez patienter ...");
//		}
//
//	}
	
	private int getStatusDescente(String mag) {
		if (utilService.getSqlRequestByLibelle(mag + ".descente").equals("0")) {
			return 0;
		} else {
			return 1;
		}

	}
	
	private void updateStatus(String mag, String libel, int seq) {
		utilService.executeUpdateSequence(mag + "." + libel, seq);
	}

}
