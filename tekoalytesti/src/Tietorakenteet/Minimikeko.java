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

    private Koordinaatti[] A;
    private int[] avaintaulu;
    private int maxkoko;
    private int koko;
    private int index;

    /**
     * Konstruktori
     *
     * @param max Keon koko.
     */
    public Minimikeko(int max) {
        maxkoko = max;
        A = new Koordinaatti[maxkoko];
        avaintaulu = new int[maxkoko];
        koko = 0;
        Koordinaatti dummycrd = new Koordinaatti();
        dummycrd.setEtaisyys(-1);
        A[0] = dummycrd;
    }

    /**
     * Palauttaa solmun vasemman lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return vasemman lapsen sijainti keossa.
     */
    private int Left(int pos) {
        return 2 * pos;
    }

    /**
     * Palauttaa solmun oikean lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return Oikean lapsen sijainti keossa.
     */
    private int Right(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Palauttaa solmun vanhemman indeksin.
     *
     * @param pos Lapsisolmun indeksi.
     * @return Vanhemman sijainti keossa.
     */
    private int Parent(int pos) {
        return pos / 2;
    }

    /**
     * Vaihtaa kahden solmun paikkaa keossa.
     *
     * @param pos1 ensimmäisen solmun indeksi.
     * @param pos2 toisen solmun indeksi.
     */
    private void swap(int pos1, int pos2) {
        Koordinaatti tmp = A[pos1];
        A[pos1] = A[pos2];
        A[pos2] = tmp;

        int ktmp = avaintaulu[pos1];
        avaintaulu[pos1] = avaintaulu[pos2];
        avaintaulu[pos2] = ktmp;
    }

    /**
     * Sijoittaa kekoon uuden koordinaatin.
     *
     * @param k Lisättävä koordinaatti.
     */
    public void insert(Koordinaatti k) {
        koko++;
        int i = koko;

        while (i > 1 && A[Parent(i)].getEtaisyys() > k.getEtaisyys()) {
            A[i] = A[Parent(i)];
            i = Parent(i);
        }
        A[i] = k;
        avaintaulu[k.getID()] = i;
    }

    /**
     * Poistaa ja palauttaa keon pienimmän alkion (= koordinaatin, jonka
     * etäisyysarvio on pienin).
     *
     * @return keon pienin alkio.
     */
    public Koordinaatti removeMin() {
        Koordinaatti min = A[1];
        A[1] = A[koko];
        koko--;
        avaintaulu[A[1].getID()] = -1;
        if (koko != 0) {
            heapify(1);
        }
        return min;
    }

    /**
     * Pitää keon järjestyksessä. Kuljettaa pieniä alkioita alaspäin.
     *
     * @param i tarkasteltavan alkion sijainti keossa.
     */
    private void heapify(int i) {
        int l = Left(i);
        int r = Right(i);
        int largest;

        if (r <= koko) {
            if (A[l].getEtaisyys() > A[r].getEtaisyys()) {
                largest = l;
            } else {
                largest = r;
            }
            if (A[l].getEtaisyys() < A[largest].getEtaisyys()) {
                swap(i, largest);
                heapify(largest);
            }
        } else if (l == koko && A[i].getEtaisyys() < A[l].getEtaisyys()) {
            swap(i, l);
        }


    }

    /**
     * Laskee alkion arvoa (=koordinaatin etäisyyttä alkuun ja siten sen
     * etäisyysarviota)
     *
     * @param avain Tutkittavan alkion avain.
     * @param newk uusi etäisyysarvio alkuun.
     */
    public void laskeArvoa(int avain, int newk) {
        if (getPos(avain) == true) {
            if (newk < A[index].getAlkuun()) {
                A[index].setAlkuun(newk);
                heapify(index);
            }
        }
    }

/**
 * Etsii avainta vastaavan koordinaatin indeksin aputaulukosta.
 * @param avain etsittävän koordinaatin avain.
 * @return true, jos koordinaatti on vielä keossa ja indeksi löytyy, false muulloin.
 */
    private boolean getPos(int avain) {
        index = avaintaulu[avain];
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }
}
