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
        
    }
}
