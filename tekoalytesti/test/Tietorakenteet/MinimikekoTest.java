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
public class MinimikekoTest {
    
    public MinimikekoTest() {
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
     * Test of insert method, of class Minimikeko.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Koordinaatti juttu = null;
        Minimikeko instance = null;
        instance.insert(juttu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMin method, of class Minimikeko.
     */
    @Test
    public void testRemoveMin() {
        System.out.println("removeMin");
        Minimikeko instance = null;
        Koordinaatti expResult = null;
        Koordinaatti result = instance.removeMin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laskeArvoa method, of class Minimikeko.
     */
    @Test
    public void testLaskeArvoa() {
        System.out.println("laskeArvoa");
        int pos = 0;
        int uusiArvo = 0;
        Minimikeko instance = null;
        instance.laskeArvoa(pos, uusiArvo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
