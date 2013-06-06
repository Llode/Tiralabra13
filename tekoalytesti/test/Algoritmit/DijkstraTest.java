/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import java.util.Random;
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
public class DijkstraTest {

    public DijkstraTest() {
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
     * Test of RelaxMekaniikka method, of class Dijkstra.
     */
    @Test
    public void testRelaxMekaniikka() {
        System.out.println("RelaxMekaniikka");
        int modx = 0;
        int mody = 0;
        int origx = 0;
        int origy = 0;
        Dijkstra instance = null;
        instance.RelaxMekaniikka(modx, mody, origx, origy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Dijkstra method, of class Dijkstra.
     */
    @Test
    public void testDijkstra() {
        System.out.println("Dijkstra");
        Dijkstra instance = new Dijkstra(Tekoalytesti.labyrintti, 1, 1, 9, 9);
        instance.Dijkstra();
        assertTrue(instance.TulostaReitti());

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
                Dijkstra instance = new Dijkstra(Tekoalytesti.labyrintti, alkux, alkuy, maalix, maaliy);
                instance.Dijkstra();
                assertTrue(instance.TulostaReitti());
            }
        }
    }
}
