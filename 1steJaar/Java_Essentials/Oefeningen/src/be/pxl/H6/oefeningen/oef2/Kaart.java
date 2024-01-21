package be.pxl.H6.oefeningen.oef2;

public class Kaart {
    public Kaarten waarde;
    public Soort soort;

    public Kaart(Kaarten waarde, Soort soort){
        this.waarde = waarde;
        this.soort = soort;
    }

    public Kaarten getWaarde() {
        return waarde;
    }

    public void setWaarde(Kaarten waarde) {
        this.waarde = waarde;
    }

    public Soort getSoort() {
        return soort;
    }

    public void setSoort(Soort soort) {
        this.soort = soort;
    }
}
