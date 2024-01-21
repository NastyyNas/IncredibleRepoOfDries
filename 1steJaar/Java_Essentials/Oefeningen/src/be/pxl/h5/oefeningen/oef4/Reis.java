package be.pxl.h5.oefeningen.oef4;

public abstract class Reis {
    private String bestemming;
    private double prijs;
    private static final int MINIMUM_PRIJS = 5;

    public Reis(String bestemming,double prijs){
        setBestemming(bestemming);
        setPrijs(prijs);
    }

    public Reis(String bestemming){
        this(bestemming, MINIMUM_PRIJS);
    }

    public String getBestemming() {
        return bestemming;
    }

    public void setBestemming(String bestemming) {
        while(Character.isDigit(bestemming.charAt(0))){
            bestemming = bestemming.substring(1);
        }
        this.bestemming = bestemming;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        if(prijs > getMinimumPrijs()){
            this.prijs = prijs;
        }else{
            this.prijs = MINIMUM_PRIJS;
        }
    }

    public static int getMinimumPrijs() {
        return MINIMUM_PRIJS;
    }

    @Override
    public String toString() {
        return "Reis met bestemming " + bestemming + " kost " + prijs + " euro.";
    }
}
