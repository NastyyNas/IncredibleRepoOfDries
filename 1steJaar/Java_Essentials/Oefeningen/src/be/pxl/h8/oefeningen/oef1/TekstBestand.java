package be.pxl.h8.oefeningen.oef1;

import java.util.Locale;

public class TekstBestand extends Bestand{
    public TekstBestand(String naam){
        super(naam);
    }
    @Override
    public void voerUit() {
        System.out.println(super.getNaam().toUpperCase());
        System.out.println(super.getInhoud() + " bytes");
    }
}
