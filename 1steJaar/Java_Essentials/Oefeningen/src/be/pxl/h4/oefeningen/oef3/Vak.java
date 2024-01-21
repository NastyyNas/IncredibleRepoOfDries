package be.pxl.h4.oefeningen.oef3;

public class Vak {
    private String naam;
    private String code;
    private int aantalStudiepunten;

    public Vak(String naam, String code, int aantalStudiepunten){
        this.naam = naam;
        this.code = code;
        setAantalStudiepunten(aantalStudiepunten);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAantalStudiepunten() {
        return aantalStudiepunten;
    }

    public void setAantalStudiepunten(int aantalStudiepunten) {
        if(aantalStudiepunten > 18){
            System.out.println("een vak kan niet meer dan 18 studiepunten hebben.");
        }else{
            this.aantalStudiepunten = aantalStudiepunten;
        }

    }

    public String toString(){
        return String.format("%14s %20s %5d", code, naam, aantalStudiepunten);
    }
}
