package Tietorakenteet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class KoordinaattiTest {

    Koordinaatti naatti, varanaatti;
    double vertailutarkkuus = 0.0001;

    public KoordinaattiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        naatti = new Koordinaatti();
        naatti.setKoordinaatit(5, 10);
        naatti.setEtaisyys(12, 8);

        varanaatti = new Koordinaatti();
        varanaatti.setKoordinaatit(4, 10);
        varanaatti.setEtaisyys(11, 7);
    }

    @Test
    public void EtaisyysOnOikein() {
        assertEquals(20, naatti.getEtaisyys(), vertailutarkkuus);
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /**
     * Test of setPath method, of class Koordinaatti.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        Koordinaatti solmu = new Koordinaatti(1, 2);
        Koordinaatti instance = new Koordinaatti(1, 1);
        instance.setPath(solmu);
        assertEquals(solmu, instance.getPath());
    }

    /**
     * Test of getPath method, of class Koordinaatti.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Koordinaatti instance = new Koordinaatti(2, 2);
        instance.setPath(naatti);
        Koordinaatti expResult = naatti;
        Koordinaatti result = instance.getPath();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEtaisyys method, of class Koordinaatti.
     */
    @Test
    public void testSetEtaisyys() {
        System.out.println("setEtaisyys");
        int alkuun = 0;
        int loppuun = 0;
        Koordinaatti instance = new Koordinaatti();
        instance.setEtaisyys(alkuun, loppuun);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEtaisyys method, of class Koordinaatti.
     */
    @Test
    public void testGetEtaisyys() {
        System.out.println("getEtaisyys");
        Koordinaatti instance = new Koordinaatti();
        int expResult = 0;
        int result = instance.getEtaisyys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laskeEtaisyys method, of class Koordinaatti.
     */
    @Test
    public void testLaskeEtaisyys() {
        System.out.println("laskeEtaisyys");
        Koordinaatti instance = new Koordinaatti();
        instance.laskeEtaisyys();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAlkuun method, of class Koordinaatti.
     */
    @Test
    public void testSetAlkuun() {
        System.out.println("setAlkuun");
        int alkuun = 0;
        Koordinaatti instance = new Koordinaatti();
        instance.setAlkuun(alkuun);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoppuun method, of class Koordinaatti.
     */
    @Test
    public void testSetLoppuun() {
        System.out.println("setLoppuun");
        int loppuun = 0;
        Koordinaatti instance = new Koordinaatti();
        instance.setLoppuun(loppuun);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlkuun method, of class Koordinaatti.
     */
    @Test
    public void testGetAlkuun() {
        System.out.println("getAlkuun");
        Koordinaatti instance = new Koordinaatti();
        int expResult = 0;
        int result = instance.getAlkuun();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoppuun method, of class Koordinaatti.
     */
    @Test
    public void testGetLoppuun() {
        System.out.println("getLoppuun");
        Koordinaatti instance = new Koordinaatti();
        int expResult = 0;
        int result = instance.getLoppuun();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKoordinaatit method, of class Koordinaatti.
     */
    @Test
    public void testSetKoordinaatit() {
        System.out.println("setKoordinaatit");
        int x = 1;
        int y = 2;
        Koordinaatti instance = new Koordinaatti();
        instance.setKoordinaatit(x, y);
        int resultx = 1;
        int resulty = 2;
        assertEquals(instance.getX(), resultx, vertailutarkkuus);
        assertEquals(instance.getY(), resulty, vertailutarkkuus);
        
    }


    /**
     * Test of setY method, of class Koordinaatti.
     */
    @Test
    public void testSetGetXY() {
        System.out.println("setY");
        int y = 10;
        int x = 5;
        Koordinaatti instance = new Koordinaatti();
        instance.setY(y);
        instance.setX(x);
        int result = instance.getX();
        int expResult = 5;
        assertEquals(expResult, result);
        
        expResult = 10;
        result = instance.getY();
        assertEquals(expResult, result);
    }


    /**
     * Test of toString method, of class Koordinaatti.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Koordinaatti instance = new Koordinaatti(1, 2);
        String expResult = "(1, 2)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
