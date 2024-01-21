package be.pxl.h4.oefeningen.oef2;

public class Trainer {
    private Pokedex pokedex;
    private String naam;

    public Trainer(String naam){
        Pokedex pokedex = new Pokedex(10);
        this.naam = naam;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public String getNaam() {
        return naam;
    }

    public void vangPokemon(Pokemon poke){
        if(!pokedex.bevat(poke)){
            pokedex.voegToe(poke);
        }
    }

    public String toString(){
        return naam;
    }
}
