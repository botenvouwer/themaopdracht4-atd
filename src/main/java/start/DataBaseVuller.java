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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.PersonService;

/**
 *
 * @author william
 */
@Singleton
@Startup
public class DataBaseVuller {
    
    @Inject
    private PersonService persons;
    
    @PostConstruct
    private void vullDB(){
        
        //vull de database met test data
        
        Person person = new Person();
        person.setName("pipo karel");
        person.setEmail("pipo@hotmail.com");
        person.setPassword("loppop");
        person.setRole(Person.Role.BOSS);
        person.setAdress("dinkie 22");
        person.setZipcode("3645BP");
        person.setPlace("Utrecht");
        
        persons.create(person);
    }
}
