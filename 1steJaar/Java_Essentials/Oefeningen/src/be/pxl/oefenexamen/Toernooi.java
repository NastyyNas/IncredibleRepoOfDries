package be.pxl.oefenexamen;

import java.time.LocalDateTime;

/* naam: */
public class Toernooi{
    private Wedstrijd[] wedstrijden;
    private static final int MAX_AANTAL_WEDSTRIJDEN = 10;

    public Toernooi(){
        wedstrijden = new Wedstrijd[MAX_AANTAL_WEDSTRIJDEN];
    }

    public void voegWedstrijdToe(Wedstrijd wedstrijd){
        int i = 0;
        boolean gevonden = false;
        while(!gevonden || i == 11){
            if(wedstrijden[i] == null){
                wedstrijden[i] = wedstrijd;
                gevonden = true;
            }
            i++;
        }
        if(!gevonden){
            System.out.println("geen ruimte meer voor wedstrijden");
        }
    }

    public LocalDateTime zoekDatumVolgendeWedstrijdVan(String spelerId){
        for(Wedstrijd wed: wedstrijden){
            if(wed.spelerIdKomtVoor(spelerId)){
               return wed.getWedstrijdDatum();
            }
        }
        return null;

    }

}

