package be.pxl.h4.oefeningen.oef2;

public class Pokedex {
    private Pokemon[] pokemon;

    public Pokedex(int grootte) {
        Pokemon[] pokemon = new Pokemon[grootte];
    }

    public Pokemon[] getPokemon() {
        return pokemon;
    }

    public boolean bevat(Pokemon poke){
        for(Pokemon pokemon: pokemon){
            if (pokemon != null && pokemon.getNaam().equals(poke.getNaam())){
                return true;
            }
        }
        return false;
    }

    public void voegToe(Pokemon poke){
        int index =  getEersteLegePlaats();
        if(index != -1){
            pokemon[index] = poke;
        }

    }

    private int getEersteLegePlaats(){
        for(int i = 0; i < pokemon.length; i++){
            if (pokemon[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        for(Pokemon poke: pokemon){
            if(poke != null){
                output.append(String.format("- %s (%s)%n", poke.getNaam(), poke.getType()));
            }
        }
        return output.toString();
    }

}
