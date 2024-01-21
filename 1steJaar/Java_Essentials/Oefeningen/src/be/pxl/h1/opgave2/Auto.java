package be.pxl.h1.opgave2;

public class Auto {
    private String merk;
    private String kleur;
    private int kilometerstand;
    private int aantalDeuren;

    public String getMerk(){
        return merk;
    }

    public int getAantalDeuren(){
        return aantalDeuren;
    }

    public String getKleur(){
        return kleur;
    }

    public int getKilometerstand(){
        return kilometerstand;
    }

    public void setKleur(String kleur){
        this.kleur = kleur;
    }

    public void setMerk(String merk){
        this.merk = merk;
    }

    public void setKilometerstand (int kilometerstand){
        this.kilometerstand = kilometerstand;
    }

    public void setAantalDeuren(int aantalDeuren){
        this.aantalDeuren = aantalDeuren;
    }

}
