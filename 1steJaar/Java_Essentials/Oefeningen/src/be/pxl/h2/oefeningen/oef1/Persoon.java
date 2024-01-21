package be.pxl.h2.oefeningen.oef1;
public class Persoon {
    private String naam;
    private String voornaam;
    private double lengte;
    private double gewicht;
    private int geboortejaar;

    public Persoon(String naam, String voornaam){
        this.naam = naam;
        this.voornaam = voornaam;
    }
    public Persoon(){
        this("onbekend", "onbekend");
    }

    public Persoon(Persoon p){
        this.naam = p.naam;
        this.voornaam = p.voornaam;
        this.lengte = p.lengte;
        this.gewicht = p.gewicht;
        this.geboortejaar = p.geboortejaar;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public double getLengte() {
        return lengte;
    }

    public double getGewicht() {
        return gewicht;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setLengte(double lengte) {
        if (lengte > 2.40){
            this.lengte = 2.40;
        }else{
            this.lengte = lengte;
        }

    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public void setGeboortejaar(int geboortejaar) {
        this.geboortejaar = geboortejaar;
    }

    public double berekenBMI(){
        return gewicht / (lengte * lengte);
    }

    public String geefOmschrijving(){
        if (berekenBMI() < 18){
            return "ondergewicht";
        }else if(berekenBMI() < 25){
            return "normaal";
        }else if(berekenBMI() < 30){
            return "overgewicht";
        }else if(berekenBMI() < 40){
            return "obesitas";
        }else{
            return "morbide obesitas";
        }
    }

    public void voegVoornamenToe(String[] voornamen){
        String voornaam = "";
        for (String naam: voornamen){
            voornaam += naam + " ";
        }
        this.voornaam += voornaam;
    }

    public void groei(){
        groei(0.01);
    }

    public void groei(double aantal){
        if (lengte + aantal > 2.40){
            lengte += aantal;
        }else{
            lengte += aantal;
        }


    }

    @Override
    public String toString() {
        return String.format("Deze persoon is %s %s %n%15s: %.2f %n%15s: %.2f %n%15s: %d %n", voornaam, naam, "gewicht", gewicht, "lengte", lengte, "geboortejaar", geboortejaar);
    }
}
