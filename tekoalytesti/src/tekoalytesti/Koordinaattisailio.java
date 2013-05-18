/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoalytesti;

/**
 *
 * @author Larppa
 */
public class Koordinaattisailio {
    private Koordinaatti crd;
    private Koordinaatti[] sailio;
    private Koordinaatti pienin;
    private int index = 0;
    
    public Koordinaattisailio(){
        Koordinaatti crd = this.crd;
        Koordinaatti[] sailio = this.sailio;
        Koordinaatti pienin = this.pienin;
    }
    
    public void uusiSailio(int koko) {
        sailio = new Koordinaatti[koko];
        
    }
    public void sailioInsert(Koordinaatti crd) {
        sailio[index] = crd;
        if(crd.getEtaisyys() < pienin.getEtaisyys()) {
            pienin = crd;
        }
    }
}
