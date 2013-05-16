package ghostai;

/**
 * Main-class, sisältää juttuja.
 * @author Larppa
 */
public class GhostAI {

    public static int max = Integer.MAX_VALUE;
    /**
     * apumatriisi etäisyysarvioita varten
     */
    public static int[][] alkuun;
    /**
     * apumatriisi etäisyysarvioita varten
     */
    public static int[][] loppuun;
    /**
     * Lyhin polku startista maaliin.
     */
    public static Koordinaatti[][] path;

    /**
     * Tekee oikeankokoiset apumatriisit käytettävän labyrintin pohjalta.
     *
     * @param verkko Labyrintti, jossa reittiä etsitään.
     */
    static void alustaTaulukot(int[][] verkko) {
        alkuun = new int[verkko.length][verkko[0].length];
        loppuun = new int[verkko.length][verkko[0].length];
        path = new Koordinaatti[verkko.length][verkko[0].length];
    }
//    /**
//     * Laskee kahden pisteen suorat etäisyydet labyrintissä.
//     *
//     * @param alkux aloituskoordinaatit
//     * @param alkuy aloituskoordinaatit
//     * @param loppux maalin koordinaatit
//     * @param loppuy maalin koordinaatit
//     * @return Palauttaa Manhattan-etäisyyden
//     */
//    static int laskeEtaisyys(int alkux, int alkuy, int loppux, int loppuy) {
//        int etaisyysx = loppux - alkux;
//        int etaisyysy = loppuy - alkuy;
//
//        if (etaisyysx < 0) {
//            etaisyysx = etaisyysx*-1;
//        }
//        if (etaisyysy < 0) {
//            etaisyysy = etaisyysy*-1;
//        }
//        return etaisyysx + etaisyysy;
//    }

    /**
     * Alustaa etäisyysarvioihin käytettävät matriisit
     *
     * @param loppux maalin koordinaatit
     * @param loppuy maalin koordinaatit
     * @param alkux aloituskoordinaatit
     * @param alkuy aloituskoordinaatit
     */
    static void alustaEtaisyydet(int loppux, int loppuy, int alkux, int alkuy) {
        for (int i = 0; i < alkuun.length; i++) {
            for (int j = 0; j < alkuun[0].length; j++) {
                alkuun[i][j] = max;
            }
        }

        for (int i = 0; i < loppuun.length; i++) {
            for (int j = 0; j < loppuun[0].length; j++) {
//                loppuun[i][j] = laskeEtaisyys(j, i, loppux, loppuy);
                loppuun[i][j] = Math.abs(loppuy - j) + Math.abs(loppux - i);
            }
        }
        alkuun[alkuy][alkux] = 0;
    }

    /**
     * Kaarien löysäysoperaatio. Labyrintissa liikutaan vain pääilmansuuntiin,
     * joten käsiteltävällä solmulla on vain neljä vierussolmua.
     *
     * @param alkuun etäisyysarviot
     * @param x tutkittavan solmun koordinaatit
     * @param y tutkittavan solmun koordinaatit
     */
    static void relax(int[][] verkko, int[][] alkuun, int x, int y) {
        int vx, vy;
        Koordinaatti crd = new Koordinaatti();

        if (x - 1 >= 0) {
            vx = x - 1;
            if (verkko[y][vx] == 0) {
                if (alkuun[y][vx] > alkuun[y][x] + 1) {
                    alkuun[y][vx] = alkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[y][vx] = crd;
                }
            }
        }
        if (x + 1 < alkuun[0].length) {
            vx = x + 1;
            if (verkko[y][vx] == 0) {
                if (alkuun[y][vx] > alkuun[y][x] + 1) {
                    alkuun[y][vx] = alkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[y][vx] = crd;
                }
            }
        }
        if (y - 1 >= 0) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (alkuun[vy][x] > alkuun[y][x] + 1) {
                    alkuun[vy][x] = alkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[vy][x] = crd;
                }
            }
        }
        if (y + 1 < alkuun.length) {
            vy = y - 1;
            if (verkko[vy][x] == 0) {
                if (alkuun[vy][x] > alkuun[y][x] + 1) {
                    alkuun[vy][x] = alkuun[y][x] + 1;
                    crd.setKoordinaatit(x, y);
                    path[vy][x] = crd;
                }
            }
        }
    }

    /**
     * Reitihakualgoritmi
     *
     * @param verkko Toisin sanoen labyrintti.
     * @param ax aloituskoordinaatit
     * @param ay aloituskoordinaatit
     * @param mx maalikoordinaatit
     * @param my maalikoordinaatit
     */
    static void Astar(int[][] verkko, int ax, int ay, int mx, int my) {
        alustaEtaisyydet(mx, my, ax, ay);

    }
    /**
     * (Toistaiseksi) käytettävä labyrintti. Koko 21x19, 1=seinää 0=lattiaa.
     */
    public static int[][] labyrintti = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static void main(String[] args) {
        // TODO code application logic here
    }
}
