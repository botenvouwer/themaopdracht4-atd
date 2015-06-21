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
public class UsedArticleTest {
    private UsedArticle testUsedArticle;
    private Long id = 15L;
    private int count = 12;
    private Article testArticle;
    
    public UsedArticleTest() {
        testUsedArticle = new UsedArticle();
        testUsedArticle.setId(id);
        testUsedArticle.setCount(count);
        testArticle = new Article();
        testArticle.setId(id);
        testUsedArticle.setArticle(testArticle);
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
     * Test of getId method, of class UsedArticle.
     */
    @Test
    public void testGetId() {
        assertEquals(id, testUsedArticle.getId(),0);
    }

    /**
     * Test of getArticle method, of class UsedArticle.
     */
    @Test
    public void testGetArticle() {
        assertEquals(testArticle, testUsedArticle.getArticle());
    }

    /**
     * Test of getCount method, of class UsedArticle.
     */
    @Test
    public void testGetCount() {
        assertEquals(count, testUsedArticle.getCount(),0);
    }

    /**
     * Test of equals method, of class UsedArticle.
     */
    @Test
    public void testEquals() {
        UsedArticle test = new UsedArticle();
        test.setId(id);
        assertEquals(true, testUsedArticle.equals(test));
    }
    
    @Test
    public void testEqualsWrong() {
        UsedArticle test = new UsedArticle();
        test.setId(id+ 1L);
        assertEquals(false, testUsedArticle.equals(test));
    }
}
