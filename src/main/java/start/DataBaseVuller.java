/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Article;
import domain.Person;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import service.InvoiceLineService;
import service.InvoiceService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.ArticleService;
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
    
    @Inject
    public InvoiceLineService invoiceLines;
    
    @Inject
    public InvoiceService invoices;

    @Inject
    public ArticleService articles;
    
    @PostConstruct
    private void vullDB() {
        // Yanick van Barneveld
        Person p = new Person();
        p.setAdress("Beverstraat 37");
        p.setEmail("admin");
        p.setName("Yanick");
        p.setPassword("admin");
        p.setPlace("Utrecht");
        p.setRole(Person.Role.BOSS);
        p.setZipcode("3513AP");
        persons.create(p);
        
        Article a1 = new Article();
        a1.setName("Wieldop");
        a1.setPrice(27.55);
        a1.setStock(50);
        articles.create(a1);
        
        Article a2 = new Article();
        a2.setName("Band");
        a2.setPrice(100);
        a2.setStock(5);
        articles.create(a2);
        
        Article a3 = new Article();
        a3.setName("Ruitenwisser");
        a3.setPrice(45.55);
        a3.setStock(2);
        articles.create(a3);
    }
}
