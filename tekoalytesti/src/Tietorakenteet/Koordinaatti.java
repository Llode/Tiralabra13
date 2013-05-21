/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 * Koordinaattien säilytystä mm. lyhimmän reitin
 * tallentamista/tulostamista/piirtämistä varten.
 * Getterit ja setterit ovat suurelta osin itsestäänselviä,
 * ja tuskin tarvitsevat dokumentointia.
 *
 * @author Larppa
 */
public class Koordinaatti implements Comparable<Koordinaatti> {

    private int x;
    private int y;
    private int etaisyysarvio;
    private int alkuun;
    private int loppuun;
    private Koordinaatti path;

    public Koordinaatti() {
        int x = this.x;
        int y = this.y;
        int etaisyysarvio = this.etaisyysarvio;
        int alkuun = this.alkuun;
        int loppuun = this.loppuun;
        Koordinaatti path = this.path;
    }

    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
        int etaisyysarvio = this.etaisyysarvio;
        int alkuun = this.alkuun;
        int loppuun = this.loppuun;
        Koordinaatti path = this.path;
    }
/**
 * Path osoittaa aina edeltävään solmuun.
 * @param solmu Solmu, joka johtaa tähän solmuun. 
 */
    public void setPath(Koordinaatti solmu) {
        path = solmu;
    }
    
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

    public int getEtaisyys() {
        return etaisyysarvio;
    }

    public void laskeEtaisyys() {
        etaisyysarvio = alkuun + loppuun;
    }

    public void setAlkuun(int alkuun) {
        this.alkuun = alkuun;
        laskeEtaisyys();
    }

    public void setLoppuun(int loppuun) {
        this.loppuun = loppuun;
        laskeEtaisyys();
    }

    public int getAlkuun() {
        return alkuun;
    }

    public int getLoppuun() {
        return loppuun;
    }

    public void setKoordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Koordinaatti o) {
        return this.etaisyysarvio - o.etaisyysarvio;
    }
}
