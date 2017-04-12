/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedulerapp.ejb;

import com.schedulerapp.common.JobInfo;
import com.schedulerapp.batchjob.BatchJobInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Serge AYEPI
 */
@Stateless
@LocalBean
public class JobSessionBean
{
    @Resource
    TimerService timerService;  //Resource Injection
    
    static final Logger LOGGER = Logger.getLogger("JobSessionBean");

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /*
    @PostConstruct
    public void initialise()
    {
        System.out.println("###PostConstruct - calling TimerSessionBean...");
    }

    @PreDestroy
    public void cleanup()
    {
        System.out.println("###Predestroy - cleaning up...");
    }
    ***/

    /*
     * Callback method for the timers.
     */
    @Timeout
    public void timeout(Timer timer)
    {
        System.out.println("###Timer <" + timer.getInfo() + "> timeout at " + new Date());
        try
        {
            JobInfo jobInfo = (JobInfo) timer.getInfo();
            BatchJobInterface batchJob = (BatchJobInterface) InitialContext.doLookup(jobInfo.getJobClassName());
            batchJob.executeJob(timer); //Asynchronous method
        }
        catch (NamingException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        catch (IllegalStateException | EJBException ex1)
        {
            LOGGER.log(Level.SEVERE, "Exception caught: {0}", ex1);
        }
    }

    /*
     * Returns the Timer object based on the given JobInfo
     */
    private Timer getTimer(JobInfo jobInfo)
    {
        Collection<Timer> timers = timerService.getTimers();
        for (Timer t : timers)
        {
            if (jobInfo.equals((JobInfo) t.getInfo()))
            {
                return t;
            }
        }
        return null;
    }

    /*
     * Creates a timer based on the information in the JobInfo
     */
    public JobInfo createJob(JobInfo jobInfo)
            throws Exception
    {
        //Check for duplicates
        if (getTimer(jobInfo) != null)
        {
            throw new DuplicateKeyException("Job with the ID already exist!");
        }

        TimerConfig timerAConf = new TimerConfig(jobInfo, true);

        ScheduleExpression schedExp = new ScheduleExpression();
        schedExp.start(jobInfo.getStartDate());
        schedExp.end(jobInfo.getEndDate());
        schedExp.second(jobInfo.getSecond());
        schedExp.minute(jobInfo.getMinute());
        schedExp.hour(jobInfo.getHour());
        schedExp.dayOfMonth(jobInfo.getDayOfMonth());
        schedExp.month(jobInfo.getMonth());
        schedExp.year(jobInfo.getYear());
        schedExp.dayOfWeek(jobInfo.getDayOfWeek());
        LOGGER.log(Level.INFO, "### Scheduler expr: {0}", schedExp.toString());
        Timer newTimer = timerService.createCalendarTimer(schedExp, timerAConf);
        JobInfo ji = (JobInfo)newTimer.getInfo();
        LOGGER.log(Level.INFO, "New timer created: {0}", ji);
        ji.setNextTimeout(newTimer.getNextTimeout());

        return ji;
    }

    /*
     * Returns a list of JobInfo for the active timers
     */
    public List<JobInfo> getJobList()
    {
        LOGGER.info("getJobList() called!!!");
        ArrayList<JobInfo> jobList = new ArrayList<>();
        Collection<Timer> timers = timerService.getAllTimers();
        for (Timer t : timers)
        {
            JobInfo jobInfo = (JobInfo) t.getInfo();
            jobInfo.setNextTimeout(t.getNextTimeout());
            jobList.add(jobInfo);
        }
        return jobList;
    }

    /*
     * Returns the updated JobInfo from the timer
     */
    public JobInfo getJobInfo(JobInfo jobInfo)
    {
        Timer t = getTimer(jobInfo);
        if (t != null)
        {
            JobInfo j = (JobInfo) t.getInfo();
            j.setNextTimeout(t.getNextTimeout());
            return j;
        }
        return null;
    }

    /*
     * Updates a timer with the given JobInfo
     */
    public JobInfo updateJob(JobInfo jobInfo)
            throws Exception
    {
        Timer t = getTimer(jobInfo);
        if (t != null)
        {
            LOGGER.log(Level.INFO, "Removing timer: {0}", t.getInfo());
            t.cancel();
            LOGGER.log(Level.INFO, "Updating job with expr: {0}", jobInfo.getExpression());
            return createJob(jobInfo);
        }
        return null;
    }

    /*
     * Remove a timer with the given JobInfo
     */
    public void deleteJob(JobInfo jobInfo)
    {
        Timer t = getTimer(jobInfo);
        if (t != null)
        {
            t.cancel();
        }
    }
}
