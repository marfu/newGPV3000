/**
 * 
 */
package ci.prosuma.prosumagpv.metier.service;

import java.util.concurrent.Future;

/**
 * @author tagsergi
 *
 */
public interface IDescenteAuto {
	
	public Future<Boolean> descenteAuto(String mag);

}
