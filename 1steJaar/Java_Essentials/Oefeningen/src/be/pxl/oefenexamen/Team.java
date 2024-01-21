package be.pxl.oefenexamen;
/* naam: */
public class Team{
    private Sport sport;
    private Speler[] spelers;
    public Team(Sport sport){
        this.sport = sport;
        this.spelers = new Speler[sport.getAantalSpelers()];
    }

    public void voegSpelerToe(Speler speler){
        if(speler.getSport() == sport && !spelerKomtVoor(speler) && vindVrijePositie() != -1){
            spelers[vindVrijePositie()] = speler;
        }else{
            System.out.println("foutieve ingave");
        }
    }
    private boolean spelerKomtVoor(Speler speler){
        for(Speler sp: spelers){
            if(sp.getId().equals(speler.getId())){
                return true;
            }
        }
        return false;
    }
    private int vindVrijePositie(){
        int positie = 0;
        for(Speler sp: spelers){
            if(sp == null){
                return positie;
            }
            positie++;
        }
        return -1;
    }
    public boolean spelerIdKomtVoor(String id){
        for(Speler sp: spelers){
            if(sp.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        if(vindVrijePositie() == -1){
            for(Speler sp: spelers){
                output.append(sp.toString());
            }
            return output.toString();
        }else{
            return "onvolledig team";
        }
    }
}

