/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.scheduler.ScheduleTask;
import java.util.List;

/**
 *
 * @author tagsergi
 */
public interface IScheduleTaskDAO {
    
    public ScheduleTask createTask(ScheduleTask task);
    
    public ScheduleTask updateTask(ScheduleTask task);
    
    public boolean removeTask(String jndiName);
    
    public List<ScheduleTask> getAllTask();
    
    public ScheduleTask getTask(String jndiName);
    
}
