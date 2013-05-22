/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.Comparator;

/**
 * Koordinaattien säilytystä mm. lyhimmän reitin
 * tallentamista/tulostamista/piirtämistä varten.
 * Getterit ja setterit ovat suurelta osin itsestäänselviä,
 * ja tuskin tarvitsevat dokumentointia.
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
    private boolean onkoTutkittu;

    /**
     *
     */
    public Koordinaatti() {
        int x = this.x;
        int y = this.y;
        int etaisyysarvio = this.etaisyysarvio;
        int alkuun = this.alkuun;
        int loppuun = this.loppuun;
        int id = this.id;
        boolean onkoTutkittu = false;
        Koordinaatti path = this.path;
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
//        int etaisyysarvio = this.etaisyysarvio;
//        int alkuun = this.alkuun;
//        int loppuun = this.loppuun;
//        Koordinaatti path = this.path; 
   }
    /**
     * Toivottavasti antaa jokaiselle koordinaatille oman avaimen.
     */
    private int numeroJuoksee(){
        avain++;
        return avain;
    }
    /**
     *
     * @return
     */
    public int getID(){
        return id;
    }
/**
 * Path osoittaa aina edeltävään solmuun.
 * @param solmu Solmu, joka johtaa tähän solmuun. 
 */
    public void setPath(Koordinaatti solmu) {
        path = solmu;
    }
    public boolean onkoTutkittu(){
        return onkoTutkittu;
    }
    public void tutkittu(){
        onkoTutkittu = true;
    }
    /**
     *
     * @return
     */
    public Koordinaatti getPath(){
        return path;
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
    public void setEtaisyys(int etaisyys){
        this.etaisyysarvio = etaisyys;
    }
    /**
     *
     * @return
     */
    public int getEtaisyys() {
        return etaisyysarvio;
    }

    /**
     *
     */
    public void laskeEtaisyys() {
        etaisyysarvio = alkuun + loppuun;
    }

    /**
     *
     * @param alkuun
     */
    public void setAlkuun(int alkuun) {
        this.alkuun = alkuun;
        laskeEtaisyys();
    }

    /**
     *
     * @param loppuun
     */
    public void setLoppuun(int loppuun) {
        this.loppuun = loppuun;
        laskeEtaisyys();
    }

    /**
     *
     * @return
     */
    public int getAlkuun() {
        return alkuun;
    }

    /**
     *
     * @return
     */
    public int getLoppuun() {
        return loppuun;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setKoordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
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
