package be.pxl.h5.oefeningen.oef1;

public class Manager extends Werknemer{
    private double bonus;
    private static int teller = 0;

    public Manager(String naam, String voornaam, double salaris, String functie){
        this(naam, voornaam, salaris, functie, 50);
    }

    public Manager(String naam, String voornaam, double salaris, String functie, double bonus){
        super(naam, voornaam, functie, salaris);
        this.bonus = bonus;
        teller++;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalaris() {
        return super.getSalaris() + bonus;
    }

    public static double getProcAandeelManagers(){
        double proc = Math.round((double) teller / (double) Manager.getTeller() * 100 * 100) / 100.0;
        return proc;
    }
}
