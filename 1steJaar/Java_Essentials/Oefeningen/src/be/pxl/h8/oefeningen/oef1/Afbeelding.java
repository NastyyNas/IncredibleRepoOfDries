package be.pxl.h8.oefeningen.oef1;

public class Afbeelding extends Bestand{
    private int[] dimensies;
    public Afbeelding(String naam, int[] dimensies){
        super(naam);
        this.dimensies = dimensies;
    }

    @Override
    public void voerUit() {
        for(int i = 0; i < dimensies.length; i++){
            System.out.println(super.getInhoud());
        }

    }
}
