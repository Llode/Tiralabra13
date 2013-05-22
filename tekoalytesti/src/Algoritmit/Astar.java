/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Koordinaatti;
import Tietorakenteet.Minimikeko;
import java.util.PriorityQueue;

/**
 * Reitinhakualgoritmi
 *
 * @author Larppa
 */
public class Astar {

    /**
     * Teoreettinen maksimiarvo, joka annetaan etaisyysAlkuun-taulukon alkioille
     * alussa. Maksimietäisyys (labyrintin korkeus + leveys) on poistettu, ettei
     * etaisyysAlkuun[y][x]+etaisyysLoppuun[y][x] aiheuttaisi virhettä.
     */
    private final int max = Integer.MAX_VALUE - (19 + 21);
    /**
     * Säilöö koordinaatit tietoinen omille paikoilleen matriisiin.
     */
    private Koordinaatti[][] sailio;
    /**
     * minimikeon korvike reitinhakua varten.
     */
    private Minimikeko keko;
    private Koordinaatti solmu = new Koordinaatti();
    private int alkux;
    private int alkuy;
    private int maalix;
    private int maaliy;
    private int[][] verkko;

    /**
     * Konstruktori
     *
     * @param labyrintti tutkittava verkko
     * @param aloitusx aloituskoordinaatti
     * @param aloitusy aloituskoordinaatti
     * @param maalix maalikoordinaatti
     * @param maaliy lottoapa
     */
    public Astar(int[][] labyrintti, int aloitusx, int aloitusy, int maalix, int maaliy) {
        this.verkko = labyrintti;
        this.alkux = aloitusx;
        this.alkuy = aloitusy;
        this.maalix = maalix;
        this.maaliy = maaliy;
    }

    /**
     * Taulukoiden ja etäisyyksien alustaminen samassa paketissa.
     */
    public void Init() {
        AlustaTaulukot();
        AlustaEtaisyydet();
    }

    /**
     * Tekee oikeankokoiset apumatriisit käytettävän labyrintin pohjalta.
     *
     */
    public void AlustaTaulukot() {
        int keonkoko = verkko.length * verkko[0].length;
        sailio = new Koordinaatti[verkko.length][verkko[0].length];
        keko = new Minimikeko(keonkoko);

    }

    /**
     * Alustaa etäisyysarvioihin käytettävät matriisit
     *
     */
    public void AlustaEtaisyydet() {
        Koordinaatti koord;
        int loppuun;

        for (int y = 0; y < verkko.length; y++) {
            for (int x = 0; x < verkko[0].length; x++) {
                loppuun = Math.abs(maaliy - y) + Math.abs(maalix - x);
                koord = new Koordinaatti(x, y);

                if (OllaankoStartissa(koord)) {
                    koord.setEtaisyys(0, loppuun);
                } else {
                    koord.setEtaisyys(max, loppuun);
                }

                if (verkko[y][x] == 0) {
                    keko.insert(koord);
                }
                sailio[y][x] = koord;

            }
        }
    }

    /**
     * True, jos tutkittava koordinaatti on aloituskoordinaatti
     *
     * @param crd Tutkittava koordinaatti.
     * @return
     */
    public boolean OllaankoStartissa(Koordinaatti crd) {
        if (crd.getX() == alkux) {
            if (crd.getY() == alkuy) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kaarien löysäysoperaatio. Labyrintissa liikutaan vain pääilmansuuntiin,
     * joten käsiteltävällä solmulla on vain neljä vierussolmua.
     *
     * @param solmu Keosta popattu tutkittava solmu.
     */
    public void Relax(Koordinaatti solmu) {
        int x = solmu.getX();
        int y = solmu.getY();

        int vx;
        int vy;

        if (x - 1 >= 0) {
            vx = x - 1;
            if (verkko[y][vx] == 0) {
                if (sailio[y][vx].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[y][vx].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[y][vx].setPath(sailio[y][x]);
                    keko.insert(sailio[y][vx]);

                }
            }
        }

        if (x + 1 < verkko[0].length) {
            vx = x + 1;
            if (verkko[y][vx] == 0) {
                if (sailio[y][vx].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[y][vx].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[y][vx].setPath(sailio[y][x]);
                    keko.insert(sailio[y][vx]);
                }
            }
        }

        if (y - 1 >= 0) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (sailio[vy][x].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[vy][x].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[vy][x].setPath(sailio[y][x]);
                    keko.insert(sailio[vy][y]);
                }
            }
        }

        if (y + 1 < verkko.length) {
            vy = y + 1;
            if (verkko[vy][x] == 0) {
                if (sailio[vy][x].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[vy][x].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[vy][x].setPath(sailio[y][x]);
                    keko.insert(sailio[vy][x]);
                }
            }
        }
    }

    /**
     * Itse Reitihakualgoritmi. Kaiken pitäisi toimia tästä.
     */
    public void Reitinhaku() {
        Init();
        while (!OllaankoMaalissa(solmu)) {
            solmu = keko.removeMin();
            Relax(solmu);
        }
    }

    /**
     * True, jos tutkittava solmu on maalisolmu.
     *
     * @param solmu tutkittava solmu
     * @return
     */
    public boolean OllaankoMaalissa(Koordinaatti solmu) {
        if (solmu.getX() == maalix) {
            if (solmu.getY() == maaliy) {
                return true;
            }
        }
        return false;
    }

    /**
     * Printtaa reitin koordinaatit. Jossain vaiheessa jopa piirtää ne
     * labyrinttiin.
     *
     */
    public void TulostaReitti() {
        Koordinaatti reitti = sailio[maaliy][maalix].getPath();
        System.out.println(reitti);

        while (reitti != null) {
            System.out.println(reitti);
            reitti = reitti.getPath();
        }
    }
}
