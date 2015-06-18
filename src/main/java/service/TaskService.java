/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Task;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author william
 */
@Stateless
public class TaskService extends Service<Task, Long> {

    public TaskService() {
        super(Task.class);
    }
    
    //todo: moet nog getest worden
    public List<Task> getTasks(Timestamp day) {
        EntityManager e = getEntityManager();

        Query q = e.createQuery("SELECT t FROM Task t");
        // q.setParameter("date", day);

        return q.getResultList();
    }
    
    //todo: schrijf methodes voor werkplaats
}
