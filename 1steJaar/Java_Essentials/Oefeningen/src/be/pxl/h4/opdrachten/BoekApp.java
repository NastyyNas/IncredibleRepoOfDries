package be.pxl.h4.opdrachten;

public class BoekApp {
    public static void main(String[] args) {
        Boek boek = new Boek();
        boek.setTitel("Programmeren in java");
        boek.setIsbn("123456789123456789");
        boek.setAantalBladzijden(664);
        Auteur auteur = new Auteur("Barnes", "David");
        boek.setAuteur(auteur);
        System.out.println(boek.getAuteur().toString());
        System.out.println(boek.getAuteur().getVoornaam() + " " + boek.getAuteur().getNaam());
        boek.toonBoekgegevens();
    }
}
