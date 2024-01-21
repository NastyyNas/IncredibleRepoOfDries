package be.pxl.h5.oefeningen.oef4;

import java.util.Arrays;

public class GeboekteReis{
    private String naam;
    private Reis[] reizen;
    private static int aantal_boekingen;

    public GeboekteReis(String naam) {
        this.naam = naam;
        aantal_boekingen++;
    }

    public void voegReisToe(Reis reis){
        reizen[aantal_boekingen] = reis;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Reis van " + naam + "\n");
        for(int i = 0; i < aantal_boekingen; i++){
            output.append("==> " + i + reizen[i].toString());
            //output.append() // hoe check ik of het een trein of vliegtuig reis is
        }
        return output.toString();
    }
}
