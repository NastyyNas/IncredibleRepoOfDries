package be.pxl.h5.oefeningen.oef2;
import be.pxl.h5.oefeningen.oef1.Persoon;

public class Sporter extends Persoon{
    private String sport;
    private static int teller = 0;

    public Sporter(String naam, String voornaam, String sport){
        super(naam, voornaam);
        this.sport = sport;
        teller++;
    }
    public Sporter(String naam, String voornaam){
        this(naam, voornaam, "onbepaald");
    }

    public static int getTeller() {
        return teller;
    }

    public void wijzigSport(String sport) {
        this.sport = sport;
    }

    public void print(){
        super.print();
        System.out.print(" " + sport);
    }
}
