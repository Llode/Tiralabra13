/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Koordinaatti;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * REitinhakualgoritmi
 *
 * @author Larppa
 */
public class Astar {

    /**
     * Teoreettinen maksimiarvo, joka annetaan etaisyysAlkuun-taulukon alkioille
     * alussa. Maksimietäisyys (labyrintin korkeus + leveys) on poistettu, ettei
     * etaisyysAlkuun[y][x]+etaisyysLoppuun[y][x] aiheuttaisi virhettä.
     */
    public int max = Integer.MAX_VALUE - (19 + 21);
    /**
     * apumatriisi etäisyysarvioita varten
     */
    public int[][] etaisyysAlkuun;
    /**
     * apumatriisi etäisyysarvioita varten
     */
    public int[][] etaisyysLoppuun;
    public Koordinaatti[][] sailio;
    public Stack<Koordinaatti> polku = new Stack<Koordinaatti>();
    public PriorityQueue<Koordinaatti> pino = new PriorityQueue<Koordinaatti>();
    public Koordinaatti solmu = new Koordinaatti();

    /**
     * Tekee oikeankokoiset apumatriisit käytettävän labyrintin pohjalta.
     *
     * @param verkko Labyrintti, jossa reittiä etsitään.
     */
    public void alustaTaulukot(int[][] verkko) {
        sailio = new Koordinaatti[verkko.length][verkko[0].length];
//        etaisyysAlkuun = new int[verkko.length][verkko[0].length];
//        etaisyysLoppuun = new int[verkko.length][verkko[0].length];
//        path = new Koordinaatti[verkko.length][verkko[0].length];
    }

    /**
     * Alustaa etäisyysarvioihin käytettävät matriisit
     *
     * @param maalix maalin koordinaatit
     * @param maaliy maalin koordinaatit
     * @param alkux aloituskoordinaatit
     * @param alkuy aloituskoordinaatit
     */
    public void alustaEtaisyydet(int[][] verkko, int maalix, int maaliy, int alkux, int alkuy) {
        Koordinaatti koord;

        for (int i = 0; i < verkko.length; i++) { // i = 'y'
            for (int j = 0; j < verkko[0].length; j++) { // j = 'x'

                if (i == alkuy) {
                    if (j == alkux) {
                        koord = new Koordinaatti();
                        koord.setKoordinaatit(j, i);
                        koord.setAlkuun(0);
                        koord.setLoppuun(Math.abs(maaliy - i) + Math.abs(maalix - j));
                        koord.laskeEtaisyys();
                        pino.add(koord);
                        sailio[alkuy][alkux] = koord;
                    }
                }

                koord = new Koordinaatti();
                koord.setKoordinaatit(j, i);
                koord.setAlkuun(max);
                koord.setLoppuun(Math.abs(maaliy - i) + Math.abs(maalix - j));
                koord.laskeEtaisyys();
                pino.add(koord);
                sailio[i][j] = koord;

//                etaisyysAlkuun[i][j] = max;
//                etaisyysLoppuun[i][j] = Math.abs(maaliy - i) + Math.abs(maalix - j);
//
//                Koordinaatti crd = new Koordinaatti();
//                crd.setKoordinaatit(j, i);
//                crd.setEtaisyys(etaisyysAlkuun[i][j], etaisyysLoppuun[i][j]);
//                pino.add(crd);
            }
        }
    }

    /**
     * Kaarien löysäysoperaatio. Labyrintissa liikutaan vain pääilmansuuntiin,
     * joten käsiteltävällä solmulla on vain neljä vierussolmua.
     *
     * @param verkko tutkittava verkko/labyrintti/juttu
     * @param x tutkittavan solmun koordinaatit
     * @param y tutkittavan solmun koordinaatit
     */
    public void relax(int[][] verkko, int x, int y) {
        int vx;
        int vy;
        Koordinaatti crd = new Koordinaatti();

        if (x - 1 >= 0) {
            vx = x - 1;
            if (verkko[y][vx] == 0) {
                if (sailio[y][vx].getAlkuun() > sailio[y][x].getAlkuun() + 1) {
                    sailio[y][vx].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    crd.setKoordinaatit(x, y);
                    polku.push(crd);
                    pino.add(sailio[y][vx]);
                }
            }
        }
        if (x + 1 < verkko[0].length) {
            vx = x + 1;
            if (verkko[y][vx] == 0) {
                if (sailio[y][vx].getAlkuun() > sailio[y][x].getAlkuun() + 1) {
                    sailio[y][vx].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    crd.setKoordinaatit(x, y);
                    polku.push(crd);
                    pino.add(sailio[y][vx]);
                }
            }
        }
        if (y - 1 >= 0) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (sailio[vy][x].getAlkuun() > sailio[y][x].getAlkuun() + 1) {
                    sailio[vy][x].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    crd.setKoordinaatit(x, y);
                    polku.push(crd);
                    pino.add(sailio[vy][x]);
                }
            }
        }
        if (y + 1 < verkko.length) {
            vy = y + 1;
            if (verkko[vy][x] == 0) {
                if (sailio[vy][x].getAlkuun() > sailio[y][x].getAlkuun() + 1) {
                    sailio[vy][x].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    crd.setKoordinaatit(x, y);
                    polku.push(crd);
                    pino.add(sailio[vy][x]);
                }
            }
        }
    }

    /**
     * Reitihakualgoritmi
     *
     * @param verkko Toisin sanoen labyrintti.
     * @param alkux aloituskoordinaatit
     * @param alkuy aloituskoordinaatit
     * @param maalix maalikoordinaatit
     * @param maaliy maalikoordinaatit
     */
    public void Astar(int[][] verkko, int alkux, int alkuy, int maalix, int maaliy) {
        alustaTaulukot(verkko);
        alustaEtaisyydet(verkko, maalix, maaliy, alkux, alkuy);
        while (pino.isEmpty() == false) {
            solmu = pino.poll();
            relax(verkko, solmu.getX(), solmu.getY());
        }
    }

    /**
     * Printtaa reitin koordinaatit. Jossain vaiheessa jopa piirtää ne
     * labyrinttiin.
     *
     * @param mx maalin koordinaatit.
     * @param my maalin koordinaatit.
     */
    public void TulostaReitti() {
        while (polku.isEmpty() == false) {
            Koordinaatti asd = polku.pop();
            System.out.println(asd);
        }
//        while(path[y][x] != null) {
//            System.out.println(path[y][x]);
//            x = path[y][x].getX();
//            y = path[y][x].getY();
//        }
    }
}
