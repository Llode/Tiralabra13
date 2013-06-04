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
        double vertailutarkkuus = 0.00001;
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
     * Testaa inserttia ja getPosia.
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
     * Test of removeMin method, of class Minimikeko.
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
     * Test of laskeArvoa method, of class Minimikeko.
     */
    @Test
    public void testLaskeArvoa() {
        System.out.println("laskeArvoa");
        Minimikeko instance = new Minimikeko(20);

        Koordinaatti juttu = new Koordinaatti(20, 20);
        juttu.setAlkuun(15);
        int avain = juttu.getID();
        instance.insert(juttu);

        for (int i = 0; i < 9; i++) {
            juttu = new Koordinaatti(1 + i, 2 + i);
            juttu.setAlkuun(i + 10);
            instance.insert(juttu);
        }

        int expResult = 2;

        boolean asd = instance.laskeArvoa(avain, expResult);
        assertTrue(asd);

        Koordinaatti result = new Koordinaatti();
        while (true) {
            result = instance.removeMin();
            if (result.getID() == avain) {
                break;
            }
        }
        assertEquals(result.getAlkuun(), 2);
    }

    /**
     * Test of heapify method, of class Minimikeko.
     */
    @Test
    public void testHeapify() {
        System.out.println("heapify");
        int i = 0;
        Minimikeko instance = null;
        instance.heapify(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPos method, of class Minimikeko.
     */
    @Test
    public void testGetPos() {
        System.out.println("getPos");
        Minimikeko instance = new Minimikeko(300);
        Koordinaatti juttu = null;

        for (int i = 0; i < 9; i++) {
            juttu = new Koordinaatti(1 + i, 2 + i);
            juttu.setAlkuun(i + 10);
            instance.insert(juttu);
        }
        int avain = juttu.getID();

        boolean expResult = true;
        boolean result = instance.getPos(avain);
        assertEquals(expResult, result);

        int i = instance.getIndex();
        assertEquals(instance.keko[i].getID(), avain);

    }
}
