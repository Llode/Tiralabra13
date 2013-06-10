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
import java.util.Random;

/**
 *
 * @author Larppa
 */
public class AstarTest {

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Astar method, of class Astar.
     */
    @Test
    public void testReitinhaku() {
        System.out.println("Reitinhaku");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 10, 1);
        instance.Init();
        assertTrue(instance.Reitinhaku());

    }

    /**
     * Test of TallennaReitti method, of class Astar.
     */
    @Test
    public void testTulostaReitti() {
        System.out.println("TulostaReitti");
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 9, 9);
        instance.Astar();
        instance.TallennaReitti();
        assertTrue(instance.TallennaReitti());

    }

    /**
     * Test of RelaxMekaniikka method, of class Astar.
     */
    @Test
    public void testRelaxMekaniikka() {
        System.out.println("RelaxMekaniikka");

        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 10, 11);
        instance.Init();
        instance.RelaxMekaniikka(2, 1, 1, 1);

        int result = instance.sailio[1][2].getEtaisyys();
        int expResult = instance.sailio[1][1].getEtaisyys();
        assertEquals(expResult, result);


        int modx = 3;
        int mody = 5;
        int origx = 4;
        int origy = 5;
        instance.sailio[origy][origx].setAlkuun(7);
        instance.RelaxMekaniikka(modx, mody, origx, origy);

        int result2 = instance.sailio[mody][modx].getEtaisyys();
        int expResult2 = instance.sailio[origy][origx].getAlkuun() + 1
                + instance.sailio[mody][modx].getLoppuun();

        assertEquals(expResult2, result2);
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
    public void testaaPisinMatka() {
        Astar instance = new Astar(Tekoalytesti.labyrintti, 1, 1, 17, 19);
        instance.Init();
        assertTrue(instance.Reitinhaku());
        assertTrue(instance.TallennaReitti());

    }

    /**
     * Suorittaa reitinhaun 100 000 satunnaiselle maali- ja lähtösolmuparille.
     */
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
                
                Astar instance = new Astar(Tekoalytesti.labyrintti, alkux, alkuy, maalix, maaliy);
                instance.Init();
//                System.out.println("Startti: " + instance.sailio[alkuy][alkux] + " Maali: " + instance.sailio[maaliy][maalix]);
                assertTrue(instance.Astar());
            }
        }
    }
    /**
     * Testaa, että etäisyydet tallennetaan oikein taulukkoon
     * alustaEtaisyydet()-metodissa
     */
    @Test
    public void etaisyyksienAlustaminenToimii() {
        System.out.println("Init");

        int sx = 1;
        int sy = 1;
        int mx = 10;
        int my = 2;

        Astar instance = new Astar(Tekoalytesti.labyrintti, sx, sy, mx, my);
        instance.Init();
        Koordinaatti[][] sailio = instance.getSailio();

        int result;
        int alkuun;
        int loppuun;

        int expResult;

        for (int y = 0; y < sailio.length; y++) {
            for (int x = 0; x < sailio[0].length; x++) {
                if (Tekoalytesti.labyrintti[y][x] == 0) { //vain lattisolmuilla on etäisyysarvio
                    Koordinaatti crd = sailio[y][x];
                    result = crd.getEtaisyys();

                    if (y == sy && x == sx) {
                        alkuun = 0;
                    } else {
                        alkuun = instance.getMax();
                    }
                    
                    loppuun = Math.abs(my - y) + Math.abs(mx - x);
                    
                    expResult = alkuun + loppuun;
                    assertEquals(expResult, result);
                }

            }

        }

    }
}
