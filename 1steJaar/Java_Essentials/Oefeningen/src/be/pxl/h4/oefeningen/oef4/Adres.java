package be.pxl.h4.oefeningen.oef4;

public class Adres {
    private String straat;
    private String huisNummer;
    private Gemeente gemeente;

    public Adres(String straat, String huisNummer, int postcode, String gemeenteNaam){
        this.straat = straat;
        this.huisNummer = huisNummer;
        Gemeente gemeente = new Gemeente(postcode, gemeenteNaam);
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisNummer() {
        return huisNummer;
    }

    public void setHuisNummer(String huisNummer) {
        this.huisNummer = huisNummer;
    }

    public Gemeente getGemeente() {
        return gemeente;
    }

    @Override
    public String toString() {
        return straat + " " + huisNummer + "\n" + gemeente;
    }
}
