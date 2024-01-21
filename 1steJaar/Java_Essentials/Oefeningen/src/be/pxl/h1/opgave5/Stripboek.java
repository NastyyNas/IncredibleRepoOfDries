package be.pxl.h1.opgave5;

public class Stripboek {
// initiatie eigenschappen
    private String reeks;
    private String titel;
    private int album;
    private String auteur;
//getters
    public String getReeks() {
        return reeks;
    }

    public String getTitel() {
        return titel;
    }

    public int getAlbum() {
        return album;
    }

    public String getAuteur() {
        return auteur;
    }
//setters

    public void setReeks(String reeks) {
        this.reeks = reeks;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
