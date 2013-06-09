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
import java.util.Random;

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
     * Testaa inserttia ja getPosia.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Koordinaatti juttu = new Koordinaatti(1, 0);
        juttu.setEtaisyys(3, 5);

        Minimikeko instance = new Minimikeko(10);
        instance.insert(juttu);


        boolean insertOnnistuu = false;
        if (instance.removeMin() != null) {
            insertOnnistuu = true;
        }
        assertTrue(insertOnnistuu);


    }

    /**
     * Test of removeMin method, of class Minimikeko.
     */
    @Test
    public void testRemoveMin() {
        System.out.println("removeMin");

        Minimikeko instance = new Minimikeko(100);

        for (int i = 1; i < 19; i++) {
            Koordinaatti juttu = new Koordinaatti(10 + i, i);
            juttu.setEtaisyys(i);
            instance.insert(juttu);
        }
        Koordinaatti expResult = new Koordinaatti(11, 1);
        expResult.setEtaisyys(1);
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
        Koordinaatti juttu = new Koordinaatti();
        juttu.nollaaIDLaskuri();
        int expResult = 2;

        for (int j = 0; j < 14; j++) {
            juttu = new Koordinaatti(1 + j, 2);
            juttu.setEtaisyys(j + 2, j + 30);
            instance.insert(juttu);
        }

        juttu = new Koordinaatti(20, 20);
        juttu.setEtaisyys(10, 25);
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
        assertEquals(result.getAlkuun(), expResult);
    }

    /**
     * Test of heapify method, of class Minimikeko.
     */
    @Test
    public void testHeapifySatunnaisillaTaulukoilla() {
        System.out.println("heapify 100000 satunnaisella taulukolla");
        Random rng = new Random();
        int i = 1;
     
        for (int k = 0; k < 100000; k++) {

            Minimikeko instance = new Minimikeko(500);

            for (int j = 1; j < 500; j++) {
                Koordinaatti crd = new Koordinaatti(1 + i, 2 + (2 * i));
                crd.setEtaisyys(rng.nextInt());
                instance.insert(crd);
            }
            
            instance.heapify(i);
            Koordinaatti[] taulu = instance.getArray();

            for (int j = 2; j < taulu.length; j++) {
                if (taulu[i].getEtaisyys() > taulu[j].getEtaisyys()) {
                    fail("Heapify failed");
                }
            }
        }

    }

    /**
     * Test of isEmpty method, of class MinimikekoDijkstra.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Minimikeko instance = new Minimikeko(200);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        Koordinaatti koord = new Koordinaatti();
        instance.insert(koord);
        assertFalse(instance.isEmpty());
    }

    /**
     * Test of swap method, of class Minimikeko.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        int pos1 = 7;
        int pos2 = 3;
        Minimikeko instance = new Minimikeko(50);

        for (int i = 0; i < 15; i++) {
            Koordinaatti crd = new Koordinaatti(1 + i, 25 - i);
            instance.insert(crd);
        }

        Koordinaatti[] keko = instance.getArray();
        Koordinaatti expResult = keko[pos2];
        instance.swap(pos1, pos2);
        Koordinaatti Result = keko[pos1];
        assertEquals(expResult, Result);

    }
}
