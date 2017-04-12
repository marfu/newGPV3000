/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import ci.prosuma.prosumagpv.entity.scheduler.ScheduleTask;
import ci.prosuma.prosumagpv.metier.dao.mysql.IScheduleTaskDAO;
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
@Local(IScheduleTaskDAO.class)
public class ScheduleTaskDAOImpl implements IScheduleTaskDAO, Serializable{

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    protected EntityManager em;

    @Override
    public ScheduleTask createTask(ScheduleTask task) {
        em.persist(task);
        em.flush();
        return task;
    }

    @Override
    public ScheduleTask updateTask(ScheduleTask task) {
        ScheduleTask a = getTask(task.getJndiName());
        if (a != null) {
            em.merge(a);
            return a;
        } else {
            em.persist(a);
            return a;
        }
    }

    @Override
    public boolean removeTask(String jndiName) {
        ScheduleTask a = getTask(jndiName);
        if (a != null) {
            em.remove(a);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<ScheduleTask> getAllTask() {
        return em.createQuery("SELECT s FROM ScheduleTask s").getResultList();
    }

    @Override
    public ScheduleTask getTask(String jndiName) {
         return em.find(ScheduleTask.class, jndiName);
    }
    
}
