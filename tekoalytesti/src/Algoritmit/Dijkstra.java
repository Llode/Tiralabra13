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
     * @param alkux
     * @param alkuy
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

        for (int y = 0; y < verkko.length; y++) {
            for (int x = 0; x < verkko[0].length; x++) {

                koord = new Koordinaatti(x, y);

                if (OllaankoStartissa(koord)) {
                    koord.setEtaisyys(0);
                    keko.insert(koord);
                } else {
                    koord.setEtaisyys(max);
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

        if (modcrd.getEtaisyys() > origcrd.getEtaisyys() + 1) {
            modcrd.setEtaisyys(origcrd.getEtaisyys() + 1);

            modcrd.setPath(origcrd);
            sailio[mody][modx] = modcrd;
            if (!sailio[mody][modx].onkoTutkittu()) {
                sailio[mody][modx].setTutkittu();
                keko.insert(sailio[mody][modx]);
            }

        }
    }

    /**
     * Itse Reitihakualgoritmi. Kaiken pitäisi toimia tästä.
     *
     * @return True, jos reitinhaku onnistuu
     */
    public boolean Dijkstra() {
        if (!tarkastaArvot()) {
            return false;

        } else {

            Init();
            Reitinhaku();
            if (TallennaReitti()) {
                TulostaReitti();
                return true;
            } else {
                return false;
            }

        }

    }

    /**
     * Tallentaa reitin koordinaatit pinoon. Jossain vaiheessa jopa piirtää ne
     * labyrinttiin.
     *
     * @return true, jos viimeinen tallennettava solmu on starttisolmu
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
     * Tulostaa haetun reitin.
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
        System.out.println("Huonot naatit");
        return false;
    }

    /**
     * Itse reitinhakualgoritmi.
     */
    void Reitinhaku() {
        while (!keko.isEmpty()) {
            Koordinaatti solmu = keko.removeMin();
            Relax(solmu);
        }
    }

    /**
     * Palauttaa pinoon tallennetun reitin. Käytetään Polun visualisointiin.
     *
     * @return Koordinaattipino sen ollessa "täysi"
     */
    public Koordinaatti[] getPolku() {
        return pinonsisalto;
    }
}
