/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.entity.scheduler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Serge AYEPI
 */

//@Entity
public class JobInfo implements Serializable
{
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    
    private String jobName;
    
    private String jobClassName;
    
    private String description;
    //Details required by the SchedulerExpression
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    private String second;
    private String minute;
    private String hour;
    private String dayOfWeek;
    private String dayOfMonth;
    private String month;
    private String year;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date nextTimeout;

    public JobInfo() {
    }



    public JobInfo(Long jobId, String jobName, String jobClassName)
    {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobClassName = jobClassName;
        this.description = "";
        //Default values
        this.startDate = new Date();
        this.endDate = null;
        this.second = "0";
        this.minute = "0";
        this.hour = "0";
        this.dayOfMonth = "*";  //Every Day
        this.month = "*";       //Every Month
        this.year = "*";        //Every Year
        this.dayOfWeek = "*";   //Every Day of Week (Sun-Sat)
    }

    public long getJobId()
    {
        return jobId;
    }

    public void setJobId(long jobId)
    {
        this.jobId = jobId;
    }

    public String getJobClassName()
    {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName)
    {
        this.jobClassName = jobClassName;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public String getEndDateStr()
    {
        if (endDate != null)
        {
            return sdf.format(endDate);
        }
        return "Never";
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public String getStartDateStr()
    {
        return sdf.format(startDate);
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public String getDayOfMonth()
    {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth)
    {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek()
    {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHour()
    {
        return hour;
    }

    public void setHour(String hour)
    {
        this.hour = hour;
    }

    public String getMinute()
    {
        return minute;
    }

    public void setMinute(String minute)
    {
        this.minute = minute;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getSecond()
    {
        return second;
    }

    public void setSecond(String second)
    {
        this.second = second;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public Date getNextTimeout()
    {
        return nextTimeout;
    }

    public String getNextTimeoutStr()
    {
        return sdf2.format(nextTimeout);
    }

    public void setNextTimeout(Date nextTimeout)
    {
        this.nextTimeout = nextTimeout;
    }

    /*
     * Expression of the schedule set in the object
     */
    public String getExpression()
    {
        return "sec=" + second + ";min=" + minute + ";hour=" + hour
                + ";dayOfMonth=" + dayOfMonth + ";month=" + month + ";year=" + year
                + ";dayOfWeek=" + dayOfWeek;
    }

    @Override
    public boolean equals(Object anotherObj)
    {
        if (anotherObj instanceof JobInfo)
        {
            return jobId.equals(((JobInfo) anotherObj).jobId);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return jobId + "-" + jobName + "-" + jobClassName;
    }
}
