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
    }

    /**
     * Palauttaa solmun vasemman lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return vasemman lapsen sijainti keossa.
     */
    public int Left(int pos) {
        return 2 * pos;
    }

    /**
     * Palauttaa solmun oikean lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return Oikean lapsen sijainti keossa.
     */
    public int Right(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Palauttaa solmun vanhemman indeksin.
     *
     * @param pos Lapsisolmun indeksi.
     * @return Vanhemman sijainti keossa.
     */
    public int Parent(int pos) {
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

        while (index > 1 && keko[Parent(index)].getEtaisyys() > koord.getEtaisyys()) {
            keko[index] = keko[Parent(index)];
            index = Parent(index);
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
        int left = Left(index);
        int right = Right(index);
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
     * Rakentaa taulukosta keon. Käytetään testaukseen.
     */
    void buildHeap() {
        for (int i = koko/2 +1; i > 0; i--) {
            heapify(i);
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

            while (index > 1 && keko[Parent(index)].getAlkuun() > keko[index].getAlkuun()) {
                swap(index, Parent(index));
                index = Parent(index);
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

    /**
     * Antaa keolle uuden listan. Testejä varten.
     *
     * @param array keon uusi lista
     */
    public void introduceArray(Koordinaatti[] array) {
        this.keko = array;
        this.koko = array.length - 1;
    }

    /**
     * Etsii haettavan alkion keosta syklillä. Ei ole nopein mahdollinen tapa,
     * mutta toisaalta tätä metodia ei työssä tarvita.
     * @param ID heittavan solmun tunniste
     * @return solmun indeksi keossa, jos solmu ei enää ole keossa, palautetaan -1.
     */
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

    /**
     * Palauttaa keon koon. Testejä varten.
     *
     * @return Keon tämänhetkinen koko.
     */
    public int getKoko() {
        return koko;
    }
}
