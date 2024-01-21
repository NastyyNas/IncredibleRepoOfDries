package be.pxl.h5.oefeningen.oef1;

public class WerknemerApp {
    public static void main(String[] args) {
        Werknemer werknemer1 = new Werknemer("dries", "melotte");
        Werknemer werknemer2 = new Werknemer("jan", "knoops");
        Manager manager1 = new Manager("Vincent", "yalla", 900, "Verkoop");
        System.out.printf("%.2f", Manager.getProcAandeelManagers());
        System.out.println();
        System.out.println(werknemer1.getSalaris());
        System.out.println(manager1.getSalaris());
    }
}
