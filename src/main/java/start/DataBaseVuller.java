/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Person;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import service.InvoiceLineService;
import service.InvoiceService;
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
    
    @Inject
    private InvoiceLineService invoiceLines;
    
    @Inject
    private InvoiceService invoices;
    
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
        
        Person person2 = new Person();
        person2.setName("jan appel");
        person2.setEmail("jan@hotmail.com");
        person2.setPassword("loppop");
        person2.setRole(Person.Role.CUSTOMER);
        person2.setAdress("zoet 22");
        person2.setZipcode("2343KD");
        person2.setPlace("Zwolle");
        
        persons.create(person2);
        
        InvoiceLine ln = new InvoiceLine();
        ln.setDescription("test");
        ln.setPrice(14.99);
        ln.setQuantity(10);
        
        InvoiceLine ln2 = new InvoiceLine();
        ln2.setDescription("Product AA");
        ln2.setPrice(1.99);
        ln2.setQuantity(787);
        
        InvoiceLine ln3 = new InvoiceLine();
        ln3.setDescription("Product BB");
        ln3.setPrice(699);
        ln3.setQuantity(1);
        
        invoiceLines.create(ln);
        invoiceLines.create(ln2);
        invoiceLines.create(ln3);
        
        Invoice factuur = new Invoice();
        factuur.setCustomer(person2);
        factuur.addLine(ln);
        factuur.addLine(ln2);
        factuur.addLine(ln3);
        
        invoices.create(factuur);
        
    }
}
