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
import java.util.Random;

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
     * Test of Astar method, of class Astar.
     */
    @Test
    public void testReitinhaku() {
        System.out.println("Reitinhaku");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 10, 1);
        assertTrue(instance.Astar());

    }

    /**
     * Test of TulostaReitti method, of class Astar.
     */
    @Test
    public void testTulostaReitti() {
        System.out.println("TulostaReitti");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 9, 9);
        instance.Astar();
        instance.TulostaReitti();
        assertTrue(instance.TulostaReitti());

    }

    /**
     * Test of RelaxMekaniikka method, of class Astar.
     */
    @Test
    public void testRelaxMekaniikka() {
        System.out.println("RelaxMekaniikka");
        int modx = 0;
        int mody = 0;
        int origx = 0;
        int origy = 0;
        Astar instance = null;
        instance.RelaxMekaniikka(modx, mody, origx, origy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tarkastaArvot method, of class Astar.
     */
    @Test
    public void testTarkastaArvot() {
        System.out.println("tarkastaArvot");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 0, 0, 1, 2);
        boolean expResult = false;
        boolean result = instance.tarkastaArvot();
        assertEquals(expResult, result);

        instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 9, 1);
        expResult = false;
        result = instance.tarkastaArvot();
        assertEquals(expResult, result);

        instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 10, 1);
        expResult = true;
        result = instance.tarkastaArvot();
        assertEquals(expResult, result);
    }

    @Test
    public void testaaPisinMatka() {
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 17, 19);
        instance.Astar();
        assertTrue(instance.TulostaReitti());

    }

    @Test
    public void testaaSatunnaisia() {
        Random rng = new Random();
        int[][] verkko = Tekoalytesti.labyrintti;
        int alkux;
        int alkuy;
        int maalix;
        int maaliy;

        for (int i = 0; i < 500; i++) {
            alkux = rng.nextInt(19);
            alkuy = rng.nextInt(21);
            maalix = rng.nextInt(19);
            maaliy = rng.nextInt(21);
            if (verkko[alkuy][alkux] == 0 && verkko[maaliy][maalix] == 0) {
                Astar instance = new Astar(Tekoalytesti.labyrintti, alkux, alkuy, maalix, maaliy);
                instance.Astar();
                assertTrue(instance.TulostaReitti());
            }
        }
    }
}
