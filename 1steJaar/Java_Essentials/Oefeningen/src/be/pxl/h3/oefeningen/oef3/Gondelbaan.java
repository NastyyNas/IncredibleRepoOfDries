package be.pxl.h3.oefeningen.oef3;

import java.util.Locale;

public class Gondelbaan {
    private String naam;
    private String land;
    private int hoogteDalstation;
    private int hoogteBergstation;
    private int lengte;
    private int snelheid;
    private int passagiersPerGondel;
    private int aantalGondels;

    public Gondelbaan(String naam, String land, int lengte, int snelheid){
        this.naam = naam;
        setLand(land);
        this.lengte = lengte;
        this.snelheid = snelheid;
    }

    public Gondelbaan(String naam, String land){
        this(naam, land, 2, 6);
    }

    public String getNaam() {
        return naam;
    }

    public String getLand() {
        return land;
    }

    public int getHoogteDalstation() {
        return hoogteDalstation;
    }

    public int getHoogteBergstation() {
        return hoogteBergstation;
    }

    public int getLengte() {
        return lengte;
    }

    public int getSnelheid() {
        return snelheid;
    }

    public int getPassagiersPerGondel() {
        return passagiersPerGondel;
    }

    public int getAantalGondels() {
        return aantalGondels;
    }

    public void setNaam(String naam) {
        String[] woorden = naam.toLowerCase().split(" ");
        StringBuilder Naam = new StringBuilder();
        for(int i = 0; i < woorden.length; i++){
            Naam.append(woorden[i].toUpperCase().charAt(0));
            for(int j = 1; j < woorden.length; j++){
                Naam.append(woorden[i].charAt(i));
            }
        }
        this.naam = Naam.toString();

    }

    public void setLand(String land) {
        if(land.equals("Frankrijk") || land.equals("Oostenrijk") || land.equals("Zwitserland") || land.equals("Italië")){
            this.land = land;
        }else{
            this.land = "Onbekend";
        }
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public void setSnelheid(int snelheid) {
        if (snelheid > 8){
            this.snelheid = 8;
        }else if(snelheid < 3){
            this.snelheid = 3;
        }else{
            this.snelheid = snelheid;
        }

    }

    public void setPassagiersPerGondel(int passagiersPerGondel) {
        this.passagiersPerGondel = passagiersPerGondel;
    }

    public void setAantalGondels(int aantalGondels) {
        this.aantalGondels = aantalGondels;
    }

    public void setHoogte(int hoogteBergstation, int hoogteDalstation){
        this.hoogteBergstation = hoogteBergstation;
        this.hoogteDalstation = hoogteDalstation;
    }

    public int getHoogteVerschil(){
        if (hoogteDalstation - hoogteBergstation < 0){
            return hoogteBergstation - hoogteDalstation;
        }else{
            return hoogteDalstation - hoogteBergstation;
        }
    }

    public int getDuur(){
        return snelheid / getHoogteVerschil();
    }

    public int getVervoersCapaciteit(){
        return (60 * aantalGondels * passagiersPerGondel) / getDuur();
    }

    public String ToString(){
        return naam + " [" + getHoogteVerschil() + "]";
    }
}
