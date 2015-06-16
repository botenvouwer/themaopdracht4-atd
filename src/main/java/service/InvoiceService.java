/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Person;
import domain.validate.DomainError;
import domain.validate.MultiDimensionalErrorList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
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
    
    public boolean setNotPaid(Long id){
        Invoice i = find(id);
        if(i != null && i.getStatus() == Invoice.Status.OFFER){
            i.setStatus(Invoice.Status.NOTPAID);
            i.setSend(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            getEntityManager().merge(i);
            return true;
        }
        return false;
    }
    
    public boolean setPaid(Long id){
        Invoice i = find(id);
        if(i != null && i.getStatus() == Invoice.Status.NOTPAID){
            i.setStatus(Invoice.Status.PAID);
            getEntityManager().merge(i);
            return true;
        }
        return false;
    }
    
    public boolean setCanceled(Long id){
        Invoice i = find(id);
        if(i != null && i.getStatus() != Invoice.Status.PAID){
            i.setStatus(Invoice.Status.CANCELED);
            getEntityManager().merge(i);
            return true;
        }
        return false;
    }
    
    @Override
    public MultiDimensionalErrorList create(Invoice entity) {
        
        MultiDimensionalErrorList e = entity.validate();
        
        if(e.isValid()){
            
            for(InvoiceLine line : entity.getLines()){
                getEntityManager().persist(line);
            }
            
            getEntityManager().persist(entity);
        }
        
        return e;
    }
    
    @Override
    public MultiDimensionalErrorList update(Invoice entity) {
        MultiDimensionalErrorList e = entity.validate();
        
        if(entity.getStatus() == Invoice.Status.PAID || entity.getStatus() == Invoice.Status.CANCELED){
            e.setError(new DomainError("invoiceError", "Kan factuur niet wijzigen als deze al betaald of geannuleerd is"));
        }
        
        if(e.isValid()){
            
            List<InvoiceLine> old = find(entity.getId()).getLines();
            List<InvoiceLine> nieuw = entity.getLines();
            
            for(InvoiceLine line : old){
                if(!nieuw.contains(line)){
                    getEntityManager().remove(line);
                }
            }
            
            for(InvoiceLine line : nieuw){
                if(line.getId() == null || line.getId() == 0l){
                    getEntityManager().persist(line);
                }
                else{
                    getEntityManager().merge(line);
                }
            }
            
            getEntityManager().merge(entity);
            
        }
        
        return e;
    }
    
}
