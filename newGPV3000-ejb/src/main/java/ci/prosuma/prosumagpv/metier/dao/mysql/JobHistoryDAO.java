/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.scheduler.JobHistory;
import java.util.List;

/**
 *
 * @author tagsergi
 */


public interface JobHistoryDAO {
    
    public JobHistory createJobHistory(JobHistory jobInfo);
    
    public JobHistory updateJobHistory(JobHistory jobInfo);
    
    public void deleteJobHistory (JobHistory jobInfo);
    
    public JobHistory getJobHistoryByid(long id);
    
    public List<JobHistory> getAllJobHistory ();
    
    
    
}
