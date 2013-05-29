/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Koordinaatti;
import Tietorakenteet.Minimikeko;
import java.util.Stack;

/**
 * Reitinhakualgoritmi
 *
 * @author Larppa
 */
public class Astar {

    /**
     * Teoreettinen maksimiarvo, joka annetaan etaisyysAlkuun-taulukon alkioille
     * alussa. Maksimietäisyys (labyrintin korkeus + leveys) on vähennetty, ettei
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
    Minimikeko keko;
    private Koordinaatti solmu = new Koordinaatti();
    private int alkux;
    private int alkuy;
    private int maalix;
    private int maaliy;
    private int[][] verkko;
    private Koordinaatti[][] polku = new Koordinaatti[25][25];
    private Koordinaatti startti;

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
    private void Init() {
        AlustaTaulukot();
        AlustaEtaisyydet();
    }

    /**
     * Tekee oikeankokoiset apumatriisit käytettävän labyrintin pohjalta.
     *
     */
    private void AlustaTaulukot() {
        int keonkoko = verkko.length * verkko[0].length + 1;
        sailio = new Koordinaatti[verkko.length][verkko[0].length];
        keko = new Minimikeko(keonkoko);

    }

    /**
     * Alustaa etäisyysarvioihin käytettävät matriisit
     *
     */
    private void AlustaEtaisyydet() {
        Koordinaatti koord;
        int loppuun;

        for (int y = 0; y < verkko.length; y++) {
            for (int x = 0; x < verkko[0].length; x++) {
                loppuun = Math.abs(maaliy - y) + Math.abs(maalix - x);
                koord = new Koordinaatti(x, y);

                if (OllaankoStartissa(koord)) {
                    koord.setEtaisyys(0, loppuun);
                    startti = koord;
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
    private boolean OllaankoStartissa(Koordinaatti crd) {
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
    private void Relax(Koordinaatti solmu) {
        int x = solmu.getX();
        int y = solmu.getY();

        RelaxVasen(x, y);
        RelaxOikea(x, y);
        RelaxYlos(y, x);
        RelaxAlas(y, x);
    }

    /**
     * Operoi tutkittavan solmun vasemmanpuoleisen solmun.
     *
     * @param x
     * @param y
     */
    private void RelaxVasen(int x, int y) {
        int vx;
        if (x - 1 >= 0) {
            vx = x - 1;
            if (verkko[y][vx] == 0) {
                if (sailio[y][vx].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[y][vx].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[y][vx].setPath(sailio[y][x]);
                    polku[y][vx] = sailio[y][x];
                    keko.laskeArvoa(sailio[y][vx].getID(), sailio[y][vx].getAlkuun());
                }
            }
        }
    }

    /**
     * Operoi tutkittavan solmun oikeanpuoleisen solmun.
     *
     * @param x
     * @param y
     */
    private void RelaxOikea(int x, int y) {
        int vx;
        if (x + 1 < verkko[0].length) {
            vx = x + 1;
            if (verkko[y][vx] == 0) {
                if (sailio[y][vx].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[y][vx].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[y][vx].setPath(sailio[y][x]);
                    polku[y][vx] = sailio[y][x];
                    keko.laskeArvoa(sailio[y][vx].getID(), sailio[y][vx].getAlkuun());
                }
            }
        }
    }

    /**
     * Operoi tutkittavan solmun yläpuolella olevan solmun.
     *
     * @param x
     * @param y
     */
    private void RelaxYlos(int y, int x) {
        int vy;
        if (y - 1 >= 0) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (sailio[vy][x].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[vy][x].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[vy][x].setPath(sailio[y][x]);
                    polku[vy][x] = sailio[y][x];
                    keko.laskeArvoa(sailio[vy][x].getID(), sailio[vy][x].getAlkuun());
                }
            }
        }
    }

    /**
     * Operoi tutkittavan solmun alapuolella olevan solmun.
     *
     * @param x
     * @param y
     */
    private void RelaxAlas(int y, int x) {
        int vy;
        if (y + 1 < verkko.length) {
            vy = y + 1;
            if (verkko[vy][x] == 0) {
                if (sailio[vy][x].getAlkuun() > sailio[y][x].getAlkuun() + 1) {

                    sailio[vy][x].setAlkuun(sailio[y][x].getAlkuun() + 1);
                    sailio[vy][x].setPath(sailio[y][x]);
                    polku[vy][x] = sailio[y][x];
                    keko.laskeArvoa(sailio[vy][x].getID(), sailio[vy][x].getAlkuun());
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
    private boolean OllaankoMaalissa(Koordinaatti solmu) {
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
        Koordinaatti reitti = polku[maaliy][maalix];
        Stack<Koordinaatti> pino = new Stack();
        while(reitti != startti){
            pino.push(reitti);
            reitti = polku[reitti.getY()][reitti.getX()];
        }
        System.out.println(reitti);
        while (!pino.empty()) {
            reitti = pino.pop();
            System.out.println(reitti);
        }
    }
}
