package be.pxl.h2.oefeningen.oef1;

public class PersoonApp {
    public static void main(String[] args) {
        Persoon persoon1 = new Persoon("Dries", "Melotte");
        Persoon persoon2 = new Persoon();
        Persoon persoon3 = new Persoon(persoon1);
        System.out.println(persoon1.toString());

    }
}
