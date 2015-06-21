/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Timestamp;
import java.time.Instant;
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
 */
public class DeliveryTest {
    private Delivery testDelivery;
    private Long id = 15L;
    private Delivery.Status status = Delivery.Status.GELEVERD;
    private Article testArticle;
    private int count = 12;
    private Timestamp date = Timestamp.from(Instant.now());
    
    public DeliveryTest() {
        testDelivery = new Delivery();
        testDelivery.setCount(count);
        testDelivery.setId(id);
        testDelivery.setStatus(status);
        testDelivery.setdate(date);
        testArticle = new Article();
        testArticle.setId(id);
        testDelivery.setArticle(testArticle);
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
     * Test of getId method, of class Delivery.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testDelivery.getId(), 0);
    }

    /**
     * Test of getArticle method, of class Delivery.
     */
    @Test
    public void testGetArticle() {
        assertEquals(testArticle, testDelivery.getArticle());
    }

    /**
     * Test of getCount method, of class Delivery.
     */
    @Test
    public void testGetCount() {
        assertEquals(count, testDelivery.getCount(),0);
    }

    /**
     * Test of getStatus method, of class Delivery.
     */
    @Test
    public void testGetStatus() {
        assertEquals(Delivery.Status.GELEVERD, testDelivery.getStatus());
    }

    /**
     * Test of getDate method, of class Delivery.
     */
    @Test
    public void testGetDate() {
        assertEquals(date, testDelivery.getDate());
    }

    /**
     * Test of equals method, of class Delivery.
     */
    @Test
    public void testEquals() {
        Delivery test = new Delivery();
        test.setId(id);
        assertEquals(true, testDelivery.equals(test));
    }
    
    @Test
    public void testEqualsWrong() {
        Delivery test = new Delivery();
        test.setId(id+1L);
        assertEquals(false, testDelivery.equals(test));
    }
}
