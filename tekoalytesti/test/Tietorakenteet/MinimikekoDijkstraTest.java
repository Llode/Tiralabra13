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
     * Test of getLeft method, of class MinimikekoDijkstra.
     */
    @Test
    public void testGetLeft() {
        System.out.println("getLeft");
        int pos = 0;
        MinimikekoDijkstra instance = null;
        int expResult = 0;
        int result = instance.getLeft(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRight method, of class MinimikekoDijkstra.
     */
    @Test
    public void testGetRight() {
        System.out.println("getRight");
        int pos = 0;
        MinimikekoDijkstra instance = null;
        int expResult = 0;
        int result = instance.getRight(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParent method, of class MinimikekoDijkstra.
     */
    @Test
    public void testGetParent() {
        System.out.println("getParent");
        int pos = 0;
        MinimikekoDijkstra instance = null;
        int expResult = 0;
        int result = instance.getParent(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        Koordinaatti koord = null;
        MinimikekoDijkstra instance = null;
        instance.insert(koord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMin method, of class MinimikekoDijkstra.
     */
    @Test
    public void testRemoveMin() {
        System.out.println("removeMin");
        MinimikekoDijkstra instance = null;
        Koordinaatti expResult = null;
        Koordinaatti result = instance.removeMin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        int ID = 0;
        int newk = 0;
        MinimikekoDijkstra instance = null;
        boolean expResult = false;
        boolean result = instance.laskeArvoa(ID, newk);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        MinimikekoDijkstra instance = null;
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndex method, of class MinimikekoDijkstra.
     */
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");
        MinimikekoDijkstra instance = null;
        int expResult = 0;
        int result = instance.getIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
