/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Person;
import domain.Task;
import domain.Task.Status;
import domain.UsedArticle;
import domain.validate.DomainError;
import domain.validate.ErrorList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author william
 */
@Stateless
public class TaskService extends Service<Task, Long> {

    public TaskService() {
        super(Task.class);
    }
    
    public List<Task> getRequests(){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT t FROM Task t WHERE t.status = :req");
        q.setParameter("req", Status.REQUEST);
        return q.getResultList();
    }
    
    public List<Task> getTasks(Calendar  date) {
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT t FROM Task t WHERE t.plannedFor = :date");
        q.setParameter("date", date, TemporalType.DATE);
        return q.getResultList();
    }
    
    public List<Task> getTasks(Calendar  date, Person employee) {
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT t FROM Task t WHERE t.plannedFor = :date AND t.mechanic = :emp");
        q.setParameter("date", date, TemporalType.DATE);
        q.setParameter("emp", employee);
        return q.getResultList();
    }
    
    public List<Task> getTasksFor(Person person) {
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT t FROM Task t WHERE t.customer = :customer ORDER BY t.status");
         q.setParameter("customer", person);

        return q.getResultList();
    }
    
    public void removeUsedArticle(Long taskid, Long usedArticleid){
        EntityManager e = getEntityManager();
        Task t = find(taskid);
        System.out.println(t);
        UsedArticle ua = e.find(UsedArticle.class, usedArticleid);
        System.out.println(ua);
        System.out.println(t.getUsedArticles().size());
        t.removeArticle(ua);
        System.out.println(t.getUsedArticles().size());
        
        e.merge(t);
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
            for(UsedArticle ua : task.getUsedArticles()){
                getEntityManager().persist(ua);
            }
            
            getEntityManager().persist(task);
        }
     
        return list;
    }
    
    @Override
    public ErrorList update(Task task){
        
        ErrorList list = task.validate();
        
        Task old = find(task.getId());
        
        //Anders kan er in de toekomst nooit geupdate worden
//        if(task.getStatus() == Status.PLANNED){
//            if(task.getPlannedFor() == null){
//                list.setError(new DomainError("plannedForError", "Geef plan datum op!"));
//            }
//            else if(task.getPlannedFor().before(Calendar.getInstance())){
//                list.setError(new DomainError("plannedForError", "Datum moet in toekomst zijn!"));
//            }
//        }
        
        if(list.isValid()){
            
            List<UsedArticle> oldlist = old.getUsedArticles();
            List<UsedArticle> nieuw = task.getUsedArticles();
            
            for(UsedArticle used : oldlist){
                if(!nieuw.contains(used)){
                    getEntityManager().remove(used);
                }
            }
            
            for(UsedArticle ua : task.getUsedArticles()){
                if(ua.getId() == null || ua.getId() == 0l){
                    getEntityManager().persist(ua);
                }
                else{
                    getEntityManager().merge(ua);
                }
            }
            
            getEntityManager().merge(task);
        }
     
        return list;
    }
    
    
}
