package be.pxl.h5.oefeningen.oef1;

public class Persoon {
    private String voornaam;
    private String naam;

    public Persoon(String voornaam, String naam){
        this.voornaam = voornaam;
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void print(){
        System.out.println(voornaam + " " + naam);
    }
}
