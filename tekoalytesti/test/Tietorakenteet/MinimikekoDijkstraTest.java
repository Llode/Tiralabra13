/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Algoritmit.Dijkstra;
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
     * Test of swap method, of class MinimikekoDijkstra.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        int pos1 = 0;
        int pos2 = 0;
        MinimikekoDijkstra instance = null;
        instance.swap(pos1, pos2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        boolean avain = instance.getPos(juttu.getID());
        assertTrue(avain);
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
     * Test of heapify method, of class MinimikekoDijkstra.
     */
    @Test
    public void testHeapify() {
        System.out.println("heapify");
        int index = 0;
        MinimikekoDijkstra instance = null;
        instance.heapify(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nostaArvoa method, of class MinimikekoDijkstra.
     */
    @Test
    public void testNostaArvoa() {
        System.out.println("nostaArvoa");
        int avain = 0;
        int newk = 0;
        MinimikekoDijkstra instance = null;
        boolean expResult = false;
        boolean result = instance.nostaArvoa(avain, newk);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of getPos method, of class MinimikekoDijkstra.
     */
    @Test
    public void testGetPos() {
        System.out.println("getPos");
        int ID = 0;
        MinimikekoDijkstra instance = null;
        boolean expResult = false;
        boolean result = instance.getPos(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
