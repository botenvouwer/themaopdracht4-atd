///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package domain;
//
//import java.util.Calendar;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Nigel
// */
//public class ReservationTest {
//    private Reservation testReservation;
//    private Calendar arrival = Calendar.getInstance(), pickup = Calendar.getInstance();
//    private Long id = 15L;
//    private Person testPerson = new Person();
//    
//    public ReservationTest() {
//        testReservation = new Reservation();
//        testReservation.setArrivalDate(arrival);
//        testReservation.setPickupDate(pickup);
//        testPerson.setId(id);
//        testReservation.setId(id);
//        testReservation.setThePerson(testPerson);
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    /**
//     * Test of getDateCreated method, of class Reservation.
//     */
//    @Test
//    public void testGetDateCreated() {
//        assertEquals(Calendar.getInstance(), testReservation.getDateCreated());
//    }
//
//    /**
//     * Test of getThePerson method, of class Reservation.
//     */
//    @Test
//    public void testGetThePerson() {
//        assertEquals(testPerson, testReservation.getThePerson());
//    }
//
//    /**
//     * Test of getPickupDate method, of class Reservation.
//     */
//    @Test
//    public void testGetPickupDate() {
//        assertEquals(pickup, testReservation.getPickupDate());
//    }
//
//    /**
//     * Test of getId method, of class Reservation.
//     */
//    @Test
//    public void testGetId() {
//        assertEquals(id, testReservation.getId());
//    }
//
//    /**
//     * Test of isActive method, of class Reservation.
//     */
//    @Test
//    public void testIsActive() {
//        assertEquals(true, testReservation.isActive());
//    }
//    
//}