package be.pxl.h8.oefeningen.oef1;

public abstract class Bestand implements Uitvoerbaar {
    private String naam;
    private String inhoud;
    private int bestandsGrootte;
    public Bestand(String naam, int bestandsGrootte){
        this(naam);
        this.bestandsGrootte = bestandsGrootte;
    }

    public Bestand(String naam){
        this.naam = naam;
    }

    public int getBestandsGrootte() {
        return bestandsGrootte;
    }

    public String getNaam() {
        return naam;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public void setBestandsGrootte(int bestandsGrootte) {
        this.bestandsGrootte = bestandsGrootte;
    }

    @Override
    public String toString() {
        return naam + "\t" + bestandsGrootte + " bytes";
    }
}
