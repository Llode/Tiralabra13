/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 * Pino-tietorakenne lyhimmän reitin tallentamista varten.
 *
 * @author Larppa
 */
public class Koordinaattipino {

    private Koordinaatti[] taulu;
    private int top;

    /**
     * Luo uuden pinon.
     * @param koko pinon haluttu koko.
     */
    public Koordinaattipino(int koko) {
        top = -1;
        taulu = new Koordinaatti[koko];
    }

    /**
     * Poistaa ja palauttaa pinon 'päällimmäisen' alkion.
     * @return Viimeksi lisätty alkio.
     */
    public Koordinaatti pop() {
        Koordinaatti crd = taulu[top];
        top--;
        return crd;
    }

    /**
     * Sijoittaa alkion pinon päälle.
     * @param crd Lisättävä koordinaatti.
     */
    public void push(Koordinaatti crd) {
        top++;
        if (top == taulu.length) {
            tuplaaTaulukko();
        }
        taulu[top] = crd;
    }

    /**
     * Testaa, onko pino tyhjä.
     * @return true, jos top < 0, false muulloin
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Tuplaa taulukon koon, mikäli se täyttyy.
     */
    private void tuplaaTaulukko() {
        Koordinaatti[] aputaulu = new Koordinaatti[taulu.length * 2];

        for (int i = 0; i < taulu.length; i++) {
            aputaulu[i] = taulu[i];
        }
        taulu = aputaulu;
    }
    
    /**
     * Palauttaa pinon taulukkona. Testejä varten.
     * @return taulu.
     */
    public Koordinaatti[] getArray() {
        return taulu;
    }
    /**
     * Palauttaa pinossa olevien alkioiden määrän.
     * @return alkioiden määrä
     */
    int getSize(){
        return top+1;
    }
}
