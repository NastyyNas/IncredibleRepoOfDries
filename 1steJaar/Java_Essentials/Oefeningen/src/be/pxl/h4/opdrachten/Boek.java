package be.pxl.h4.opdrachten;

public class Boek {
    private String isbn;
    private String titel;
    private int aantalBladzijden;
    private Auteur auteur;

    public Boek(){
        this("onbekend", "onbekend", 0, new Auteur("onbekend", "onbekend"));
    }

    public Boek(String isbn, String titel, int aantalBladzijden, Auteur auteur){
        this.isbn = isbn;
        this.titel = titel;
        this.aantalBladzijden = aantalBladzijden;
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitel() {
        return titel;
    }

    public int getAantalBladzijden() {
        return aantalBladzijden;
    }

    public Auteur getAuteur() {
        return auteur;

    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAantalBladzijden(int aantalBladzijden) {
        this.aantalBladzijden = aantalBladzijden;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public void toonBoekgegevens(){
        System.out.println("isbn nummer " + isbn);
        System.out.println(titel);
        System.out.println(auteur.toString());
        System.out.println("aantal paginas " + aantalBladzijden);
    }
}
