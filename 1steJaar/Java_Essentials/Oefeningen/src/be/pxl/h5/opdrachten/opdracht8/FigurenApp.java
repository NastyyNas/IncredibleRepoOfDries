package be.pxl.h5.opdrachten.opdracht8;

public class FigurenApp {
    public static void main(String[] args) {
        Cirkel cirkel1 = new Cirkel(10, 20, 50);
        System.out.printf("Cirkel1 omtrek: %.2f opp: %.2f%n", cirkel1.getOmtrek(), cirkel1.getOppervlakte());
        Cirkel cirkel2 = new Cirkel(1, 5, 20);
        System.out.printf("Cirkel1 omtrek: %.2f opp: %.2f%n", cirkel2.getOmtrek(), cirkel2.getOppervlakte());
        System.out.println("Het aantal aangemaakte figuren " + GrafischElement.getTeller());

    }
}
