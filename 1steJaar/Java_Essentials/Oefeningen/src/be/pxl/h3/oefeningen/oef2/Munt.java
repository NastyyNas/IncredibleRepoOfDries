package be.pxl.h3.oefeningen.oef2;

public class Munt {
    private String naam;
    private double koers;
    private static final int AFRONDING = 3;
    private static int aantal;

    public Munt(String naam, double koers){
        this.naam = naam;
        this.koers = koers;
        aantal++;
    }

    public Munt(){
        this("Euro", 1);
    }

    public String getNaam() {
        return naam.toUpperCase();
    }

    public double getKoers() {
        return koers;
    }

    public static int getAantal() {
        return aantal;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setKoers(double koers) {
        this.koers = koers;
    }

    public static void setAantal(int aantal) {
        Munt.aantal = aantal;
    }

    public static int getAfronding(){
        return AFRONDING;
    }
}
