/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Article;
import domain.Delivery;
import domain.Delivery.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author yanick
 */
@Stateless
public class DeliveryService extends Service<Delivery, Long> {
    
    @Inject
    ArticleService articles;
    
    public DeliveryService() {
        super(Delivery.class);
    }
    
    public List<Delivery> getOrders(){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT d FROM Delivery d ORDER BY d.date");
        return q.getResultList();
    }
    
    public List<Delivery> getOrdersByStatus(Status status){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT d FROM Delivery d WHERE d.status = :status ORDER BY d.date");
        q.setParameter("status", status);
        return q.getResultList();
    }
    
    public void deleteOrder(int id) {
        Query q = getEntityManager().createQuery("DELETE FROM Delivery d WHERE d.id= :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
    
    public void setStatus(Status status, int id) {
        Query q = getEntityManager().createQuery("UPDATE Delivery SET status = :status WHERE id = :id");
        q.setParameter("id", id);
        q.setParameter("status", status);
        q.executeUpdate();
        
        if (status.equals(Status.GELEVERD)) {
            Delivery d = (Delivery) find(Long.parseLong(id + ""));
            Article a = (Article) d.getArticle();

            articles.inboeken(a.getId(), d.getCount());
        }
    }
}