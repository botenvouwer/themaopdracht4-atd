/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.ErrorList;
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
public class CarTest {
    private Car testCar;
    private Person testPerson;
    
    public CarTest() {
        testCar = new Car();
        testCar.setId(15L);
        testCar.setLicensePlate("AA-65-LPG");
        testCar.setModel("R8");
        testCar.setBrand("Audi");
        testPerson = new Person();
        testPerson.setId(15L);
        testCar.setOwner(testPerson);
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
     * Test of getId method, of class Car.
     */
    @Test
    public void testGetId() {
        assertEquals(15L, testCar.getId(),0);
    }

    /**
     * Test of getLicensePlate method, of class Car.
     */
    @Test
    public void testGetLicensePlate() {
        assertEquals("AA-65-LPG", testCar.getLicensePlate());
    }

    /**
     * Test of getBrand method, of class Car.
     */
    @Test
    public void testGetBrand() {
        assertEquals("Audi", testCar.getBrand());
    }

    /**
     * Test of getModel method, of class Car.
     */
    @Test
    public void testGetModel() {
        assertEquals("R8", testCar.getModel());
    }

    /**
     * Test of getOwner method, of class Car.
     */
    @Test
    public void testGetOwner() {
        assertEquals(testPerson, testCar.getOwner());
    }
    
    /**
     * Test of isSoftDelete method, of class Car.
     */
    @Test
    public void testIsSoftDelete() {
        assertEquals(false, testCar.isSoftDelete());
    }

    /**
     * Test of setSoftDelete method, of class Car.
     */
    @Test
    public void testSetSoftDelete() {
        testCar.setSoftDelete(true);
        assertEquals(true, testCar.isSoftDelete());
    }

    /**
     * Test of equals method, of class Car.
     */
    @Test
    public void testEquals() {
        Car test = new Car();
        test.setId(15L);
        assertEquals(true, testCar.equals(test));
    }
  
}
