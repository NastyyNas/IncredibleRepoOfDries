package be.pxl.h4.oefeningen.oef1;

import java.util.Random;

public class Band {
    private String naam;
    private Muzikant[] muzikant; //???

    public Band(String naam, Muzikant[] muzikant){
        this.naam = naam;
        this.muzikant = muzikant; //???
    }

    public void speel(int aantal){
        System.out.println(naam + " in concert!");
        Random random = new Random();
        for(int i = 0; i < aantal; i++){
            muzikant[random.nextInt(3)].speel();
        }
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(naam).append("\n");
        for(int i = 0; i < muzikant.length; i++){
            output.append(muzikant[i].toString());
        }
        return output.toString();
    }
}
