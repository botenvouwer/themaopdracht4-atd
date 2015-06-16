/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Car;
import domain.Person;
import domain.Person.Role;
import static domain.Person_.role;
import domain.validate.DomainError;
import domain.validate.ErrorList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author william
 */
@Stateless
public class CarService extends Service<Car, Long> {

    public CarService() {
        super(Car.class);
    }
  
    public List<Car> getCars(Person person){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT c FROM Car c WHERE c.owner = :person");
        q.setParameter("person", person);
        return q.getResultList();
    }
}
