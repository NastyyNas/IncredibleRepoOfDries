package be.pxl.h5.oefeningen.oef4;

import java.util.Arrays;

public class TreinReis extends Reis{
    private boolean nationaal;
    private String specificatie;
    private static final String[] specificatieList = {"IC", "IR", "L", "P"};

    public TreinReis(String bestemming, double prijs, boolean nationaal, String specificatie){
        super(bestemming, prijs);
        this.nationaal = nationaal;
        setSpecificatie(specificatie);
    }
    public TreinReis(String bestemming){
        super(bestemming);
    }

    public String getSpecificatie() {
        return specificatie;
    }

    public void setSpecificatie(String specificatie) {
        for(int i = 0; i < specificatieList.length; i++){
            if(specificatie.equals(specificatieList[i])){
                this.specificatie = specificatie;
            }else{
                this.specificatie = specificatieList[0];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(super.toString() + "\n");
        if(nationaal){
            output.append("Nationale treinreis (" + specificatie + ")");
        }else{
            output.append("Internationale treinreis (" + specificatie + ")");
        }
        return output.toString();
    }
}
