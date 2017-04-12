/**
 * 
 */
package ci.prosuma.prosumagpv.metier.service.impl;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import ci.prosuma.prosumagpv.metier.service.IUtilService;
import ci.prosuma.prosumagpv.metier.service.IVersionIntegration;
import ci.prosuma.prosumagpv.metier.service.IVersionService;

/**
 * @author tagsergi
 * 
 */

@Stateless
@Local(IVersionIntegration.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VersionIntegration implements IVersionIntegration{
	
	public static Logger logger = Logger.getLogger(VersionIntegration.class);

	@EJB
	IUtilService utilService;

	@EJB
	IVersionService versionService;
	
	

	
	@Asynchronous
	@Override
	public Future<Boolean> integrateVersion(String mag) {
		System.out.print("##### OK");
		logger.info("##### OK");
		// remise a zero des marqueurs
		//String mag = "035";
		
		logger.info("##### resetAllTablesForIntegration(mag) : "+mag);
		versionService.resetAllTablesForIntegration(mag);
		boolean resultat = true;

		// si il n'y a pas de version en cours
		logger.info("#### debut de check");
		if (getStatusVersion(mag) == 0) {
			logger.info("D\u00E9but de l'int\u00E9gration de la version");
			// mise a jour status version
			updateStatus(mag, "version", 1);
			try {
				boolean result = versionService.integreVersion(mag);
				if (result) {
					logger.info("Version int\u00E9grer avec success ");
				}
			} catch (Exception e) {
				updateStatus(mag, "version", 0);
				logger.error("Une erreur est survenue durant l' int\u00E9gration de la version veuillez contacter le service informatique");
				e.printStackTrace();
			}
			updateStatus(mag, "version", 0);
		} else {
			logger.info("Processus d'int\u00E9gration d\u00E9j\u00E0 encours");
		}
		logger.info("#### Fin de la m\u00E9thode");
		return  new AsyncResult<>(resultat);
	}

	public void updateStatus(String mag, String libel, int seq) {
		utilService.executeUpdateSequence(mag + "." + libel, seq);
	}

	public int getStatusVersion(String mag) {
		if (utilService.getSqlRequestByLibelle(mag + ".version").equals("0")) {
			return 0;
		} else {
			return 1;
		}

	}
}
