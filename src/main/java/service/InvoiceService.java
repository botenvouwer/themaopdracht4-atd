/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author william
 */
@Stateless
public class InvoiceService extends Service<Invoice, Long> {
    
    public InvoiceService() {
        super(Invoice.class);
    }
    
    public List<Invoice> getInvoicesFor(Person person){
        Query q = getEntityManager().createQuery("SELECT i FROM Invoice i WHERE i.customer = :person");
        q.setParameter("person", person);
        return q.getResultList();
    }
    
    public List<Invoice> get(String query){
        Query q = getEntityManager().createQuery(query);
        return q.getResultList();
    }
    
    public void create(Invoice invoice){
        EntityManager e = getEntityManager();
        for(InvoiceLine line : invoice.getLines()){
            e.persist(line);
        }
        e.persist(invoice);
    }
    
}
