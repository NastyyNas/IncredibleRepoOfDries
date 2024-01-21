package be.pxl.h1.opgave5;

public class StripboekApp {
    public static void main(String[] args) {
        Stripboek stripboek = new Stripboek();
        stripboek.setReeks("Suske en wiske");
        stripboek.setTitel("De nacht van Narwal");
        stripboek.setAlbum(350);
        stripboek.setAuteur("Willy Vandersteen");
        System.out.println(stripboek.getReeks() + ". " + stripboek.getTitel());
        System.out.println(stripboek.getAuteur());
    }
}
