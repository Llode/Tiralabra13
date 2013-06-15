/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Larppa
 */
public class MinimikekoDijkstraTest {

    public MinimikekoDijkstraTest() {
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
     * Test of insert method, of class MinimikekoDijkstra.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Koordinaatti juttu = new Koordinaatti(1, 0);
        juttu.setEtaisyys(3, 5);
        Minimikeko instance = new Minimikeko(10);
        instance.insert(juttu);

        Koordinaatti expresult = instance.removeMin();
        assertEquals(expresult, juttu);
    }

    /**
     * Test of removeMin method, of class MinimikekoDijkstra.
     */
    @Test
    public void testRemoveMin() {
        System.out.println("removeMin");
        Minimikeko instance = new Minimikeko(10);
        for (int i = 0; i < 9; i++) {
            Koordinaatti juttu = new Koordinaatti();
            juttu.setEtaisyys(i);
            instance.insert(juttu);
        }
        Koordinaatti expResult = new Koordinaatti();
        expResult.setEtaisyys(0);
        Koordinaatti result = instance.removeMin();
        assertEquals(expResult.toString(), result.toString());

    }


    /**
     * Test of laskeArvoa method, of class MinimikekoDijkstra.
     */
    @Test
    public void testLaskeArvoa() {
        System.out.println("laskeArvoa");
        Minimikeko instance = new Minimikeko(20);
        Koordinaatti juttu;
        int expResult = 15;

        for (int i = 0; i < 14; i++) {
            juttu = new Koordinaatti(1 + i, 2);
            juttu.setDistance(10 + i);
            instance.insert(juttu);
        }

        juttu = new Koordinaatti(20, 20);
        juttu.setDistance(expResult);
        int avain = juttu.getID();
        instance.insert(juttu);

        boolean testi = instance.laskeArvoa(avain, expResult);
        assertTrue(testi);

        Koordinaatti result = new Koordinaatti();
        while (true) {
            result = instance.removeMin();
            if (result.getID() == avain) {
                break;
            }
        }
        assertEquals(result.getDistance(), expResult);
    }



    /**
     * Test of isEmpty method, of class MinimikekoDijkstra.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        MinimikekoDijkstra instance = new MinimikekoDijkstra(200);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        Koordinaatti koord = new Koordinaatti();
        instance.insert(koord);
        assertFalse(instance.isEmpty());
    }
}
