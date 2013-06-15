/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Larppa
 */
public class KayttoliittymaTest {
    
    public KayttoliittymaTest() {
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
     * Test of Tekoalytesti method, of class Kayttoliittyma.
     */
    @Test
    public void testTekoalytesti() {
        System.out.println("Tekoalytesti");
        Kayttoliittyma instance = new Kayttoliittyma();
        instance.Tekoalytesti();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class Kayttoliittyma.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Kayttoliittyma instance = new Kayttoliittyma();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
