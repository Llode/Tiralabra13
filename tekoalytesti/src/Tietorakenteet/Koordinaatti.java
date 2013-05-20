/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 * Koordinaattien säilytystä mm. lyhimmän reitin
 * tallentamista/tulostamista/piirtämistä varten.
 *
 * @author Larppa
 */
public class Koordinaatti implements Comparable<Koordinaatti> {

    private int x;
    private int y;
    private int etaisyysarvio;
    private int alkuun;
    private int loppuun;

    public Koordinaatti() {
        int x = this.x;
        int y = this.y;
        int etaisyysarvio = this.etaisyysarvio;
        int alkuun = this.alkuun;
        int loppuun = this.loppuun;
    }

    public void setAlkuun(int alkuun) {
        this.alkuun = alkuun;
    }

    public void setLoppuun(int loppuun) {
        this.loppuun = loppuun;
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

    /**
     *
     * @param alkuun arvioitu etäisyys solmusta lähtösolmuun
     * @param loppuun arvioitu etäisyys solmusta maalisolmuun
     */
    public void setEtaisyys(int alkuun, int loppuun) {
        etaisyysarvio = alkuun + loppuun;
    }

    public void laskeEtaisyys(){
        etaisyysarvio = alkuun + loppuun;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEtaisyys() {
        return etaisyysarvio;
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
