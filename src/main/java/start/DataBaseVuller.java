/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import domain.Invoice;
import domain.InvoiceLine;
import domain.Article;
import domain.Car;
import domain.Delivery;
import domain.Person;
import domain.Task;
import domain.validate.MultiDimensionalErrorList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import service.InvoiceLineService;
import service.InvoiceService;
import service.ArticleService;
import service.CarService;
import service.DeliveryService;
import service.PersonService;
import service.TaskService;

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
    public CarService cars;
    
    @Inject
    public InvoiceLineService invoiceLines;
    
    @Inject
    public InvoiceService invoices;

    @Inject
    public ArticleService articles;
    
    @Inject
    public DeliveryService deliverys;
    
    @Inject
    public TaskService tasks;
    
    @PostConstruct
    private void vullDB() {
        // Yanick van Barneveld
        Person p = new Person();
        p.setAdress("Beverstraat 37");
        p.setEmail("yanick@hotmail.com");
        p.setName("Yanick");
        p.setPassword("admin");
        p.setPlace("Utrecht");
        p.setRole(Person.Role.BOSS);
        p.setZipcode("3513AP");
        persons.create(p);
        p.setActive(true);
        persons.update(p);
        
        Person p2 = new Person();
        p2.setAdress("Leverstraat");
        p2.setEmail("pipo@hotmail.com");
        p2.setName("pipo appel");
        p2.setPassword("loppop");
        p2.setPlace("Urk");
        p2.setRole(Person.Role.CUSTOMER);
        p2.setZipcode("2354AP");
        persons.create(p2);
        p2.setActive(true);
        persons.update(p2);
        
        Car c1 = new Car();
        c1.setBrand("Volkswagen");
        c1.setLicensePlate("5-5-5");
        c1.setModel("Polo");
        c1.setOwner(p2);
        cars.create(c1);
        
        Person p3 = new Person();
        p3.setAdress("Pannekoekendijk");
        p3.setEmail("keesa@hotmail.com");
        p3.setName("kees appel");
        p3.setPassword("loppop");
        p3.setPlace("Urk");
        p3.setRole(Person.Role.CUSTOMER);
        p3.setZipcode("7374TY");
        persons.create(p3);
        p3.setActive(true);
        persons.update(p3);
        
        Person p4 = new Person();
        p4.setAdress("MeloenStraat");
        p4.setEmail("janpeer@hotmail.com");
        p4.setName("jan peer");
        p4.setPassword("loppop");
        p4.setPlace("Emmeloord");
        p4.setRole(Person.Role.CUSTOMER);
        p4.setZipcode("1626GH");
        persons.create(p4);
        p4.setActive(true);
        persons.update(p4);
        
        Car c2 = new Car();
        c2.setBrand("Audi");
        c2.setLicensePlate("34-re-34");
        c2.setModel("A4");
        c2.setOwner(p4);
        cars.create(c2);
        
        Car c3 = new Car();
        c3.setBrand("Audi");
        c3.setLicensePlate("12-er-33");
        c3.setModel("A3");
        c3.setOwner(p4);
        cars.create(c3);
        
        Person p5 = new Person();
        p5.setAdress("Kalver");
        p5.setEmail("henk@hotmail.com");
        p5.setName("Henk de man");
        p5.setPassword("loppop");
        p5.setPlace("Markermeer");
        p5.setRole(Person.Role.EMPLOYEE);
        p5.setZipcode("6783KH");
        persons.create(p5);
        p5.setActive(true);
        persons.update(p5);
        
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
        
        Delivery o1 = new Delivery();
        o1.setArticle(a1);
        o1.setCount(5);
        deliverys.create(o1);
        
        Invoice i1 = new Invoice();
        i1.setCustomer(p2);
        
        InvoiceLine l1 = new InvoiceLine();
        l1.setDescription("prod aa");
        l1.setPrice(20);
        l1.setDiscount(0.5);
        l1.setQuantity(1);
        
        InvoiceLine l2 = new InvoiceLine();
        l2.setDescription("prod bb");
        l2.setPrice(19.95);
        l2.setQuantity(2);
        
        i1.addLine(l1);
        i1.addLine(l2);
        
        MultiDimensionalErrorList list = invoices.create(i1);
        
        Task t1 = new Task();
        t1.setCar(c2);
        t1.setCustomer(p4);
        t1.setCustomerNote("Band moet vervangen worden!");
        t1.setStatus(Task.Status.REQUEST);
        t1.setType(Task.Type.REPAIR);
        tasks.create(t1);
        
        Task t3 = new Task();
        t3.setCar(c3);
        t3.setCustomer(p4);
        t3.setStatus(Task.Status.REQUEST);
        t3.setType(Task.Type.APK);
        tasks.create(t3);
        
        Task t2 = new Task();
        t2.setCar(c2);
        t2.setCustomer(p4);
        t2.setCustomerNote("Windscherm is gescheurd!");
        t2.setMechanic(p);
        t2.setPlannedFor(new Date(2016, 4, 4));
        t2.setStatus(Task.Status.PLANNED);
        t2.setType(Task.Type.REPAIR);
        tasks.create(t2);
        
        Task t4 = new Task();
        t4.setCar(c2);
        t4.setCustomer(p4);
        t4.setMechanic(p);
        t4.setPlannedFor(new Date(2015, 3, 3));
        t4.setStatus(Task.Status.FINISHED);
        t4.setType(Task.Type.APK);
        t4.setHours(4.0);
        t4.setMechanicNote("Jaja alles is goed");
        tasks.create(t4);
    }
}
