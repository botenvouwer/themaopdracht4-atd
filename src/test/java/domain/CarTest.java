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
    private Long id= 15L;
    private String brand = "Audi", model = "R8", licenseplate = "AA-65-LPG";
    
    public CarTest() {
        testCar = new Car();
        testCar.setId(id);
        testCar.setLicensePlate(licenseplate);
        testCar.setModel(model);
        testCar.setBrand(brand);
        testPerson = new Person();
        testPerson.setId(id);
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
        assertEquals(id, testCar.getId(),0);
    }

    /**
     * Test of getLicensePlate method, of class Car.
     */
    @Test
    public void testGetLicensePlate() {
        assertEquals(licenseplate, testCar.getLicensePlate());
    }

    /**
     * Test of getBrand method, of class Car.
     */
    @Test
    public void testGetBrand() {
        assertEquals(brand, testCar.getBrand());
    }

    /**
     * Test of getModel method, of class Car.
     */
    @Test
    public void testGetModel() {
        assertEquals(model, testCar.getModel());
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
        test.setId(id);
        assertEquals(true, testCar.equals(test));
    }
    
    @Test
    public void testEqualsWrong() {
        Car test = new Car();
        test.setId((id+1L));
        assertEquals(false, testCar.equals(test));
    }
  
}
