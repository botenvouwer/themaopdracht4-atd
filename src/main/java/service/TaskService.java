/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Person;
import domain.Task;
import domain.Task.Status;
import domain.validate.DomainError;
import domain.validate.ErrorList;
import java.util.Date;
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
    
    public List<Task> getTasks(String date) {
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT t FROM Task t WHERE t.plannedFor = CURRENT_TIMESTAMP");
        // q.setParameter("date", date);

        return q.getResultList();
    }
    
    public List<Task> getTasksFor(Person person) {
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT t FROM Task t WHERE t.customer = :customer ORDER BY t.status");
         q.setParameter("customer", person);

        return q.getResultList();
    }
    
    @Override
    public ErrorList create(Task task){
        
        ErrorList list = task.validate();
        
        if(task.getStatus() == Status.PLANNED){
            if(task.getPlannedFor() == null){
                list.setError(new DomainError("plannedForError", "Geef plan datum op!"));
            }
            else if(task.getPlannedFor().before(new Date())){
                list.setError(new DomainError("plannedForError", "Datum moet in toekomst zijn!"));
            }
        }
        
        if(list.isValid()){
            getEntityManager().persist(task);
        }
     
        return list;
    }
    
    @Override
    public ErrorList update(Task task){
        
        ErrorList list = task.validate();
        
        Task old = find(task.getId());
        
        if(old.getStatus() == Status.REQUEST && task.getStatus() == Status.PLANNED){
            if(task.getPlannedFor() == null){
                list.setError(new DomainError("plannedForError", "Geef plan datum op!"));
            }
            else if(task.getPlannedFor().before(new Date())){
                list.setError(new DomainError("plannedForError", "Datum moet in toekomst zijn!"));
            }
        }
        
        if(list.isValid()){
            getEntityManager().merge(task);
        }
     
        return list;
    }
    
    
}
