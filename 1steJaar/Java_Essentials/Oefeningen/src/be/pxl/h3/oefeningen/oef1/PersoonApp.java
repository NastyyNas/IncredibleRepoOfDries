package be.pxl.h3.oefeningen.oef1;


import java.util.Random;

public class PersoonApp {
    public static void main(String[] args) {
        Persoon persoon1 = new Persoon("Dries", "Melotte");
        Random rand = new Random();
        int gewicht = rand.nextInt(61) + 40;
        double lengte = (double) ((rand.nextInt(52)+158) / 100);
        int encryption = rand.nextInt(20);
        persoon1.setGewicht(gewicht);
        persoon1.setLengte(lengte);
        persoon1.encrypteerNaam(encryption);
        System.out.println(persoon1.toString());


    }
}
