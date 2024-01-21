package be.pxl.h3.oefeningen.oef1;

import java.time.LocalDate;
import java.util.Locale;

public class Persoon {
    private String voornaam;
    private String naam;
    private double lengte;
    private double gewicht;
    private LocalDate geboortedatum;
    public static final double MAX_LENGTE = 2.4; // static  = klassevariable  final = constante

    public Persoon(String voornaam, String naam) { // constructoroverloading
        this.voornaam = voornaam; // shadowing
        this.naam = naam;
    }

    public Persoon() {
        this("onbekend", "onbekend");
    }

    // copyconstructor
    public Persoon(Persoon persoon) {
        this(persoon.voornaam, persoon.naam);
        lengte = persoon.lengte;
        gewicht = persoon.gewicht;
        geboortedatum = persoon.geboortedatum;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setLengte(double lengte) {
        if (lengte > MAX_LENGTE){
            lengte = MAX_LENGTE;
        }
        this.lengte = lengte;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public void setGeboortedatum(LocalDate geboortedatum) { // call by value
        this.geboortedatum = geboortedatum;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public double getLengte() {
        return lengte;
    }

    public double getGewicht() {
        return gewicht;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public int getLeeftijd(){
        return LocalDate.now().compareTo(geboortedatum);
    }

    public String toString() {
        StringBuilder output = new StringBuilder(); // zie H3
        output.append("Deze persoon is ").append(voornaam.toUpperCase()).append(" ").append(naam.toUpperCase());
        output.append(String.format("%n%-15s: %.2f%n", "gewicht", gewicht));
        output.append(String.format("%-15s: %.2f%n", "lengte", lengte));
        output.append(String.format("%-15s: ", "geboortedatum"));
        output.append(geboortedatum).append("\n");
        return output.toString();
    }

    public double berekenBmi() {
        return (double) Math.round(gewicht / (lengte * lengte) * 10) / 10;
    }

    public String geefOmschrijving() {
        double bmi = berekenBmi();
        if (bmi < 18) return "ondergewicht";
        if (bmi < 25) return "normaal";
        if (bmi < 30) return "overgewicht";
        if (bmi < 40) return "obesitas";
        return "morbide obesitas";
    }

    public void voegVoornamenToe(String [] voornamen){ // call by reference
        StringBuilder stringBuilder =  new StringBuilder(this.voornaam);
        for (String voornaam : voornamen){
            stringBuilder.append(" ").append(voornaam);
        }
        this.voornaam = stringBuilder.toString();
    }

    public void groei(){ //method overloading
        setLengte(lengte + 0.01);
    }

    public void groei(double cm){
        setLengte(lengte + cm / 100.0);
    }

    public String geefNaamAfgekort(){
        return voornaam.toUpperCase().charAt(0) + "." + naam;
    }

    public String encrypteerNaam(int getal){
        StringBuilder naamAfgekort = new StringBuilder();
        String afgekorteNaam = geefNaamAfgekort();
        for(int i = 0; i < afgekorteNaam.length(); i++){
            char letter = afgekorteNaam.charAt(i);
            letter = (char) (letter + getal);
            naamAfgekort.append(letter);
        }
        return naamAfgekort.toString();

    }
}
