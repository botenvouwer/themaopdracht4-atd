/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.ErrorList;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nigel
 * Tests on getters will also check the setters since they are used to set the values.
 * Find functions are tested in other functions and not tested on it's own.
 */
public class TaskTest {
    private Task testTask = new Task();
    private Long id = 15L;
    private Task.Status status = Task.Status.FINISHED;
    private Task.Type type = Task.Type.REPAIR;
    private String cusNote = "Auto rammelt", mecNote = "nu niet meer";
    private double hours = 321;
    private Invoice invoice = new Invoice();
    private Person customer = new Person(), mechanic = new Person();
    private List<UsedArticle> lijst = new ArrayList<UsedArticle>();
    private Calendar plannedFor = new GregorianCalendar(2016, 0, 22);
    private Timestamp created = Timestamp.from(Instant.now());
    private Car car = new Car();
    private Article used = new Article();
    
    public TaskTest() {
        testTask.setCar(car);
        testTask.setCreated(created);
        testTask.setCustomer(customer);
        testTask.setCustomerNote(cusNote);
        testTask.setHours(hours);
        testTask.setId(id);
        testTask.setInvoice(invoice);
        testTask.setMechanic(mechanic);
        testTask.setMechanicNote(mecNote);
        testTask.setPlannedFor(plannedFor);
        testTask.setStatus(status);
        testTask.setType(type);
        testTask.setUsedArticle(used, 13);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Task.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testTask.getId(),0);
    }

    /**
     * Test of getInvoice method, of class Task.
     */
    @Test
    public void testGetInvoice() {
        assertEquals(invoice, testTask.getInvoice());
    }

    /**
     * Test of getStatus method, of class Task.
     */
    @Test
    public void testGetStatus() {
        assertEquals(status, testTask.getStatus());
    }

    /**
     * Test of getType method, of class Task.
     */
    @Test
    public void testGetType() {
        assertEquals(type, testTask.getType());
    }

    /**
     * Test of getCustomerNote method, of class Task.
     */
    @Test
    public void testGetCustomerNote() {
        assertEquals(cusNote, testTask.getCustomerNote());
    }

    /**
     * Test of getMechanicNote method, of class Task.
     */
    @Test
    public void testGetMechanicNote() {
        assertEquals(mecNote, testTask.getMechanicNote());
    }

    /**
     * Test of getHours method, of class Task.
     */
    @Test
    public void testGetHours() {
        assertEquals(hours, testTask.getHours(),0);
    }

    /**
     * Test of getCustomer method, of class Task.
     */
    @Test
    public void testGetCustomer() {
        assertEquals(customer, testTask.getCustomer());
    }

    /**
     * Test of getCar method, of class Task.
     */
    @Test
    public void testGetCar() {
        assertEquals(car, testTask.getCar());
    }

    /**
     * Test of getMechanic method, of class Task.
     */
    @Test
    public void testGetMechanic() {
        assertEquals(mechanic, testTask.getMechanic());
    }

    /**
     * Test of getUsedArticles method, of class Task.
     */
    @Test
    public void testGetUsedArticles() {
        boolean b = false;
        if(!testTask.getUsedArticles().isEmpty()) {
            b = true;
        }
        assertEquals(true, b);
    }

    /**
     * Test of removeArticle method, of class Task.
     */
    @Test
    public void testRemoveArticle() {
        testTask.removeArticle(testTask.findUsedArticle(used));
        boolean b = false;
        if(testTask.getUsedArticles().isEmpty()) {
            b = true;
        }
        assertEquals(true, b);
        
    }

    /**
     * Test of getPlannedFor method, of class Task.
     */
    @Test
    public void testGetPlannedFor() {
        assertEquals(plannedFor, testTask.getPlannedFor());
    }

    /**
     * Test of getCreated method, of class Task.
     */
    @Test
    public void testGetCreated() {
        assertEquals(created, testTask.getCreated());
    }

    /**
     * Test of equals method, of class Task.
     */
    @Test
    public void testEquals() {
        Task test = new Task();
        test.setId(id);
        assertEquals(true, testTask.equals(test));
    }
    
    @Test
    public void testEqualsWrong() {
        Task test = new Task();
        test.setId(id+1L);
        assertEquals(false, testTask.equals(test));
    }
}
