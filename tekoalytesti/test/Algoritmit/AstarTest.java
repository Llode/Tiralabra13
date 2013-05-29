/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tekoalytesti.Tekoalytesti;

/**
 *
 * @author Larppa
 */
public class AstarTest {
    
    public AstarTest() {
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
     * Test of Reitinhaku method, of class Astar.
     */
    @Test
    public void testReitinhaku() {
        System.out.println("Reitinhaku");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 10, 1);
        instance.Reitinhaku();

    }

    /**
     * Test of TulostaReitti method, of class Astar.
     */
    @Test
    public void testTulostaReitti() {
        System.out.println("TulostaReitti");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 1, 2);
        instance.Reitinhaku();
        instance.TulostaReitti();
        assertEquals(instance, this);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
