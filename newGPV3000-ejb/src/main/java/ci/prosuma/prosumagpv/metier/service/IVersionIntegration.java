/**
 * 
 */
package ci.prosuma.prosumagpv.metier.service;

import java.util.concurrent.Future;

/**
 * @author tagsergi
 *
 */
public interface IVersionIntegration {
	
	public Future<Boolean> integrateVersion(String mag);
    
    

}
