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

    Koordinaatti[] keko;
    /**
     * Avaintaulun indeksi on koordinaatin ID ja alkio sen paikka itse keossa.
     */
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
        keko = new Koordinaatti[maxkoko];
        avaintaulu = new int[maxkoko];
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

        int ktmp = avaintaulu[pos1];
        avaintaulu[pos1] = avaintaulu[pos2];
        avaintaulu[pos2] = ktmp;
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

        avaintaulu[koord.getID()] = index;
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

            avaintaulu[min.getID()] = -1;
            avaintaulu[keko[1].getID()] = 1;

            koko--;
            heapify(1);
            return min;
        }
        return keko[0];
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
     * Nostaa alkion arvoa (=koordinaatin etäisyyttä alkuun ja siten sen
     * etäisyysarviota)
     *
     * @param avain Tutkittavan alkion avain.
     * @param newk uusi etäisyysarvio alkuun.
     */
    public boolean nostaArvoa(int avain, int newk) {
        if (getPos(avain) == true) {
            if (newk > keko[index].getAlkuun()) {
                keko[index].setAlkuun(newk);
                heapify(index);
                return true;
            }
        }
        return false;
    }

    /**
     * Laskee alkion arvoa (=koordinaatin etäisyyttä alkuun ja siten sen
     * etäisyysarviota)
     *
     * @param avain Tutkittavan alkion avain.
     * @param newk uusi etäisyysarvio alkuun.
     */
    public boolean laskeArvoa(int avain, int newk) {
        if (getPos(avain) == true) {
            int adist = keko[index].getEtaisyys();
            keko[index].setAlkuun(newk);
            System.out.println("ARvonlasku " + keko[index] + "   " + adist);
            while (index > 1 && keko[getParent(index)].getAlkuun() > keko[index].getAlkuun()) {
                swap(index, getParent(index));
                index = getParent(index);
            }

            System.out.println("Arvonslasku " + keko[index].getEtaisyys());
            return true;

        }
        return false;
    }

    /**
     * Etsii avainta vastaavan koordinaatin indeksin aputaulukosta.
     *
     * @param avain etsittävän koordinaatin avain.
     * @return true, jos koordinaatti on vielä keossa ja indeksi löytyy, false
     * muulloin.
     */
    public boolean getPos(int avain) {
        index = avaintaulu[avain];
        if (index == -1) {
            return false;
        } else {
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
     * getPosin testausta varten.
     *
     * @return
     */
    public int getIndex() {
        return index;
    }
}
