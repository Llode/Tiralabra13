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
public class KoordinaattipinoTest {

    public KoordinaattipinoTest() {
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
     * Test of pop and push methods, of class Koordinaattipino.
     */
    @Test
    public void testPushAndPop() {
        System.out.println("pop pop pop push!");
        Koordinaattipino instance = new Koordinaattipino(5);
        Koordinaatti crd = new Koordinaatti();
        for (int i = 0; i < 5; i++) {
            crd = new Koordinaatti(i, 2 * i);
            instance.push(crd);
        }

        Koordinaatti expResult = crd;
        Koordinaatti result = instance.pop();
        assertEquals(expResult, result);


    }

    /**
     * Test of isEmpty method, of class Koordinaattipino.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Koordinaattipino instance = new Koordinaattipino(5);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        Koordinaatti crd = new Koordinaatti(1, 4);
        instance.push(crd);

        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.pop();

        expResult = true;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of tuplaaTaulukko method, of class Koordinaattipino.
     */
    @Test
    public void testTuplaaTaulukko() {
        System.out.println("tuplaaTaulukko");
        Koordinaattipino instance = new Koordinaattipino(5);
        Koordinaatti crd = new Koordinaatti();
        
        for (int i = 0; i < 30; i++) {
            crd = new Koordinaatti(i, 2*i);
            instance.push(crd);
        }
        Koordinaatti[] pino = instance.getArray();
        assertEquals(pino.length, 40);
        assertEquals(instance.getSize(), 30);
        assertEquals(pino[instance.getSize()-1], crd);
    }
}
