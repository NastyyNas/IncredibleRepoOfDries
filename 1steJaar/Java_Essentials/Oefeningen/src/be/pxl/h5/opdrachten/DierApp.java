package be.pxl.h5.opdrachten;

public class DierApp {
    public static void main(String[] args) {
        Dier olifant = new Dier("olifant", "herbivoor");
        Hond hond1 = new Hond("sky", "Jack Russel");
        Hond hond2 = new Hond(2012, "ceasar", "maltheser");
        hond1.setEigenaar(new Persoon("Dries", "Melotte"));
        hond2.setEigenaar(hond1.getEigenaar());
    }
}
