/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ghostai;

/**
 * Koordinaattien säilytystä mm. lyhimmän reitin tallentamista/tulostamista/piirtämistä varten.
 * @author Larppa
 */
public class Koordinaatti {

    int x;
    int y;
    int heurestiikka;

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
        heurestiikka = alkuun + loppuun;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getEtaisyys(){
        return heurestiikka;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

