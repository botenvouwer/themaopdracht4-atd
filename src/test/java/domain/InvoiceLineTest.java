/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nigel
 */
public class InvoiceLineTest {
    private InvoiceLine testInvoiceLine;
    private Long id = 15L;
    private String description = "Test line";
    private double price = 32, discount = 12;
    private int quantity = 34;
    
    public InvoiceLineTest() {
        testInvoiceLine = new InvoiceLine();
        testInvoiceLine.setId(id);
        testInvoiceLine.setDiscount(discount);
        testInvoiceLine.setDescription(description);
        testInvoiceLine.setPrice(price);
        testInvoiceLine.setQuantity(quantity);
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
     * Test of getTotal method, of class InvoiceLine.
     */
    @Test
    public void testGetTotal() {
        assertEquals((quantity * price * (1 -(discount / 100))), testInvoiceLine.getTotal(),0);
    }

    /**
     * Test of getId method, of class InvoiceLine.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testInvoiceLine.getId(),0);
    }

    /**
     * Test of getDescription method, of class InvoiceLine.
     */
    @Test
    public void testGetDescription() {
        assertEquals(description, testInvoiceLine.getDescription());
    }

    /**
     * Test of getPrice method, of class InvoiceLine.
     */
    @Test
    public void testGetPrice() {
        assertEquals(price, testInvoiceLine.getPrice(),0);
    }

    /**
     * Test of getDiscount method, of class InvoiceLine.
     */
    @Test
    public void testGetDiscount() {
        assertEquals(discount, testInvoiceLine.getDiscount(), 0);
    }

    /**
     * Test of getQuantity method, of class InvoiceLine.
     */
    @Test
    public void testGetQuantity() {
        assertEquals(quantity, testInvoiceLine.getQuantity());
    }

    /**
     * Test of equals method, of class InvoiceLine.
     */
    @Test
    public void testEquals() {
        InvoiceLine test = new InvoiceLine();
        test.setId(id);
        assertEquals(true, testInvoiceLine.equals(test));
    }
    
    @Test
    public void testEqualsWrong() {
        InvoiceLine test = new InvoiceLine();
        test.setId(id + 1L);
        assertEquals(false, testInvoiceLine.equals(test));
    }
    
}
