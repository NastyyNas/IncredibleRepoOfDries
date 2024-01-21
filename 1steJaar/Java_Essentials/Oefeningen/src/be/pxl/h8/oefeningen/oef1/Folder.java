package be.pxl.h8.oefeningen.oef1;

import java.util.ArrayList;
import java.util.Arrays;

public class Folder implements Uitvoerbaar{
    private String naam;
    private ArrayList<Bestand> bestanden = new ArrayList<>();
    public Folder(String naam){
        this.naam = naam;
    }

    public void voegBestandentoe(Bestand[] bestand){
        bestanden.addAll(Arrays.asList(bestand));
    }

    @Override
    public void voerUit() {
        System.out.println(naam);
        for(Bestand file: bestanden){
            System.out.println(file.toString());
        }
    }
}
