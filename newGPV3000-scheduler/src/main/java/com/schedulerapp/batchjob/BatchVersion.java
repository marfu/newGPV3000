/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedulerapp.batchjob;

import ci.prosuma.prosumagpv.entity.scheduler.JobHistory;
import ci.prosuma.prosumagpv.metier.dao.mysql.JobHistoryDAO;
import ci.prosuma.prosumagpv.metier.service.IVersionIntegration;
import com.schedulerapp.common.JobInfo;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author tagsergi
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BatchVersion implements BatchJobInterface{

    
    static final Logger LOGGER = Logger.getLogger("BatchVersion");
    
    @EJB
    private IVersionIntegration versionService;
    
    @EJB
    private JobHistoryDAO jobHistoryDAO;
    
    @Asynchronous
    @Override
    public void executeJob(Timer timer) {
        LOGGER.log(Level.INFO, "Start of BatchVersion at {0}...", new Date());
        String error = "";
        JobInfo jobInfo = (JobInfo) timer.getInfo();
        JobHistory histo = new JobHistory();
        histo.setJob(new ci.prosuma.prosumagpv.entity.scheduler.JobInfo(Long.parseLong(jobInfo.getJobId()),
                jobInfo.getJobName(), jobInfo.getJobClassName()));
        histo.setStartDate(new Date());
        jobHistoryDAO.createJobHistory(histo);
         try
        {
            LOGGER.log(Level.INFO, "Running job: {0}", jobInfo);
            versionService.integrateVersion("100");
        }catch(Exception ex){
            error=ex.getMessage();
        }
        finally{
             histo.setEndDate(new Date());
             histo.setErrorMessage(error);
             jobHistoryDAO.updateJobHistory(histo);
         }
        LOGGER.log(Level.INFO, "End of BatchVersion at {0}", new Date());
        
        histo.setEndDate(new Date());
    }
    
}
