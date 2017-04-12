/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedulerapp.batchjob;

import com.schedulerapp.common.JobInfo;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.Timer;

/**
 *
 * @author Christopher Lam
 */
@Stateless
public class BatchJobB implements BatchJobInterface
{
    static final Logger LOGGER = Logger.getLogger("BatchJobB");

    @Asynchronous
    @Override
    public void executeJob(Timer timer)
    {
        LOGGER.log(Level.INFO, "Start of BatchJobB at {0}...", new Date());
        JobInfo jobInfo = (JobInfo) timer.getInfo();
        try
        {
            LOGGER.log(Level.INFO, "Running job: {0}", jobInfo);
            Thread.sleep(30000);
        }
        catch (InterruptedException ex)
        {
        }
        LOGGER.log(Level.INFO, "End of BatchJobB at {0}", new Date());
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
