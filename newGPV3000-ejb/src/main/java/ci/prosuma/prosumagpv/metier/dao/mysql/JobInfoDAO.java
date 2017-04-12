/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.scheduler.JobInfo;
import java.util.List;

/**
 *
 * @author tagsergi
 */
public interface JobInfoDAO {
    
    public JobInfo createJob(JobInfo jobInfo);
    
    public JobInfo updateJob(JobInfo jobInfo);
    
    public void deleteJob(JobInfo jobInfo);
    
    public JobInfo getJobByid(long id);
    
    public List<JobInfo> getAllJob();
    
    
    
}
