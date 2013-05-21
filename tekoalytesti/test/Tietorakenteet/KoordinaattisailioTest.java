/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

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
public class KoordinaattisailioTest {
    
    public KoordinaattisailioTest() {
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
     * Test of uusiSailio method, of class Koordinaattisailio.
     */
    @Test
    public void testUusiSailio() {
        System.out.println("uusiSailio");
        int koko = 0;
        Koordinaattisailio instance = new Koordinaattisailio();
        instance.uusiSailio(koko);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sailioInsert method, of class Koordinaattisailio.
     */
    @Test
    public void testSailioInsert() {
        System.out.println("sailioInsert");
        Koordinaatti crd = null;
        Koordinaattisailio instance = new Koordinaattisailio();
        instance.sailioInsert(crd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
