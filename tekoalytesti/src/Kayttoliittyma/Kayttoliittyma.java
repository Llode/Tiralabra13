/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Algoritmit.Astar;
import Algoritmit.Dijkstra;
import Tietorakenteet.Koordinaatti;
import java.util.Scanner;

/**
 * Yksinkertainen komentorivikäyttöliittymä. Käytännössä myös suorittaa
 * ohjelman.
 *
 * @author Larppa
 */
public class Kayttoliittyma {

    /**
     * @param args the command line arguments
     */
    public static final int[][] labyrintti = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int aloitusx;
    private int aloitusy;
    private int maalix;
    private int maaliy;
    private Scanner skanneri;
    private boolean jatkuukoSuoritus;
    private int[][] visualisointi;

    /**
     * Käynnistää ohjelman suorituksen. Kyselee koordinaatit ja tulostaa lopuksi
     * reitin näkyviin.
     *
     */
    public void Tekoalytesti() {
        skanneri = new Scanner(System.in);
        init();
    }

    /**
     * Starttaa ohjelman suorituksen.
     */
    public void init() {
        jatkuukoSuoritus = true;
        kyseleKoordinaatit();

        if (jatkuukoSuoritus) {
            if (valitseAlgo()) {
                piirraKartta();
            }
        }
    }

    /**
     * Valitsee käytettävän algoritmin skannerin syötteiden perusteella.
     */
    private boolean valitseAlgo() {
        System.out.println("Valitse 1, jos haluat käyttää Dijkstraa tai 2,"
                + " jos haluat käyttää Astaria. Muut lopettavat");
        int valinta = skanneri.nextInt();

        if (valinta == 1) {
            valitseDijkstra();
            return true;
        }
        if (valinta == 2) {
            valitseAstar();
            return true;
        }

        return false;

    }

    /**
     * Kyselee skannerin avulla halutut koordinaatit käyttäjältä.
     */
    private void kyseleKoordinaatit() {
        System.out.println("Anna lähtö- ja maalikoordinaatit.");
        System.out.println("Startti-X: ");
        aloitusx = skanneri.nextInt();
        System.out.println("Startti-Y: ");
        aloitusy = skanneri.nextInt();
        System.out.println("Maali-X: ");
        maalix = skanneri.nextInt();
        System.out.println("Maali-Y: ");
        maaliy = skanneri.nextInt();
        tarkastaArvot();
    }

    /**
     * Kopioi labyrintin uuteen matriisiin ja piirtää löydetyn reitin siihen.
     *
     * @param astar Käytettävä reitinhaku (koska pinon sisältö pitää kaivaa
     * sieltä)
     */
    private void visualisoiA(Astar astar) {
        visualisointi = labyrintti;
        Koordinaatti[] polku = astar.getPolku();
        int i = 0;
        while (polku[i] != null) {
            int x = polku[i].getX();
            int y = polku[i].getY();
            visualisointi[y][x] = 8;
            i++;
        }
    }

    /**
     * Kopioi labyrintin uuteen matriisiin ja piirtää löydetyn reitin siihen.
     *
     * @param dijkstra Käytettävä reitinhaku (koska pinon sisältö pitää kaivaa
     * sieltä)
     */
    private void visualisoiD(Dijkstra dijkstra) {
        visualisointi = labyrintti;
        Koordinaatti[] polku = dijkstra.getPolku();
        int i = 0;
        while (polku[i] != null) {
            int x = polku[i].getX();
            int y = polku[i].getY();
            visualisointi[y][x] = 8;
            i++;
        }
    }

    /**
     * Tulostaa matriisin, johon käytettävä reitti on piirretty.
     *
     */
    private void piirraKartta() {
        for (int y = 0; y < visualisointi.length; y++) {
            for (int x = 0; x < visualisointi[0].length; x++) {
                System.out.print(visualisointi[y][x] + " ");

            }
            System.out.println();
        }
    }

    /**
     * Tarkastaa, että käytettävät lähtö- ja maalipisteet ovat valideja l.
     * molemmat ovat lattiaa.
     *
     */
    private void tarkastaArvot() {
        if (aloitusy > 20 || maaliy > 20 || aloitusx > 18 || maalix > 18) {
            System.out.println("Annoit liian isoja arvoja. X-max: 18, Y-max: 20");
            Uusintayritys();
        }
        if (labyrintti[aloitusy][aloitusx] == 1 || labyrintti[maaliy][maalix] == 1) {
            Uusintayritys();
        }
    }

    /**
     * Suorittaa reitinhaun Dijkstralla.
     */
    private void valitseDijkstra() {
        System.out.println("Dijkstra");
        Dijkstra algo = new Dijkstra(labyrintti, aloitusx, aloitusy, maalix, maaliy);
        algo.Dijkstra();

        visualisoiD(algo);
    }

    /**
     * Suorittaa reitinhaun Astarilla.
     */
    private void valitseAstar() {
        System.out.println("A*");
        Astar algo = new Astar(labyrintti, aloitusx, aloitusy, maalix, maaliy);
        algo.Astar();

        visualisoiA(algo);
    }
/**
 * Suorittaa koordinaattikyselyn uudestaan, jos käyttäjä näin tahtoo.
 */
    private void Uusintayritys() {
        System.out.println("Huonot koordinaatit. Syötä 1, jos haluat yrittää uudelleen. Muut lopettavat");
        int varmistus = skanneri.nextInt();
        if (varmistus == 1) {
            kyseleKoordinaatit();
        } else {
            jatkuukoSuoritus = false;
        }
    }
}
