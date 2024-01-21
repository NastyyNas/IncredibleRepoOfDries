package be.pxl.h1.opgave8;

public class Planeet {
    private String naam;
    private int diameter; // in km
    private long afstandZon;

    public String getNaam() {
        return naam;
    }

    public int getDiameter() {
        return diameter;
    }

    public long getAfstandZon() {
        return afstandZon;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setAfstandZon(long afstandZon) {
        this.afstandZon = afstandZon;
    }

    public double getAfstandAE(){
        return afstandZon / 149600000.0;
    }
}
