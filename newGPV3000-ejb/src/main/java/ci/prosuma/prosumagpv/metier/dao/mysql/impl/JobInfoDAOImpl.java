/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import ci.prosuma.prosumagpv.entity.scheduler.JobInfo;
import ci.prosuma.prosumagpv.metier.dao.mysql.JobInfoDAO;
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
@Local(JobInfoDAO.class)
public class JobInfoDAOImpl implements Serializable, JobInfoDAO {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public JobInfo createJob(JobInfo jobInfo) {
        em.persist(jobInfo);
        em.flush();
        return jobInfo;

    }

    @Override
    public JobInfo updateJob(JobInfo jobInfo) {
        JobInfo a = getJobByid(jobInfo.getJobId());
        if (a != null) {
            em.merge(a);
            return a;
        } else {
            em.persist(a);
            return a;
        }
    }

    @Override
    public void deleteJob(JobInfo jobInfo) {
        JobInfo a = getJobByid(jobInfo.getJobId());
        if (a != null) {
            em.remove(a);
        }
    }

    @Override
    public JobInfo getJobByid(long id) {
        return em.find(JobInfo.class, id);
    }

    @Override
    public List<JobInfo> getAllJob() {
        return em.createQuery("SELECT e FROM JobInfo e").getResultList();
    }

}
