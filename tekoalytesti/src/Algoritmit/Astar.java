/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Koordinaatti;
import Tietorakenteet.Koordinaattipino;
import Tietorakenteet.Minimikeko;

/**
 * Reitinhakualgoritmi
 *
 * @author Larppa
 */
public class Astar {

    /**
     * Teoreettinen maksimiarvo, joka annetaan etaisyysAlkuun-taulukon alkioille
     * alussa. Maksimietäisyys (labyrintin korkeus + leveys) on vähennetty,
     * ettei etaisyysAlkuun[y][x]+etaisyysLoppuun[y][x] aiheuttaisi virhettä.
     */
    private final int max = Integer.MAX_VALUE - 1000;
    /**
     * Säilöö koordinaatit tietoinen omille paikoilleen matriisiin.
     */
    Koordinaatti[][] sailio;
    /**
     * minimikeon korvike reitinhakua varten.
     */
    Minimikeko keko;
    private int alkux;
    private int alkuy;
    private int maalix;
    private int maaliy;
    private int[][] verkko;
    private Koordinaattipino pino = new Koordinaattipino(10);
    private Koordinaatti[] pinonsisalto;

    /**
     * Konstruktori
     *
     * @param labyrintti tutkittava verkko
     * @param alkux aloituskoordinaatti
     * @param alkuy aloituskoordinaatti
     * @param maalix maalikoordinaatti
     * @param maaliy lottoapa
     */
    public Astar(int[][] labyrintti, int alkux, int alkuy, int maalix, int maaliy) {
        this.verkko = labyrintti;
        this.alkux = alkux;
        this.alkuy = alkuy;
        this.maalix = maalix;
        this.maaliy = maaliy;
    }

    /**
     * Taulukoiden ja etäisyyksien alustaminen samassa paketissa.
     */
    void Init() {
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
                    keko.insert(koord);
                } else {
                    koord.setEtaisyys(max, loppuun);
                }

                sailio[y][x] = koord;

            }
        }
    }

    /**
     * True, jos tutkittava koordinaatti on aloituskoordinaatti
     *
     * @param crd Tutkittava koordinaatti.
     * @return true, jos tutkittava solmu on aloitussolmu
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
     * @param x relaxoitavan solmun koordinaatit
     * @param y relaxoitavan solmun koordinaatit
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
     * @param x relaxoitavan solmun koordinaatit
     * @param y relaxoitavan solmun koordinaatit
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
     * @param x relaxoitavan solmun koordinaatit
     * @param y relaxoitavan solmun koordinaatit
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
     * @param x relaxoitavan solmun koordinaatit
     * @param y relaxoitavan solmun koordinaatit
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

        if (sailio[mody][modx].getAlkuun() > sailio[origy][origx].getAlkuun() + 1) {
            sailio[mody][modx].setAlkuun(sailio[origy][origx].getAlkuun() + 1);

            sailio[mody][modx].setPath(sailio[origy][origx]);


            keko.insert(sailio[mody][modx]);


        }
    }

    /**
     * Kasaa kaikki metodit yhteen. Kaiken pitäisi toimia tästä.
     *
     * @return true, jos törmätään maalisolmuun (=reitinhaku onnistui)
     */
    public boolean Astar() {
        if (!tarkastaArvot()) {
            return false;

        } else {

            Init();

            if (Reitinhaku()) {
                TallennaReitti();
                TulostaReitti();
                return true;
            } else {
                System.out.println("Reitinhaku epäonnistui.");
                return false;
            }
        }
    }

    /**
     * itse reitinhakualgoritmi
     *
     * @return true, jos maalisolmu käsitellään.
     */
    boolean Reitinhaku() {
        while (!keko.isEmpty()) {
            Koordinaatti solmu = keko.removeMin();
            Relax(solmu);

            if (OllaankoMaalissa(solmu)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tutkii, onko solmu maalisolmu.
     *
     * @param solmu tutkittava solmu
     * @return True, jos tutkittava solmu on maalisolmu.
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
     * Tallentaa reitin koordinaatit pinoon. Jossain vaiheessa jopa piirtää ne
     * labyrinttiin.
     *
     * @return true, jos viimeinen tulostettava solmu on starttisolmu
     * (=reitinhaku onnistui)
     */
    public boolean TallennaReitti() {
        Koordinaatti reitti = sailio[maaliy][maalix];
        pino.push(reitti);
        int pituus = 0;

        while (reitti.getPath() != null) {
            reitti = reitti.getPath();
            pino.push(reitti);
            pituus++;
        }

        pinonsisalto = pino.getArray();

        if (OllaankoStartissa(reitti)) {
            System.out.println("Reitin pituus: " + pituus);
            return true;
        } else {
            System.out.println("Reittiä ei löytynyt");
            return false;
        }
    }

    /**
     * Palauttaa pinoon tallennetun reitin. Käytetään Polun visualisointiin.
     * @return Koordinaattipino sen ollessa "täysi"
     */
    public Koordinaatti[] getPolku(){
        return pinonsisalto;
    }
    /**
     * Tulostaa pinoon tallennetun reitin.
     */
    public void TulostaReitti() {
        while (!pino.isEmpty()) {
            System.out.println(pino.pop());
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

    /**
     * Palauttaa maksimiarvon (MAX_VALUE - n) testejä varten
     *
     * @return Maksimiarvo
     */
    public int getMax() {
        return max;
    }

    /**
     * Tulostaa keon sisällön pienuusjärjestyksessä. Debugausta varten.
     */
    void tulostaKeonSisalto() {
        Koordinaatti crd = keko.removeMin();
        while (!keko.isEmpty()) {
            System.out.println(crd);
            crd = keko.removeMin();
        }
    }

    /**
     * Palauttaa taulukon, johon koordinaatit tallennetaan. Käytetään
     * AlustaEtaisyydet()-metodin testaukseen.
     *
     * @return sailio
     */
    Koordinaatti[][] getSailio() {
        return sailio;
    }
}
