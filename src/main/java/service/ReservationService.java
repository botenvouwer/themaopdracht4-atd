/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Person;
import domain.Reservation;
import domain.validate.ErrorList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Nigel
 */
@Stateless
public class ReservationService extends Service<Reservation, Long> {

    public ReservationService() {
        super(Reservation.class);
    }
  
    public List<Reservation> getReservations(Person person){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT r FROM Reservation r WHERE r.thePerson = :person");
        q.setParameter("person", person);
        return q.getResultList();
    }
    
    public List<Reservation> getReservations(){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT r FROM Reservation r");
        return q.getResultList();
    }
    
    public ErrorList create(Reservation r) {
        getEntityManager().persist(r);
        return new ErrorList();
    }
}
