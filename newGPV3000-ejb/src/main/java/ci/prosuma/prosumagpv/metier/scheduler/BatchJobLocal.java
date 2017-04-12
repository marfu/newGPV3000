/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.scheduler;

import javax.ejb.Local;
import javax.ejb.Timer;

/**
 *
 * @author tagsergi
 */
@Local
public interface BatchJobLocal {
    
    public void executeJob(Timer timer);
    
}
