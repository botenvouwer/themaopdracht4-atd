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
 */
public class PersonTest {
    private Person testPerson;
    private String zipcode = "1234AB", email = "Test@test.nl", adress = "Straatnaam", name = "Henk", password = "Test", place = "Utrecht";
    private Person.Role role = Person.Role.BOSS;
    private Long id = 15L;
    
    public PersonTest() {
        testPerson = new Person();
        testPerson.setZipcode(zipcode);
        testPerson.setEmail(email);
        testPerson.setId(id);
        testPerson.setAdress(adress);
        testPerson.setName(name);
        testPerson.setPassword(password);
        testPerson.setPlace(place);
        testPerson.setRole(role);
        
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
     * Test of getId method, of class Person.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testPerson.getId(),0);
    }

    /**
     * Test of getRole method, of class Person.
     */
    @Test
    public void testGetRole() {
        assertEquals(role, testPerson.getRole());
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        assertEquals(name, testPerson.getName());
    }

    /**
     * Test of getEmail method, of class Person.
     */
    @Test
    public void testGetEmail() {
        assertEquals(email, testPerson.getEmail());
    }

    /**
     * Test of getPassword method, of class Person.
     */
    @Test
    public void testGetPassword() {
        assertEquals(password, testPerson.getPassword());
    }

    /**
     * Test of getPlace method, of class Person.
     */
    @Test
    public void testGetPlace() {
        assertEquals(place, testPerson.getPlace());
    }

    /**
     * Test of getZipcode method, of class Person.
     */
    @Test
    public void testGetZipcode() {
        assertEquals(zipcode, testPerson.getZipcode());
    }

    /**
     * Test of getAdress method, of class Person.
     */
    @Test
    public void testGetAdress() {
        assertEquals(adress, testPerson.getAdress());
    }

    /**
     * Test of isActive method, of class Person.
     */
    @Test
    public void testIsActive() {
        assertEquals(true, testPerson.isActive());
    }
    
    @Test
    public void testSetActive() {
        testPerson.setActive(false);
        assertEquals(false , testPerson.isActive());
    }

    /**
     * Test of equals method, of class Person.
     */
    @Test
    public void testEquals() {
        Person test = new Person();
        test.setId(id);
        assertEquals(true, testPerson.equals(test));
    }    
}
