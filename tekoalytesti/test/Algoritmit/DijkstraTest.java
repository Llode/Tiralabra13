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
        Dijkstra instance = new Dijkstra(Tekoalytesti.labyrintti, 1, 1, 10, 11);
        instance.Init();
        instance.RelaxMekaniikka(2, 1, 1, 1);
        int result = instance.sailio[1][2].getDistance();
        int expResult = instance.sailio[1][1].getDistance() + 1;
        assertEquals(expResult, result);


        int modx = 3;
        int mody = 5;
        int origx = 4;
        int origy = 5;
        instance.sailio[origy][origx].setDistance(7);
        instance.RelaxMekaniikka(modx, mody, origx, origy);

        int result2 = instance.sailio[mody][modx].getDistance();
        int expResult2 = instance.sailio[origy][origx].getDistance() + 1;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of Dijkstra method, of class Dijkstra.
     */
    @Test
    public void testDijkstra() {
        System.out.println("Dijkstra");
        Dijkstra instance = new Dijkstra(Tekoalytesti.labyrintti, 1, 1, 9, 9);
        assertTrue(instance.Dijkstra());

    }

    /**
     * Test of tarkastaArvot method, of class Astar.
     */
    @Test
    public void testTarkastaArvot() {
        System.out.println("tarkastaArvot");
        Dijkstra instance = new Dijkstra(Tekoalytesti.labyrintti, 0, 0, 1, 2);
        boolean expResult = false;
        boolean result = instance.tarkastaArvot();
        assertEquals(expResult, result);

        instance = new Dijkstra(Tekoalytesti.labyrintti, 1, 1, 9, 1);
        expResult = false;
        result = instance.tarkastaArvot();
        assertEquals(expResult, result);

        instance = new Dijkstra(Tekoalytesti.labyrintti, 1, 1, 10, 1);
        expResult = true;
        result = instance.tarkastaArvot();
        assertEquals(expResult, result);
    }

    @Test
    public void testaaSatunnaisia() {
        System.out.println("Testataan 100 000 satunnaista reittiä");
        Random rng = new Random();
        int[][] verkko = Tekoalytesti.labyrintti;
        int alkux;
        int alkuy;
        int maalix;
        int maaliy;

        for (int i = 0; i < 100000; i++) {
            alkux = rng.nextInt(19);
            alkuy = rng.nextInt(21);
            maalix = rng.nextInt(19);
            maaliy = rng.nextInt(21);
            if (verkko[alkuy][alkux] == 0 && verkko[maaliy][maalix] == 0) {
                Dijkstra instance = new Dijkstra(Tekoalytesti.labyrintti, alkux, alkuy, maalix, maaliy);
                
                assertTrue(instance.Dijkstra());
            }
        }
    }

}
