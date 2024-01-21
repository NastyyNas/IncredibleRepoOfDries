package be.pxl.h5.opdrachten;

import javax.annotation.processing.SupportedOptions;

public class Rund extends Dier{
    private boolean melkKoe;

    public Rund() {
        super("koe", "herbivoor");
    }

    public Rund(String soort, String voedselType, int geboortejaar, boolean melkKoe) {
        super("koe", "herbivoor", geboortejaar);
        this.melkKoe = melkKoe;
    }

    @Override
    public void print(){
        if(melkKoe){
            System.out.println("melkkoe");
        }else{
            System.out.println("vleeskoe");
        }
    }
}
