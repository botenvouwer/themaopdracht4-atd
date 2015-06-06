/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Delivery;
import domain.Delivery.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author yanick
 */
@Stateless
public class DeliveryService extends Service<Delivery, Long> {
    
    public DeliveryService() {
        super(Delivery.class);
    }
    
    public List<Delivery> getOrders(){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT d FROM Delivery d");
        return q.getResultList();
    }
    
    public void deleteOrder(int id) {
        Query q = getEntityManager().createQuery("DELETE FROM Delivery d WHERE d.id= :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
    
    public void setStatus(Status status, int id) {
        Query q = getEntityManager().createQuery("UPDATE Delivery SET status = :status");
        // q.setParameter("id", id);
        q.setParameter("status", status);
        q.executeUpdate();
    }
}