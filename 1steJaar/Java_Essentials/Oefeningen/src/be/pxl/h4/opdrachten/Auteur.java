package be.pxl.h4.opdrachten;

public class Auteur {
    private String naam;
    private String voornaam;

    public Auteur(String naam, String voornaam){
        this.naam = naam;
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String toString(){
        return "Auteur: " + voornaam + " " + naam;
    }
}
