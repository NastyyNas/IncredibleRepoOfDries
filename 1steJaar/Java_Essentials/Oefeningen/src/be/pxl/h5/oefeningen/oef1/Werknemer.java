package be.pxl.h5.oefeningen.oef1;

public class Werknemer extends Persoon{
    private String functie;
    private double salaris;
    public static final double MINIMUM = 1000;
    private static int teller = 0;

    public Werknemer(String naam, String voornaam){
        this(naam, voornaam, "algemeen bediende", 1800);
    }

    public Werknemer(String naam, String voornaam, String functie, double salaris){
        super(voornaam, naam);
        this.functie = functie;
        setSalaris(salaris);
        teller++;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public double getSalaris() {
        return salaris;
    }

    public void setSalaris(double salaris) {
        if(salaris < MINIMUM){
            this.salaris = MINIMUM;
        }else{
            this.salaris = salaris;
        }

    }

    public static int getTeller() {
        return teller;
    }

    public void print(){
        super.print();
        System.out.println(" " + functie + " " + getSalaris());
    }
}
