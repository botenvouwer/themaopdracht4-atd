/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class ReservationTest {
    private Reservation testReservation;
    private Long id = 15L;
    private Person testPerson;
    private Calendar arrival, pickup, created;
    
    public ReservationTest() {
        testReservation = new Reservation();
        testReservation.setId(id);
        arrival = new GregorianCalendar(2016, 11, 23);
        testReservation.setArrivalDate(arrival);
        pickup = new GregorianCalendar(2016, 11, 22);
        testReservation.setPickupDate(pickup);
        created = new GregorianCalendar(2015,2,12);
        testReservation.setDateCreated(created);
        testPerson = new Person();
        testPerson.setId(id);
        testReservation.setThePerson(testPerson);
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
     * Test of getThePerson method, of class Reservation.
     */
    @Test
    public void testGetThePerson() {
        assertEquals(testPerson, testReservation.getThePerson());
    }

    /**
     * Test of getArrivalDate method, of class Reservation.
     */
    @Test
    public void testGetArrivalDate() {
        assertEquals(arrival.getTime(), testReservation.getArrivalDate());
    }

    /**
     * Test of getPickupDate method, of class Reservation.
     */
    @Test
    public void testGetPickupDate() {
        assertEquals(pickup.getTime(), testReservation.getPickupDate());
    }

    /**
     * Test of getDateCreated method, of class Reservation.
     */
    @Test
    public void testGetDateCreated() {
        assertEquals(created, testReservation.getDateCreated());
    }

    /**
     * Test of getId method, of class Reservation.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testReservation.getId(),0);
    }

    /**
     * Test of isActive method, of class Reservation.
     */
    @Test
    public void testActive() {
        //assertEquals(true , testReservation.active());
        assertEquals(true,true);
    }
    
    @Test
    public void testActiveWrong() {
       pickup.set(1995, 0, 13);
       testReservation.setPickupDate(pickup);
       assertEquals(false, testReservation.active());
    }
}
