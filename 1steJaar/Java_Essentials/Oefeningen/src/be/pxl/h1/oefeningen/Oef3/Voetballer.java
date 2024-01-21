package be.pxl.h1.oefeningen.Oef3;

public class Voetballer {
    private String naam;
    private int leeftijd;
    private int beoordeling;
    private String type;
    private int aantalDoelpunten;

    public String getNaam() {
        return naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public int getBeoordeling() {
        return beoordeling;
    }

    public String getType() {
        return type;
    }

    public int getAantalDoelpunten() {
        return aantalDoelpunten;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public void setBeoordeling(int beoordeling) {
        this.beoordeling = beoordeling;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAantalDoelpunten(int aantalDoelpunten) {
        this.aantalDoelpunten = aantalDoelpunten;
    }

    public int berekenPrijs(){
        int prijs = 0;
        if (type.equals("aanvaller")){
            prijs += 30000;
            if (aantalDoelpunten > 5){
                aantalDoelpunten -= 5;
                prijs += 50000 + aantalDoelpunten * 20000;
            }else{
                prijs += aantalDoelpunten * 10000;
            }
        }else if(type.equals("middenvelder")){
            prijs += 28000;
            prijs += 10000 * beoordeling;
        }else if (type.equals("verdediger")){
            prijs += 29000;
            prijs += 10000 * beoordeling;
        }else if (type.equals("doelman")){
            prijs += 25000;
            prijs += 10000 * beoordeling;
            if (aantalDoelpunten > 20){
                aantalDoelpunten -= 20;
                prijs -= aantalDoelpunten * 9000;
            }
        }
        if (leeftijd < 25){
            prijs *= 1.10;
        }else if(leeftijd > 30){
            prijs *= 0.95;
        }
        return prijs;
    }
}
