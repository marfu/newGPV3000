/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import ci.prosuma.prosumagpv.entity.scheduler.JobHistory;
import ci.prosuma.prosumagpv.metier.dao.mysql.JobHistoryDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tagsergi
 */
@Stateless
@Local(JobHistoryDAO.class)
public class JobHistoryDAOImpl implements Serializable, JobHistoryDAO {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public JobHistory createJobHistory (JobHistory jobInfo) {
        em.persist(jobInfo);
        em.flush();
        return jobInfo;

    }

    @Override
    public JobHistory updateJobHistory (JobHistory jobInfo) {
        JobHistory a = getJobHistoryByid(jobInfo.getId());
        if (a != null) {
            em.merge(a);
            return a;
        } else {
            em.persist(a);
            return a;
        }
    }

    @Override
    public void deleteJobHistory (JobHistory jobInfo) {
        JobHistory a = getJobHistoryByid(jobInfo.getId());
        if (a != null) {
            em.remove(a);
        }
    }

    @Override
    public JobHistory getJobHistoryByid(long id) {
        return em.find(JobHistory.class, id);
    }

    @Override
    public List<JobHistory> getAllJobHistory () {
        return em.createQuery("SELECT e FROM JobInfo e").getResultList();
    }

}
