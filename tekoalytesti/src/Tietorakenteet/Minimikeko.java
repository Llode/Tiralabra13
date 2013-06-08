/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 * Koordinaateille spesialisoitu minimikeko, yritys dos.
 *
 * @author Larppa
 */
public class Minimikeko {

    private Koordinaatti[] keko;
    private int maxkoko;
    private int koko;

    /**
     * Konstruktori
     *
     * @param max Keon koko.
     */
    public Minimikeko(int max) {
        maxkoko = max;
        keko = new Koordinaatti[maxkoko];
        koko = 0;

        Koordinaatti dummycrd = new Koordinaatti();
        dummycrd.setEtaisyys(-1);
        keko[0] = dummycrd;
    }

    /**
     * Palauttaa solmun vasemman lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return vasemman lapsen sijainti keossa.
     */
    public int getLeft(int pos) {
        return 2 * pos;
    }

    /**
     * Palauttaa solmun oikean lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return Oikean lapsen sijainti keossa.
     */
    public int getRight(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Palauttaa solmun vanhemman indeksin.
     *
     * @param pos Lapsisolmun indeksi.
     * @return Vanhemman sijainti keossa.
     */
    public int getParent(int pos) {
        return pos / 2;
    }

    /**
     * Vaihtaa kahden solmun paikkaa keossa.
     *
     * @param pos1 ensimmäisen solmun indeksi.
     * @param pos2 toisen solmun indeksi.
     */
    void swap(int pos1, int pos2) {
        Koordinaatti tmp = keko[pos1];
        keko[pos1] = keko[pos2];
        keko[pos2] = tmp;
    }

    /**
     * Sijoittaa kekoon uuden koordinaatin.
     *
     * @param koord Lisättävä koordinaatti.
     */
    public void insert(Koordinaatti koord) {
        koko++;
        int index = koko;

        while (index > 1 && keko[getParent(index)].getEtaisyys() > koord.getEtaisyys()) {
            keko[index] = keko[getParent(index)];
            index = getParent(index);
        }
        keko[index] = koord;
    }

    /**
     * Poistaa ja palauttaa keon pienimmän alkion (= koordinaatin, jonka
     * etäisyysarvio on pienin).
     *
     * @return keon pienin alkio.
     */
    public Koordinaatti removeMin() {
        if (!isEmpty()) {
            Koordinaatti min = keko[1];
            keko[1] = keko[koko];

            keko[koko] = null;
            koko--;
            heapify(1);

            return min;
        }
        return null;
    }

    /**
     * Pitää keon järjestyksessä. Kuljettaa pieniä alkioita alaspäin.
     *
     * @param index tarkasteltavan alkion sijainti keossa.
     */
    void heapify(int index) {
        int left = getLeft(index);
        int right = getRight(index);
        int smallest;

        if (right <= koko) {
            if (keko[left].getEtaisyys() < keko[right].getEtaisyys()) {
                smallest = left;
            } else {
                smallest = right;
            }
            if (keko[index].getEtaisyys() > keko[smallest].getEtaisyys()) {
                swap(index, smallest);
                heapify(smallest);
            }
        } else if (left == koko && keko[index].getEtaisyys() > keko[left].getEtaisyys()) {
            swap(index, left);
        }


    }

    /**
     * Laskee alkion arvoa (=koordinaatin etäisyyttä alkuun ja siten sen
     * etäisyysarviota) (Ei tarvita enää)
     *
     * @param ID Tutkittavan alkion avain.
     * @param newk uusi etäisyysarvio alkuun.
     * @return
     */
    public boolean laskeArvoa(int ID, int newk) {
        int index = etsiAlkioKeostaLoopilla(ID);

        if (index == -1) {
            return false;
        } else {
            keko[index].setAlkuun(newk);

            while (index > 1 && keko[getParent(index)].getAlkuun() > keko[index].getAlkuun()) {
                swap(index, getParent(index));
                index = getParent(index);
            }
            return true;
        }
    }

    /**
     * Tarkastaa, onko keko tyhjä. Mikäli keossa on vain 'tyhjä koordinaatti',
     * se palauttaa true.
     *
     * @return
     */
    public boolean isEmpty() {
        if (koko < 1) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa keon listana. Testejä varten.
     *
     * @return Koordinaatti[] keon sisällöstä
     */
    public Koordinaatti[] getArray() {
        return keko;
    }

    int etsiAlkioKeostaLoopilla(int ID) {
        int etsin;
        int i = 0;
        while (i < koko) {
            i++;
            etsin = keko[i].getID();
            if (etsin == ID) {
                return i;
            }
        }
        return -1;
    }
}
