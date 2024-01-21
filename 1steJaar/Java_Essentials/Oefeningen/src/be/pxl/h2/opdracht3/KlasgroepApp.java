package be.pxl.h2.opdracht3;

public class KlasgroepApp {
    public static void main(String[] args) {
        System.out.println(Klasgroep.MAXIMAAL_STUDENTEN);
        Klasgroep tina = new Klasgroep("1TINa", 35);
        Klasgroep tinb = new Klasgroep("1TINb", 25);
        Klasgroep tinc = new Klasgroep("1TINc", 20);
        System.out.printf("er zijn %d  klasgroepen aangemaakt %n", Klasgroep.getTeller());
        System.out.printf("het totaal aantal studenten %d%n", Klasgroep.getTotaalStudenten());
        tinc.setAantalStudenten(tinc.getAantalStudenten() + 1);
        System.out.printf("het totaal aantal studenten %d%n", Klasgroep.getTotaalStudenten());
        Klasgroep[] klassen = {tina, tinb, tinc};
        for (int i = 0; i < klassen.length; i++){
            System.out.printf("%s heeft %d studenten%n", klassen[i].getNaam(), klassen[i].getAantalStudenten());
        }
        tinc.setAantalStudenten(50);
        drukGemiddeld();
    }

    public static void drukGemiddeld(){
        double gemiddeldPerKlas = (double) Klasgroep.getTotaalStudenten() / Klasgroep.getTeller();
        System.out.printf("gemiddeld aantal studenten per klas %.1f%n", gemiddeldPerKlas);
    }
}
