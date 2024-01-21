package be.pxl.h8.oefeningen.extraOefening;

public abstract class BouwGrond implements Verkoopbaar{
    private String perceelNummer;
    private double perceelGrootte;
    private String bouwVoorschrift;
    public BouwGrond(String perceelNummer, int perceelGrootte){
        this.perceelGrootte = perceelGrootte;
        this.perceelNummer = perceelNummer;
    }

    public String getPerceelNummer() {
        return perceelNummer;
    }

    public void setPerceelNummer(String perceelNummer) {
        this.perceelNummer = perceelNummer;
    }

    public double getPerceelGrootte() {
        return perceelGrootte;
    }

    public void setPerceelGrootte(double perceelGrootte) {
        this.perceelGrootte = perceelGrootte;
    }

    public String getBouwVoorschrift() {
        return bouwVoorschrift;
    }

    public void setBouwVoorschrift(String bouwVoorschrift) {
        this.bouwVoorschrift = bouwVoorschrift;
    }
}
