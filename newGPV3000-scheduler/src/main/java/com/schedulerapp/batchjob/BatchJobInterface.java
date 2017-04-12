/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedulerapp.batchjob;

import javax.ejb.Local;
import javax.ejb.Timer;

/**
 *
 * @author Christopher Lam
 */
@Local
public interface BatchJobInterface
{
    //@Asynchronous
    public void executeJob(Timer timer);
}
