/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Koordinaatti;
import Tietorakenteet.MinimikekoDijkstra;
import java.util.Stack;

/**
 * Reitinhakualgoritmi
 *
 * @author Larppa
 */
public class Dijkstra {

    /**
     * Teoreettinen maksimiarvo, joka annetaan etaisyysAlkuun-taulukon alkioille
     * alussa. Maksimietäisyys (labyrintin korkeus + leveys) on vähennetty,
     * ettei etaisyysAlkuun[y][x]+etaisyysLoppuun[y][x] aiheuttaisi virhettä.
     */
    private final int max = Integer.MAX_VALUE - (19 + 21);
    /**
     * Säilöö koordinaatit tietoinen omille paikoilleen matriisiin.
     */
    private Koordinaatti[][] sailio;
    /**
     * minimikeon korvike reitinhakua varten.
     */
    MinimikekoDijkstra keko;
    // private Koordinaatti solmu = new Koordinaatti();
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
    public Dijkstra(int[][] labyrintti, int alkux, int alkuy, int maalix, int maaliy) {
        this.verkko = labyrintti;
        this.alkux = alkux;
        this.alkuy = alkuy;
        this.maalix = maalix;
        this.maaliy = maaliy;
    }

    /**
     * Taulukoiden ja etäisyyksien alustaminen samassa paketissa.
     */
    private void Init() {
        tarkastaArvot();
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
        keko = new MinimikekoDijkstra(keonkoko);

    }

    /**
     * Alustaa etäisyysarvioihin käytettävät matriisit
     *
     */
    private void AlustaEtaisyydet() {
        Koordinaatti koord;

        for (int y = 0; y < verkko.length; y++) {
            for (int x = 0; x < verkko[0].length; x++) {

                koord = new Koordinaatti(x, y);

                if (OllaankoStartissa(koord)) {
                    koord.setDistance(0);
                    startti = koord;
                } else {
                    koord.setDistance(max);
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
        if (crd.getX() == alkux && crd.getY() == alkuy) {
            return true;
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
        System.out.println("");
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
        if (x - 1 >= 0) {
            int vx = x - 1;
            if (verkko[y][vx] == 0) {
                RelaxMekaniikka(vx, y, x, y);
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
        if (x + 1 < verkko[0].length) {
            int vx = x + 1;
            if (verkko[y][vx] == 0) {
                RelaxMekaniikka(vx, y, x, y);
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
        if (y - 1 >= 0) {
            int vy = y - 1;
            if (verkko[vy][x] == 0) {
                RelaxMekaniikka(x, vy, x, y);
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
        if (y + 1 < verkko.length) {
            int vy = y + 1;
            if (verkko[vy][x] == 0) {
                RelaxMekaniikka(x, vy, x, y);
            }
        }
    }

    /**
     * Itse löysäysoperaatio
     *
     * @param vy Muuttuva y-arvo
     * @param vx Muuttuva x-arvo
     * @param x alkup. x-arvo
     * @param y alkup. y-arvo
     */
    void RelaxMekaniikka(int modx, int mody, int origx, int origy) {
        Koordinaatti modcrd = sailio[mody][modx];
        Koordinaatti origcrd = sailio[origy][origx];

        if (modcrd.getDistance() > origcrd.getDistance() + 1) {
            modcrd.setDistance(origcrd.getDistance() + 1);
            int uusiEtaisyys = modcrd.getDistance();
            keko.laskeArvoa(modcrd.getID(), uusiEtaisyys);

            modcrd.setPath(origcrd);
            sailio[mody][modx] = modcrd;
        }
    }

    /**
     * Itse Reitihakualgoritmi. Kaiken pitäisi toimia tästä.
     */
    public void Dijkstra() {
        Init();
        while (!keko.isEmpty()) {
            Koordinaatti solmu = keko.removeMin();
            System.out.println("removemin " + solmu);
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
        if (solmu.getX() == maalix && solmu.getY() == maaliy) {
            return true;
        }
        return false;
    }

    /**
     * Printtaa reitin koordinaatit. Jossain vaiheessa jopa piirtää ne
     * labyrinttiin.
     *
     */
    public void TulostaReitti() {
        Koordinaatti reitti = sailio[maaliy][maalix];
        System.out.println("Eka: " + reitti);

        while (reitti != null) {
            reitti = reitti.getPath();
            System.out.println(reitti);
        }
    }

    /**
     * Tarkastaa, että alku- ja lähtöpisteet eivät ole seinää
     *
     * @return true, jos kaikki kunnossa, false muulloin
     */
    boolean tarkastaArvot() {
        if (verkko[alkuy][alkux] == 0) {
            if (verkko[maaliy][maalix] == 0) {
                System.out.println("kaikki ok");
                return true;
            }
        }
        System.out.println("huonot naatit");
        return false;
    }
}
