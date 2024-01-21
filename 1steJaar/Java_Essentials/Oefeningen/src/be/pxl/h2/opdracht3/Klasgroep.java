package be.pxl.h2.opdracht3;

import java.util.Scanner;

public class Klasgroep {
    private String naam;
    private int aantalStudenten;
    public static final int MAXIMAAL_STUDENTEN = 40;
    private static int teller;
    private static int totaalStudenten;

    public Klasgroep(){
        this.naam = "1TINX";
        teller++;
    }
    public Klasgroep(String naam, int aantalStudenten){
        this.naam = naam;
        this.aantalStudenten = aantalStudenten;
        teller++;
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalStudenten() {
        return aantalStudenten;
    }

    public void setAantalStudenten(int aantalStudenten){
        //if(aantalStudenten > MAXIMAAL_STUDENTEN){
        //    aantalStudenten = MAXIMAAL_STUDENTEN;
        //}
        Scanner scanner = new Scanner(System.in);
        while (!(aantalStudenten <= MAXIMAAL_STUDENTEN && aantalStudenten >= 0)){
            System.out.println("foutieve invoer van aantal studenten. Nieuwe ingave");
            aantalStudenten = scanner.nextInt();
        }
        totaalStudenten = totaalStudenten - this.aantalStudenten + aantalStudenten;
        this.aantalStudenten = aantalStudenten;
    }

    public static int getTeller() {
        return teller;
    }

    public static int getTotaalStudenten() {
        return totaalStudenten;
    }
}
