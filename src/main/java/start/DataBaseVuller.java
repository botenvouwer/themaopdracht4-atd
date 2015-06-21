/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import domain.Person;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import service.PersonService;

/**
 *
 * @author william
 */
@Singleton
@Startup
public class DataBaseVuller {
    
    @Inject
    public PersonService persons;
    
    @PostConstruct
    private void vullDB() {
        
//        Person p = new Person();
//        p.setAdress("Frikandelelaan 37");
//        p.setEmail("henk@atd.nl");
//        p.setName("Henk Paladein");
//        p.setPassword("admin");
//        p.setPlace("Utrecht");
//        p.setRole(Person.Role.BOSS);
//        p.setZipcode("3513AP");
//        persons.create(p);
        
        
    }
}
