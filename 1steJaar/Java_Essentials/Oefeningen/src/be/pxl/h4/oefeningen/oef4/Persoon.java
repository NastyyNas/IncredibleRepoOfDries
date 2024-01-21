package be.pxl.h4.oefeningen.oef4;

import java.time.LocalDate;

public class Persoon {
    private String naam;
    private String voornaam;
    private LocalDate geboorteDatum;
    private Adres adres;

    public Persoon(String naam, String voornaam, int dag, int maand, int jaar, String straat, String huisNummer, int postcode, String gemeenteNaam){
        this.naam = naam;
        this.voornaam = voornaam;
        this.geboorteDatum = LocalDate.of(jaar, maand, dag);
        adres = new Adres(straat, huisNummer, postcode, gemeenteNaam);
    }
    //omdraaien
    public Persoon(String naam, String voornaam, LocalDate geboorteDatum, Adres adres){
        this(naam, voornaam, geboorteDatum.getDayOfMonth(), geboorteDatum.getMonthValue(), geboorteDatum.getYear(), "","", 0, "");
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return voornaam + " " + naam + "\n" + adres;
    }
}
