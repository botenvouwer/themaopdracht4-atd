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
 * Tests on getters will also check the setters since they are used to set the values.
 */
public class ArticleTest {
    private Article testArticle;
    
    public ArticleTest() {
        testArticle = new Article();
        testArticle.setId(15L);
        testArticle.setName("Achterklep");
        testArticle.setPrice(120);
        testArticle.setStock(45);
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
        testArticle = null;
    }

    /**
     * Test of getId method, of class Article.
     */
    @org.junit.Test
    public void testGetId() {
        assertEquals(15L, testArticle.getId(), 0L);
    }

    /**
     * Test of getName method, of class Article.
     */
    @org.junit.Test
    public void testGetName() {
        assertEquals("Achterklep", testArticle.getName());
    }

    /**
     * Test of getPrice method, of class Article.
     */
    @org.junit.Test
    public void testGetPrice() {
        assertEquals(120, testArticle.getPrice(), 0);
    }

    /**
     * Test of getStock method, of class Article.
     */
    @org.junit.Test
    public void testGetStock() {
        assertEquals(45, testArticle.getStock(),0);
    }
    
    /**
     * Test of getDelete method, of class Article.
     */
    @org.junit.Test
    public void testGetDelete() {
        assertEquals(false, testArticle.getDelete());
    }

    /**
     * Test of setDelete method, of class Article.
     */
    @org.junit.Test
    public void testSetDelete() {
        testArticle.setDelete(true);
        assertEquals(true, testArticle.getDelete());
    }

    /**
     * Test of equals method, of class Article.
     */
    @org.junit.Test
    public void testEquals() {
        Article test = new Article();
        test.setId(15L);
        assertEquals(true, testArticle.equals(test));
    }    
}
