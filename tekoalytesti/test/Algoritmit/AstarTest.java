/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Koordinaatti;
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
public class AstarTest {
    Astar algo;
    Koordinaatti naatti, varanaatti;
    double vertailutarkkuus = 0.0001;
    

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
        naatti = new Koordinaatti(2, 2);
        algo = new Astar(Tekoalytesti.labyrintti, 2, 2, 3, 3);
    }

    @Test
    public void maalinJaStartinTarkastusToimii(){
        boolean Testi = algo.OllaankoStartissa(naatti);
        assertEquals(true, Testi);
        naatti.setKoordinaatit(3, 3);
        Testi = algo.OllaankoMaalissa(naatti);
        assertEquals(true, Testi);
   }
    @After
    public void tearDown() {
    }

    /**
     * Test of Init method, of class Astar.
     */
    @Test
    public void testInit() {
        System.out.println("Init");
        Astar instance = null;
        instance.Init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AlustaTaulukot method, of class Astar.
     */
    @Test
    public void testAlustaTaulukot() {
        System.out.println("AlustaTaulukot");
        Astar instance = null;
        instance.AlustaTaulukot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AlustaEtaisyydet method, of class Astar.
     */
    @Test
    public void testAlustaEtaisyydet() {
        System.out.println("AlustaEtaisyydet");
        Astar instance = null;
        instance.AlustaEtaisyydet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of Relax method, of class Astar.
     */
    @Test
    public void testRelax() {
        System.out.println("Relax");
        Koordinaatti solmu = null;
        Astar instance = null;
        instance.Relax(solmu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Reitinhaku method, of class Astar.
     */
    @Test
    public void testReitinhaku() {
        System.out.println("Reitinhaku");
        Astar instance = null;
        instance.Reitinhaku();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of TulostaReitti method, of class Astar.
     */
    @Test
    public void testTulostaReitti() {
        System.out.println("TulostaReitti");
        Astar instance = null;
        instance.TulostaReitti();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
