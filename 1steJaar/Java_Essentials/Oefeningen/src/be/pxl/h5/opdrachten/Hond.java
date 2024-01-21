package be.pxl.h5.opdrachten;

public class Hond extends Dier{
    private String naam;
    private String ras;
    private Persoon eigenaar;

    public Hond(String naam, String ras) {
        super("hond", "carnivoor");
        this.naam = naam;
        this.ras = ras;
    }

    public Hond(int geboortejaar, String naam, String ras) {
        this(naam, ras);
        this.setGeboortejaar(geboortejaar);
    }

    public Hond(String naamEigenaar, String voornaamEigenaar, int geboortejaar, String naam, String ras, Persoon persoon) {
        super(ras, naam, geboortejaar);
        this.naam = naam;
        this.ras = ras;
        this.eigenaar = new Persoon(naamEigenaar, voornaamEigenaar);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getRas() {
        return ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }

    public Persoon getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Persoon eigenaar) {
        this.eigenaar = eigenaar;
    }

    @Override
    public void print(){
        super.print();
        System.out.printf("het is %s de %s%n", naam, ras);
        System.out.printf("Mijn baasje is %s %s%n", eigenaar.getVoornaam(), eigenaar.getNaam());
    }
}
