/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 * Koordinaateille spesialisoitu minimikeko, yritys uno.
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
        dummycrd.setEtaisyys(Integer.MIN_VALUE, Integer.MIN_VALUE);
        keko[0] = dummycrd;
    }

    /**
     * Palauttaa solmun vasemman lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return vasemman lapsen sijainti keossa.
     */
    private int getVasenLapsi(int pos) {
        return 2 * pos;
    }

    /**
     * Palauttaa solmun oikean lapsen indeksin.
     *
     * @param pos tutkittavan solmun indeksi.
     * @return Oikean lapsen sijainti keossa.
     */
    private int getOikeaLapsi(int pos) {
        return 2 * pos + 1;
    }

    /**
     * Palauttaa solmun vanhemman indeksin.
     *
     * @param pos Lapsisolmun indeksi.
     * @return Vanhemman sijainti keossa.
     */
    private int getParent(int pos) {
        return pos / 2;
    }

    /**
     * Vaihtaa kahden solmun paikkaa keossa.
     *
     * @param pos1 ensimmäisen solmun indeksi.
     * @param pos2 toisen solmun indeksi.
     */
    private void swap(int pos1, int pos2) {
        Koordinaatti tmp = keko[pos1];
        keko[pos1] = keko[pos2];
        keko[pos2] = tmp;
    }

    /**
     * Sijoittaa kekoon uuden koordinaatin.
     *
     * @param juttu Lisättävä koordinaatti.
     */
    public void insert(Koordinaatti juttu) {
        koko++;
        keko[koko] = juttu;
        int index = koko;

        while (keko[index].getEtaisyys() < keko[getParent(index)].getEtaisyys()) {
            swap(index, getParent(index));
            index = getParent(index);
        }
    }

    /**
     * Poistaa ja palauttaa keon pienimmän alkion (= koordinaatin, jonka
     * etäisyysarvio on pienin).
     *
     * @return keon pienin alkio.
     */
    public Koordinaatti removeMin() {
        swap(1, koko);
        koko--;
        if (koko != 0) {
            heapify(1);
        }
        return keko[koko + 1];
    }

    /**
     * Pitää keon järjestyksessä. Kuljettaa pieniä alkioita alaspäin.
     *
     * @param pos tarkasteltavan alkion sijainti keossa.
     */
    private void heapify(int pos) {
        int pieninlapsi;
        while (!onkoLehti(pos)) {
            pieninlapsi = getVasenLapsi(pos);
            if (pieninlapsi < koko) {
                if (keko[pieninlapsi].getEtaisyys() > keko[pieninlapsi + 1].getEtaisyys()) {
                    pieninlapsi++;
                }
            }
            if (keko[pos].getEtaisyys() <= keko[pieninlapsi].getEtaisyys()) {
                return;
            }
            swap(pos, pieninlapsi);
            pos = pieninlapsi;
        }

    }

    /**
     * Tarkastaa, onko solmulla vanhempaa.
     *
     * @param pos tarkasteltavan solmun sijainti keossa.
     * @return true, jos tarkasteltava alkio on lehti, false muulloin.
     */
    private boolean onkoLehti(int pos) {
        return ((pos > koko / 2) && (pos <= koko));
    }

    /**
     * Laskee alkion arvoa (=koordinaatin etäisyyttä alkuun ja siten sen
     * etäisyysarviota)
     *
     * @param pos tutkittavan alkion sijainti keossa.
     * @param uusiArvo uusi etäisyysarvio alkuun.
     */
    public void laskeArvoa(int pos, int uusiArvo) {
        if (uusiArvo < keko[pos].getAlkuun()) {
            keko[pos].setAlkuun(uusiArvo);
            while (pos > 1 && keko[getParent(pos)].getEtaisyys() < keko[pos].getEtaisyys()) {
                swap(pos, getParent(pos));
                pos = getParent(pos);
            }
        }
    }
}
