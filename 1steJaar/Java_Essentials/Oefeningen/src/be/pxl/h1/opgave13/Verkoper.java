package be.pxl.h1.opgave13;

public class Verkoper {
    private String naam;
    private double[] verkoopcijfers = new double[12];

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setVerkoopMaand(int maandnummer, double verkocht){ // januari is maandnr 1
        verkoopcijfers[maandnummer - 1] = verkocht;
    }

    public double getTotaalVerkocht(){
        double som = 0;
        for (double getal : verkoopcijfers){
            som += getal;
        }
        return som;
    }

    public double getGemiddeldVerkocht(){
        return getTotaalVerkocht() / verkoopcijfers.length;
    }
}
