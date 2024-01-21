package be.pxl.h1.opgave2;

public class AutoApp {
    public static void main(String[] args) {
        System.out.println("We maken een eerste auto aan");
        Auto auto = new Auto();

        System.out.println(auto.getMerk());
        System.out.println(auto.getAantalDeuren());
        System.out.println(auto.getKleur());
        System.out.println(auto.getKilometerstand());
        auto.setMerk("Volkswagen");
        auto.setKleur("rood");
        auto.setKilometerstand(125000);
        auto.setAantalDeuren(4);
        System.out.println("deze auto is van het merk " + auto.getMerk() + " in de kleur " + auto.getKleur());
        System.out.println("het is een wagen met " + auto.getAantalDeuren() + " deuren.");
        System.out.println("De kilometerstand bedraagt " + auto.getKilometerstand());
    }
}
