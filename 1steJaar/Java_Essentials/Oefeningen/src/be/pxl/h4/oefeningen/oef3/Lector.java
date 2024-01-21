package be.pxl.h4.oefeningen.oef3;

public class Lector {
    private String naam;
    private String voornaam;
    private Vak[] vakken;
    private int aanstellingspercentage;

    public Lector(String naam, String voornaam, int aanstellingspercentage) {
        vakken = new Vak[5];
        this.naam = naam;
        this.voornaam = voornaam;
        setAanstellingspercentage(aanstellingspercentage);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public int getAanstellingspercentage() {
        return aanstellingspercentage;
    }

    public Vak[] getVakken() {
        return vakken;
    }

    public void setVakken(Vak[] vakken) {
        this.vakken = vakken;
    }

    public void setAanstellingspercentage(int aanstellingspercentage) {
        if(aanstellingspercentage > 100){
            System.out.println("het percentage mag niet boven de 100 zijn.");
        }else{
            this.aanstellingspercentage = aanstellingspercentage;
        }

    }


}
