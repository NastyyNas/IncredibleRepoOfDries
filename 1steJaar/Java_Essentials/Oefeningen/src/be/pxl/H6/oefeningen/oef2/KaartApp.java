package be.pxl.H6.oefeningen.oef2;

import java.util.*;

public class KaartApp {
    public static void main(String[] args) {
        ArrayList<Kaart> kaart = new ArrayList<>();
        int teller = 0;
        for(Soort soort: Soort.values()){
            for(Kaarten waarde: Kaarten.values()){
                kaart.add(new Kaart(waarde, soort));
            }
        }
        Collections.shuffle(kaart);
        int computer = (int)Math.floor(Math.random()*(52));
        int speler = (int)Math.floor(Math.random()*(52));
        Kaart kaartspeler = kaart.get(speler);
        Kaart kaartcomputer = kaart.get(computer);
        if(kaartspeler.getWaarde().ordinal() > kaartcomputer.getWaarde().ordinal()){
            System.out.printf("speler wint met %s %s (speler) tegen %s %s (computer)", kaartspeler.getSoort(), kaartspeler.getWaarde(), kaartcomputer.getSoort(), kaartcomputer.getWaarde());
        }else if(kaartspeler.getWaarde().ordinal() < kaartcomputer.getWaarde().ordinal()){
            System.out.printf("computer wint met %s %s (computer) tegen %s %s (speler)", kaartcomputer.getSoort(), kaartcomputer.getWaarde(), kaartspeler.getSoort(), kaartspeler.getWaarde());
        }else{
            System.out.println("gelijkspel");
        }

    }
}
