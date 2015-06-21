/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.MultiDimensionalErrorList;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
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
 * 
 */
public class InvoiceTest {
    private Invoice testInvoice = new Invoice();
    private Long id = 15L;
    private Invoice.Status status = Invoice.Status.CANCELED;
    private List<InvoiceLine> lines = new ArrayList<InvoiceLine>();
    private Person customer = new Person();
    private Timestamp date = Timestamp.from(Instant.now()), send = Timestamp.from(Instant.now());
    private double tax = 1.33;
    private InvoiceLine line = new InvoiceLine();
    private double priceline = 120;
    private int quantityline = 5;
    
    public InvoiceTest() {
        line.setId(id);
        line.setPrice(priceline);
        line.setQuantity(quantityline);
        lines.add(line);
        
        testInvoice.setCustomer(customer);
        testInvoice.setDate(date);
        testInvoice.setId(id);
        testInvoice.setLines(lines);
        testInvoice.setSend(send);
        testInvoice.setStatus(status);
        testInvoice.setTax(tax);
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
     * Test of getTotal method, of class Invoice.
     */
    @Test
    public void testGetTotal() {
        assertEquals((priceline * quantityline * tax), testInvoice.getTotal(),1);
    }

    /**
     * Test of getBTW method, of class Invoice.
     */
    @Test
    public void testGetBTW() {
        assertEquals((quantityline * priceline * (tax-1)), testInvoice.getBTW(),0);
    }

    /**
     * Test of getTaxFree method, of class Invoice.
     */
    @Test
    public void testGetTaxFree() {
        assertEquals((priceline * quantityline), testInvoice.getTaxFree(),0);
    }

    /**
     * Test of getId method, of class Invoice.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testInvoice.getId(),0);
    }

    /**
     * Test of getLines method, of class Invoice.
     */
    @Test
    public void testGetLines() {
        boolean b = false;
        if(!testInvoice.getLines().isEmpty()) {
            b = true;
        }
        assertEquals(true, b);
    }

    /**
     * Test of getLineCount method, of class Invoice.
     */
    @Test
    public void testGetLineCount() {
        assertEquals(1, testInvoice.getLineCount(),0);
    }

    /**
     * Test of addLine method, of class Invoice.
     */
    @Test
    public void testAddLine() {
        testInvoice.addLine(line);
        assertEquals(2, testInvoice.getLineCount(),0);
    }

    /**
     * Test of removeLine method, of class Invoice.
     */
    @Test
    public void testRemoveLine() {
        testInvoice.removeLine(line);
        assertEquals(0, testInvoice.getLineCount(),0);
    }

    /**
     * Test of getLine method, of class Invoice.
     */
    @Test
    public void testGetLine() {
        assertEquals(line, testInvoice.getLine(id));
    }

    /**
     * Test of getCustomer method, of class Invoice.
     */
    @Test
    public void testGetCustomer() {
        assertEquals(customer, testInvoice.getCustomer()); 
   }

    /**
     * Test of getDate method, of class Invoice.
     */
    @Test
    public void testGetDate() {
        assertEquals(date, testInvoice.getDate());
    }

    /**
     * Test of getTax method, of class Invoice.
     */
    @Test
    public void testGetTax() {
        assertEquals(tax, testInvoice.getTax(),0);
    }

    /**
     * Test of getSend method, of class Invoice.
     */
    @Test
    public void testGetSend() {
        assertEquals(send, testInvoice.getSend());
    }

    /**
     * Test of getStatus method, of class Invoice.
     */
    @Test
    public void testGetStatus() {
        assertEquals(status, testInvoice.getStatus());
    }

    /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEquals() {
        Invoice test = new Invoice();
        test.setId(id);
        assertEquals(true, testInvoice.equals(test));
    }

    @Test
    public void testEqualsWrong() {
        Invoice test = new Invoice();
        test.setId(id+1L);
        assertEquals(false, testInvoice.equals(test));
    }
}
