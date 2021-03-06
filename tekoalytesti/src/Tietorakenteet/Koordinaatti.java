/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 * Koordinaattien säilytystä mm. lyhimmän reitin
 * tallentamista/tulostamista/piirtämistä varten. Getterit ja setterit ovat
 * suurelta osin itsestäänselviä, ja tuskin tarvitsevat dokumentointia.
 *
 * @author Larppa
 */
public class Koordinaatti {

    private static int avain = 0;
    private int id;
    private int x;
    private int y;
    private int etaisyysarvio;
    private int alkuun;
    private int loppuun;
    private Koordinaatti path;
    private boolean tutkittu;

    /**
     *
     */
    public Koordinaatti() {
        int id = this.id;
    }

    /**
     *
     * @param x Uuden koordinaatin koordinaatit
     * @param y Uuden koordinaatin koordinaatit
     */
    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
        this.id = numeroJuoksee();
        tutkittu = false;
    }

    /**
     * Toivottavasti antaa jokaiselle koordinaatille oman avaimen.
     */
    private int numeroJuoksee() {
        avain++;
        return avain;
    }

    /**
     * Nollaa ID-laskurin, testaamista varten.
     */
    static void nollaaIDLaskuri() {
        avain = 0;
    }

    /**
     * Palauttaa koordinaatin avaimen.
     *
     * @return
     */
    public int getID() {
        return id;
    }

    /**
     * Asettaa Koordinaatille halutun IDn. Debugausta varten.
     * @param id uusi tunnus
     */
    public void setID(int id) {
        id = this.id;
    }

    /**
     * Path osoittaa aina edeltävään solmuun.
     *
     * @param solmu Solmu, joka johtaa tähän solmuun.
     */
    public void setPath(Koordinaatti solmu) {
        path = solmu;
    }

    /**
     * palauttaa solmuun johtavan solmun.
     *
     * @return
     */
    public Koordinaatti getPath() {
        return path;
    }
/**
 * Muuttaa solmun tutkituksi.
 */
    public void setTutkittu() {
        tutkittu = true;
    }
/**
 * Tarkastaa, onko solmu lisätty jo kekoon
 * @return true, jos on, false muulloin.
 */
    public boolean onkoTutkittu() {
        return tutkittu;
    }

    /**
     * Asettaa koordinaatille etäisyysarviot alku- ja maalikoordinaatteihin.
     * Laskee myös näiden pisteiden etäisyyden.
     *
     * @param alkuun arvioitu etäisyys solmusta lähtösolmuun
     * @param loppuun arvioitu etäisyys solmusta maalisolmuun
     */
    public void setEtaisyys(int alkuun, int loppuun) {
        this.alkuun = alkuun;
        this.loppuun = loppuun;
        laskeEtaisyys();
    }

    /**
     * Asettaa koordinaatille uuden etäisyyden ilman muuta härväämistä. Dijkstra
     * käyttää tätä etäisyysarvioihin.
     *
     * @param etaisyys
     */
    public void setEtaisyys(int etaisyys) {
        this.etaisyysarvio = etaisyys;
    }

    /**
     * Palauttaa etäisyysarvion
     *
     * @return
     */
    public int getEtaisyys() {
        return etaisyysarvio;
    }

    /**
     * laskee etäisyysarviot alkuun ja loppuun yhteen
     */
    public void laskeEtaisyys() {
        etaisyysarvio = alkuun + loppuun;
    }

    /**
     * asettaa uuden etäisyysarvion alkuun
     *
     * @param alkuun
     */
    public void setAlkuun(int alkuun) {
        this.alkuun = alkuun;
        laskeEtaisyys();
    }

    /**
     * asettaa uuden etäisyysarvion loppuun
     *
     * @param loppuun
     */
    public void setLoppuun(int loppuun) {
        this.loppuun = loppuun;
        laskeEtaisyys();
    }

    /**
     * Palauttaa etäisyyden alkusolmuun
     *
     * @return
     */
    public int getAlkuun() {
        return alkuun;
    }

    /**
     * Palauttaa etäisyyden loppusolmuun.
     *
     * @return
     */
    public int getLoppuun() {
        return loppuun;
    }

    /**
     * ASettaa kaikki koordinaatit!
     *
     * @param x
     * @param y
     */
    public void setKoordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ASettaa x-koordinaatin
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * asettaa y-koordinaatin
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Palauttaa x-koordinaatin
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa y-koordinaatin
     *
     * @return
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
