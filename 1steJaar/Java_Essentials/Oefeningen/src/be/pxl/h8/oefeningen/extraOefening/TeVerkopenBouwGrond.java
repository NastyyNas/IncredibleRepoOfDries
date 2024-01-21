package be.pxl.h8.oefeningen.extraOefening;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TeVerkopenBouwGrond extends BouwGrond implements Verkoopbaar{
    public String notaris;
    public LocalDate datumTeKoop;
    public double hoogsteBod = 0;
    public LocalDate datumHoogsteBod;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMMM yyyy");
    public TeVerkopenBouwGrond(String perceelNummer, int perceelGrootte, String bouwVoorschrift){
        super(perceelNummer, perceelGrootte);
        super.setBouwVoorschrift(bouwVoorschrift);
    }

    public String getNotaris() {
        return notaris;
    }

    public void setNotaris(String notaris) {
        this.notaris = notaris;
    }

    public LocalDate getDatumTeKoop() {
        return datumTeKoop;
    }

    public void setDatumTeKoop(LocalDate datumTeKoop) {
        this.datumTeKoop = datumTeKoop;
    }

    public double getHoogsteBod() {
        return hoogsteBod;
    }

    public void setHoogsteBod(double hoogsteBod) {
        this.hoogsteBod = hoogsteBod;
    }

    public LocalDate getDatumHoogsteBod() {
        return datumHoogsteBod;
    }

    public void setDatumHoogsteBod(LocalDate datumHoogsteBod) {
        this.datumHoogsteBod = datumHoogsteBod;
    }

    @Override
    public void wijsEenNotarisToe(String naam, LocalDate datum) {
        this.notaris = naam;
        this.datumTeKoop = datum;
    }

    @Override
    public void doeEenBod(double prijs, LocalDate datum) {
        if(notaris != null && datumTeKoop.plusDays(10).isBefore(datum) && prijs > hoogsteBod){
            System.out.println("Perceelnummer: " + getPerceelNummer());
            System.out.println("Perceelgrootte: " + getPerceelGrootte() + " are");
            System.out.println("Bouwvoorschrift: " + getBouwVoorschrift());
            System.out.printf("Te koop gesteld op %s bij notaris %s", formatter.format(datum), notaris);
            if(hoogsteBod != 0){
                System.out.printf("Vorig hoogste bod %.3f geregistreerd op %s", hoogsteBod, datumHoogsteBod);
            }
            System.out.printf("nieuw hoogste bod %.3f geregistreerd op %s", prijs, datum);
            this.hoogsteBod = prijs;
            this.datumHoogsteBod = datum;
        }
    }
}
