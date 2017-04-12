/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedulerapp.web;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.scheduler.ScheduleTask;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IScheduleTaskDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.JobInfoDAO;
import com.schedulerapp.common.JobInfo;
import com.schedulerapp.ejb.JobSessionBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Serge AYEPI
 */
@ManagedBean(name = "JobMBean")
@SessionScoped
public class JobMBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private JobSessionBean jobSessionBean;

    @EJB
    private JobInfoDAO jobDAO;

    @EJB
    private IScheduleTaskDAO taskDAO;

    @EJB
    private IPointDeVenteDAO pvtDAO;

    List<PointDeVente> listMags;

    private List<ScheduleTask> tasks = new ArrayList<>();

    private JobInfo selectedJob;
    private JobInfo newJob;

    private List<PointDeVente> selectedMags;

    public List<PointDeVente> getSelectedMags() {
        return selectedMags;
    }

    public void setSelectedMags(List<PointDeVente> selectedMags) {
        System.out.println(" selectedMags  : "+selectedMags.toString());
        this.selectedMags = selectedMags;
    }

   
    /**
     * Creates a new instance of JobMBean
     */
    public JobMBean() {
    }

    /*
     * Getter method for the newJob property
     */
    public JobInfo getNewJob() {
        return newJob;
    }

    /*
     * Setter method for the newJob property
     */
    public void setNewJob(JobInfo newJob) {
        this.newJob = newJob;
    }

    /*
     * Getter method for the selectedJob property
     */
    public JobInfo getSelectedJob() {
        return selectedJob;
    }

    /*
     * Setter method for the selectedJob property
     */
    public String setSelectedJob(JobInfo selectedJob) {
        this.selectedJob = jobSessionBean.getJobInfo(selectedJob);
        return "JobDetail";
    }

    /*
     * Action handler for back to Listing Page
     */
    public String gotoListing() {
        return "JobList";
    }

    /*
     * Action handler for New Job button
     */
    public String gotoNew() {
        System.out.println("gotoNew() called!!!");
        newJob = new JobInfo();
        //selectedJob = new JobInfo();
        return "JobNew";
    }

    /*
     * Action handler for Duplicate button in the Details page
     */
    public String duplicateJob() {
        newJob = selectedJob;
        newJob.setJobId("<Job ID>");
        return "JobNew";
    }

    /*
     * Action handler for Update button in the Details page
     */
    public String updateJob() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            selectedJob = jobSessionBean.updateJob(selectedJob);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Job successfully updated!"));
        } catch (Exception ex) {
            Logger.getLogger(JobMBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Failed", ex.getCause().getMessage()));
        }
        return null;
    }

    /*
     * Action handler for Delete button in the Details page
     */
    public String deleteJob() {
        jobSessionBean.deleteJob(selectedJob);
        return "JobList";
    }

    /*
     * Action handler for Create button in the New page
     */
    public String createJob() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("com.schedulerapp.web.JobMBean.createJob() newJob : " + newJob.toString());
            ci.prosuma.prosumagpv.entity.scheduler.JobInfo job = jobDAO.createJob(loadJobInfo(newJob));
            newJob.setJobId(String.valueOf(job.getJobId()));
            selectedJob = jobSessionBean.createJob(newJob);
            for(PointDeVente p : selectedMags ){
                System.out.println("@@@@@@@@@@@@@@@@@  Mag : "+p.getPvtCode());
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucess", "Job successfully created!"));
            return "JobDetail";
        } catch (Exception ex) {
            Logger.getLogger(JobMBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", ex.getMessage()));
        }
        return null;
    }

    private ci.prosuma.prosumagpv.entity.scheduler.JobInfo loadJobInfo(JobInfo newJob) {
        ci.prosuma.prosumagpv.entity.scheduler.JobInfo job = new ci.prosuma.prosumagpv.entity.scheduler.JobInfo();
        job.setDayOfMonth(newJob.getDayOfMonth());
        job.setDayOfWeek(newJob.getDayOfWeek());
        job.setDescription(newJob.getDescription());
        job.setEndDate(job.getEndDate());
        job.setHour(newJob.getHour());
        job.setJobClassName(newJob.getJobClassName());
        job.setJobName(newJob.getJobName());
        job.setMinute(newJob.getMinute());
        job.setMonth(newJob.getMonth());
        job.setNextTimeout(newJob.getNextTimeout());
        job.setSecond(newJob.getSecond());
        job.setStartDate(newJob.getStartDate());
        job.setYear(newJob.getYear());
        return job;
    }

    public List<ScheduleTask> getTasks() {
        tasks = new ArrayList<>();
        ScheduleTask t = new ScheduleTask();
        t.setDescription("--- Choisir un traitement --");
        t.setJndiName("AUCUN");
        tasks.add(t);
        tasks.addAll(taskDAO.getAllTask());
        return tasks;
    }

    public void setTasks(List<ScheduleTask> tasks) {
        this.tasks = tasks;
    }

    public List<PointDeVente> getListMags() {
        setListMags(pvtDAO.getAllPVTActive());
        return listMags;
    }

    public void setListMags(List<PointDeVente> listMags) {
        this.listMags = listMags;
    }

}
