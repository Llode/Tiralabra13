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
public class MinimikekoDijkstra {

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
    public MinimikekoDijkstra(int max) {
        maxkoko = max;
        keko = new Koordinaatti[maxkoko];
        avaintaulu = new int[maxkoko];
        koko = 0;
        Koordinaatti dummycrd = new Koordinaatti();
        dummycrd.setDistance(-1);
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
//        int avain = avaintaulu[tmp.getID()];

//        avaintaulu[keko[pos1].getID()] = avaintaulu[keko[pos2].getID()];
//        avaintaulu[keko[pos2].getID()] = avain;

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

        while (index > 1 && keko[getParent(index)].getDistance() > koord.getDistance()) {
            keko[index] = keko[getParent(index)];
            index = getParent(index);
        }
        keko[index] = koord;

//        avaintaulu[koord.getID()] = index;
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

//            avaintaulu[min.getID()] = -1;
//            avaintaulu[keko[koko].getID()] = 1;

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
            if (keko[left].getDistance() < keko[right].getDistance()) {
                smallest = left;
            } else {
                smallest = right;
            }
            if (keko[index].getDistance() > keko[smallest].getDistance()) {
                swap(index, smallest);
                heapify(smallest);
            }
        } else if (left == koko && keko[index].getDistance() > keko[left].getDistance()) {
            swap(index, left);
        }


    }

    /**
     * Nostaa alkion arvoa (=koordinaatin etäisyyttä loppuun ja siten sen
     * etäisyysarviota)
     *
     * @param avain 
     * @param newk 
     * @return  
     */
    public boolean nostaArvoa(int avain, int newk) {
        if (getPos(avain) == true) {
            if (newk > keko[index].getDistance()) {
                keko[index].setDistance(newk);
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
     * @param ID Tutkittavan alkion avain.
     * @param newk uusi etäisyysarvio alkuun.
     * @return  
     */
    public boolean laskeArvoa(int ID, int newk) {
//        if (getPos(ID) == true) {
        int etsin = 0;
        int i = 0;
        while (ID != etsin && i < koko) {
            i++;
            etsin = keko[i].getID();
        }
        index = i;
        

        keko[index].setDistance(newk);
//        System.out.println("avaintaulu " + avaintaulu[keko[index].getID()]);
//        System.out.println("ARvonlasku " + keko[index] + "   " + odist);

        while (index > 1 && keko[getParent(index)].getDistance() > keko[index].getDistance()) {
            swap(index, getParent(index));
            index = getParent(index);
        }

//        System.out.println("Arvonslasku " + keko[index].getEtaisyys());
        return true;

//        }
//        return false;
    }

    /**
     * Etsii avainta vastaavan koordinaatin indeksin aputaulukosta.
     *
     * @param ID etsittävän koordinaatin avain.
     * @return true, jos koordinaatti on vielä keossa ja indeksi löytyy, false
     * muulloin.
     */
    public boolean getPos(int ID) {
//        index = avaintaulu[ID];
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
