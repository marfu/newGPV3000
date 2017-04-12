/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.scheduler;

import javax.ejb.Stateless;
import javax.ejb.Timer;

/**
 *
 * @author tagsergi
 */
@Stateless
public class BatchJob implements BatchJobLocal {

    @Override
    public void executeJob(Timer timer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
