package be.pxl.oefenexamen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/* naam: */
public class Wedstrijd implements DatumVerkrijgbaar{
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private LocalDateTime wedstrijdDatum;
    private Team team1;
    private Team team2;
    private Scheidsrechter scheidsrechter;
    private int scoreTeam1;
    private int scoreTeam2;

    public Wedstrijd(Team team1, Team team2, Scheidsrechter scheidsrechter, int dag, int maand, int jaar, int uur, int minuut){
        this.team1 = team1;
        this.team2 = team2;
        this.scheidsrechter = scheidsrechter;
        this.wedstrijdDatum = LocalDateTime.of(jaar, maand, dag, uur, minuut);
    }

    public boolean spelerIdKomtVoor(String spelerId){
        if(team1.spelerIdKomtVoor(spelerId)){
            return true;
        }else return team2.spelerIdKomtVoor(spelerId);
    }

    @Override
    public long berekenAantalMinutenNa(LocalDateTime Datum) {
        return ChronoUnit.MINUTES.between(Datum, wedstrijdDatum);
    }

    public LocalDateTime getWedstrijdDatum() {
        return wedstrijdDatum;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(team1.toString() + "\n" + team2.toString() + "\n" + FORMAT.format(wedstrijdDatum) + "SCHEIDSRECHTER" + scheidsrechter.toString());
        if(berekenAantalMinutenNa(LocalDateTime.now()) > 0){
            output.append("\nSCORE " + scoreTeam1 + " " + scoreTeam2);
        }
        return output.toString();
    }

    public void setScore(int scoreTeam1, int scoreTeam2) {
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }
}

