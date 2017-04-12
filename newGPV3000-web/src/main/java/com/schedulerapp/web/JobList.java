/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedulerapp.web;

import com.schedulerapp.common.JobInfo;
import com.schedulerapp.ejb.JobSessionBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Christopher Lam
 */
@ManagedBean(name = "JobList")
@RequestScoped
public class JobList implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    @EJB
    private JobSessionBean jobSessionBean;
    private List<JobInfo> jobList = null;

    /** Creates a new instance of JobList */
    public JobList()
    {
    }

    @PostConstruct
    public void initialize()
    {
        System.out.println("JobList - initialize() called!");
        
        jobList = jobSessionBean.getJobList();
    }

    /*
     * Returns a list of active Jobs/Timers
     */
    public List<JobInfo> getJobs()
    {
        System.out.println("JobList - getJobs() called!");
        return jobList;
    }
}
