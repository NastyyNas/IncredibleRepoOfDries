package be.pxl.h1.oefeningen.Oef1;

public class TorensApp {
    public static void main(String[] args) {
        Torens torens = new Torens();
        torens.setNaam("zuidertoren");
        torens.setLocatie("Sint-Gillis, Brussel");
        torens.setVoltooid(1967);
        torens.setHoogte(150);
        torens.setVerdiepingen(37);
        System.out.println(torens.getNaam() + " in " + torens.getLocatie() + " is voltooid in " + torens.getVoltooid() + " de toren is " + torens.getHoogte() + "m hoog en heeft " + torens.getVerdiepingen() + " verdiepingen");
    }
}
