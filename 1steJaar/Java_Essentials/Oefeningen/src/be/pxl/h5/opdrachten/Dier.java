package be.pxl.h5.opdrachten;

import java.util.Locale;

public class Dier {
    private String soort;
    private String voedselType;
    private int geboortejaar;

    public Dier(String soort, String voedselType) {
        this(soort, voedselType, 2018);
    }

    public Dier(String soort, String voedselType, int geboortejaar) {
        this.soort = soort;
        this.voedselType = voedselType;
        this.geboortejaar = geboortejaar;
    }

    public String getSoort() {
        return soort;
    }

    public String getVoedselType() {

        return voedselType;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public void setVoedselType(String voedselType) {
        if (voedselType.toLowerCase().equals("herbivoor") || voedselType.toLowerCase().equals("omnivoor") || voedselType.toLowerCase().equals("carnivoor")){
            this.voedselType = voedselType;
        }else{
            this.voedselType = "onbekend";
        }

    }

    public void setGeboortejaar(int geboortejaar) {
        this.geboortejaar = geboortejaar;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void print(){
        System.out.printf("Dit is een %s (%s)%n", soort, voedselType);
    }
}
