/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ghostai;

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
    public TreeSet<Koordinaatti> pino = new TreeSet<Koordinaatti>();

    /**
     * Tekee oikeankokoiset apumatriisit käytettävän labyrintin pohjalta.
     *
     * @param verkko Labyrintti, jossa reittiä etsitään.
     */
    void alustaTaulukot(int[][] verkko) {
        etaisyysAlkuun = new int[verkko.length][verkko[0].length];
        etaisyysLoppuun = new int[verkko.length][verkko[0].length];
        path = new Koordinaatti[verkko.length][verkko[0].length];
    }
//    /**
//     * Laskee kahden pisteen suorat etäisyydet labyrintissä.
//     *
//     * @param alkux aloituskoordinaatit
//     * @param alkuy aloituskoordinaatit
//     * @param loppux maalin koordinaatit
//     * @param loppuy maalin koordinaatit
//     * @return Palauttaa Manhattan-etäisyyden
//     */
//     int laskeEtaisyys(int alkux, int alkuy, int loppux, int loppuy) {
//        int etaisyysx = loppux - alkux;
//        int etaisyysy = loppuy - alkuy;
//
//        if (etaisyysx < 0) {
//            etaisyysx = etaisyysx*-1;
//        }
//        if (etaisyysy < 0) {
//            etaisyysy = etaisyysy*-1;
//        }
//        return etaisyysx + etaisyysy;
//    }

    /**
     * Alustaa etäisyysarvioihin käytettävät matriisit
     *
     * @param loppux maalin koordinaatit
     * @param loppuy maalin koordinaatit
     * @param alkux aloituskoordinaatit
     * @param alkuy aloituskoordinaatit
     */
    void alustaEtaisyydet(int[][] verkko, int loppux, int loppuy, int alkux, int alkuy) {
        for (int i = 0; i < verkko.length; i++) {
            for (int j = 0; j < verkko[0].length; j++) {
                etaisyysAlkuun[i][j] = max;
                etaisyysLoppuun[i][j] = Math.abs(loppuy - j) + Math.abs(loppux - i);
//              etaisyysLoppuun[i][j] = laskeEtaisyys(j, i, loppux, loppuy);
                Koordinaatti solmu = new Koordinaatti();
                solmu.setKoordinaatit(j, i);
                solmu.setEtaisyys(etaisyysAlkuun[i][j], etaisyysLoppuun[i][j]);
                pino.add(solmu);
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
    void relax(int[][] verkko, int x, int y) {
        int vx, vy;
        Koordinaatti crd = new Koordinaatti();

        if (x - 1 >= 0) {
            vx = x - 1;
            if (verkko[y][vx] == 0) {
                if (etaisyysAlkuun[y][vx] > etaisyysAlkuun[y][x] + 1) {
                    etaisyysAlkuun[y][vx] = etaisyysAlkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[y][vx] = crd;
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
                }
            }
        }
        if (y + 1 < etaisyysAlkuun.length) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (etaisyysAlkuun[vy][x] > etaisyysAlkuun[y][x] + 1) {
                    etaisyysAlkuun[vy][x] = etaisyysAlkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[vy][x] = crd;
                }
            }
        }
    }

    /**
     * Reitihakualgoritmi
     *
     * @param verkko Toisin sanoen labyrintti.
     * @param ax aloituskoordinaatit
     * @param ay aloituskoordinaatit
     * @param mx maalikoordinaatit
     * @param my maalikoordinaatit
     */
    void Astar(int[][] verkko, int ax, int ay, int mx, int my) {
        alustaTaulukot(verkko);
        alustaEtaisyydet(verkko, mx, my, ax, ay);
        while (pino.isEmpty() == false) {
            Koordinaatti solmu = pino.pollFirst();
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
    void TulostaReitti(int mx, int my) {
        int x = mx;
        int y = my;
        while (path[y][x] != null) {
            System.out.println(path[y][x]);
            x = path[y][x].getX();
            y = path[y][x].getY();
        }
    }
}
