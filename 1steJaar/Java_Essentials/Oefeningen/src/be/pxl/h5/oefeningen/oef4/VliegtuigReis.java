package be.pxl.h5.oefeningen.oef4;

public class VliegtuigReis extends Reis{
    private String vluchtnummer;
    private static final int MINIMUM_PRIJS = 25;

    public VliegtuigReis(String bestemming, double prijs, String vluchtnummer){
        super(bestemming, prijs);
        this.vluchtnummer = vluchtnummer;
    }

    public VliegtuigReis(String bestemming){
        super(bestemming);
        this.vluchtnummer = bestemming.charAt(0) + "999";
    }

    public String getVluchtnummer() {
        return vluchtnummer;
    }

    public void setVluchtnummer(String vluchtnummer) {
        if(vluchtnummer.charAt(0) == super.getBestemming().charAt(0)){
            this.vluchtnummer = vluchtnummer;
        }else{
            String nieuw_vluchtnummer = super.getBestemming().charAt(0) + vluchtnummer.substring(1);
        }
    }

    public static int getMinimumPrijs() {
        return MINIMUM_PRIJS;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(super.toString() + "\n");
        output.append("Vliegtuigreis (vluchtnr " + vluchtnummer + ")");
        return output.toString();
    }
}

