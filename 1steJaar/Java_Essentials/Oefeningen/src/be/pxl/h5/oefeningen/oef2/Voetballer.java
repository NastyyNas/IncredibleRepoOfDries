package be.pxl.h5.oefeningen.oef2;

public class Voetballer extends Sporter{
    private String club;
    private String opstelling;
    private static final String[] OPSTELLINGEN = {"middenvelder", "aanvaller", "verdediger", "onbepaald"};

    public Voetballer(String naam, String voornaam, String club, String opstelling){
        super(naam, voornaam, "voetbal");
        this.club = club;
        setOpstelling(opstelling);
    }
    public Voetballer(String naam, String voornaam){
        this(naam, voornaam, "onbepaald", "onbepaald");
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getOpstelling() {
        return opstelling;
    }

    public void setOpstelling(String opstelling) {
        this.opstelling = OPSTELLINGEN[3];
        for(int i = 0; i < OPSTELLINGEN.length; i++){
            if (opstelling.equals(OPSTELLINGEN[i])) {
                this.opstelling = OPSTELLINGEN[i];
            }
        }
    }

    public String printOpstellingen() {
        StringBuilder opstellingenOutput = new StringBuilder();
        for(int i = 0; i < OPSTELLINGEN.length - 1; i++){
            opstellingenOutput.append(OPSTELLINGEN[i]);
        }
        return opstellingenOutput.toString();
    }

    public void print(){
        System.out.printf("%s %s%n voetbal%nclub: %s opstelling: %s", getNaam(), getVoornaam(), club, opstelling);
    }
}
