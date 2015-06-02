/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Person;
import domain.Person.Role;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author william
 */
@Stateless
public class PersonService extends Service<Person, Long> {

    public PersonService() {
        super(Person.class);
    }

    public boolean exists(String email) {

        Query q = getEntityManager().createQuery("SELECT COUNT(e.id) FROM Person e WHERE e.email = :email");
        q.setParameter("email", email);

        List<Long> test = q.getResultList();

        return (test.get(0) == 1l);
    }

    public Person getPerson(String email, String password) {
        EntityManager e = getEntityManager();

        Query q = e.createQuery("SELECT e FROM Person e WHERE e.email = :email AND e.password = :password");
        q.setParameter("email", email);
        q.setParameter("password", password);

        List<Person> u = q.getResultList();

        if (u.isEmpty()) {
            return null;
        } else {
            return u.get(0);
        }
    }
    
    public List<Person> getPersons(Role role){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT p FROM Person p WHERE p.role = :role");
        q.setParameter("role", role.toString());
        return q.getResultList();
    }
    
    public List<Person> getPersons(){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT p FROM Person p");
        return q.getResultList();
    }

}
