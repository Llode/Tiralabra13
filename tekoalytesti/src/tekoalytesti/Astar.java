/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoalytesti;

import java.util.Stack;
import java.util.TreeSet;


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
    /**
     * Lyhin polku startista maaliin.
     */
    public Koordinaatti[][] path;

    
    public Stack<Koordinaatti> polku = new Stack<Koordinaatti>();
    public TreeSet<Koordinaatti> pino = new TreeSet<Koordinaatti>();
    public Koordinaatti solmu = new Koordinaatti();

    

    /**
     * Tekee oikeankokoiset apumatriisit käytettävän labyrintin pohjalta.
     *
     * @param verkko Labyrintti, jossa reittiä etsitään.
     */
    public void alustaTaulukot(int[][] verkko) {
        etaisyysAlkuun = new int[verkko.length][verkko[0].length];
        etaisyysLoppuun = new int[verkko.length][verkko[0].length];
        path = new Koordinaatti[verkko.length][verkko[0].length];
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
        for (int i = 0; i < verkko.length; i++) {
            for (int j = 0; j < verkko[0].length; j++) {
                etaisyysAlkuun[i][j] = max;
                etaisyysLoppuun[i][j] = Math.abs(maaliy - i) + Math.abs(maalix - j);

                Koordinaatti crd = new Koordinaatti();
                crd.setKoordinaatit(j, i);
                crd.setEtaisyys(etaisyysAlkuun[i][j], etaisyysLoppuun[i][j]);
                pino.add(crd);
            }
        }
        etaisyysAlkuun[alkuy][alkux] = 0;
    }

    /**
     * Kaarien löysäysoperaatio. Labyrintissa liikutaan vain pääilmansuuntiin,
     * joten käsiteltävällä solmulla on vain neljä vierussolmua.
     *
     * @param etaisyysAlkuun etäisyysarviot
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
                if (etaisyysAlkuun[y][vx] > etaisyysAlkuun[y][x] + 1) {
                    etaisyysAlkuun[y][vx] = etaisyysAlkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[y][vx] = crd;
                    polku.addElement(crd);
                }
            }
        }
        if (x + 1 < etaisyysAlkuun[0].length) {
            vx = x + 1;
            if (verkko[y][vx] == 0) {
                if (etaisyysAlkuun[y][vx] > etaisyysAlkuun[y][x] + 1) {
                    etaisyysAlkuun[y][vx] = etaisyysAlkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[y][vx] = crd;
                    polku.addElement(crd);
                }
            }
        }
        if (y - 1 >= 0) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (etaisyysAlkuun[vy][x] > etaisyysAlkuun[y][x] + 1) {
                    etaisyysAlkuun[vy][x] = etaisyysAlkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[vy][x] = crd;
                    polku.addElement(crd);
                }
            }
        }
        if (y + 1 < etaisyysAlkuun.length) {
            vy = y + 1;
            if (verkko[vy][x] == 0) {
                if (etaisyysAlkuun[vy][x] > etaisyysAlkuun[y][x] + 1) {
                    etaisyysAlkuun[vy][x] = etaisyysAlkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[vy][x] = crd;
                    polku.addElement(crd);
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
            solmu = pino.pollFirst();
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
    public void TulostaReitti(int mx, int my) {
        int x = mx;
        int y = my;
        while (polku.empty() == false) {
            System.out.println(polku.pop());

        }
//        while(path[y][x] != null) {
//            System.out.println(path[y][x]);
//            x = path[y][x].getX();
//            y = path[y][x].getY();
//        }
    }
}
