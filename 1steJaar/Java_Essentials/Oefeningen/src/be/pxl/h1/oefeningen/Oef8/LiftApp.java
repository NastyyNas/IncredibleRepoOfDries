package be.pxl.h1.oefeningen.Oef8;

import java.util.Scanner;

public class LiftApp {
    public static void main(String[] args) {
        Lift lift = new Lift();
        Scanner input = new Scanner(System.in);
        System.out.println("Maak uw keuze:");
        System.out.println("1. Ga naar verdieping ...");
        System.out.println("2. Betreed de lift...");
        System.out.println("3. Verlaat de lift...");
        System.out.println("4. Stoppen");
        int keuze = input.nextInt();
        while (keuze != 4){
            if (keuze == 1){
                System.out.print("kies een verdieping: ");
                int verdieping = input.nextInt();
                lift.gaNaar(verdieping);
            }else if(keuze == 2){
                System.out.print("Aantal personen: ");
                int aantalPersonen = input.nextInt();
                lift.betreed(aantalPersonen);
            }else{
                System.out.print("Aantal personen: ");
                int aantalPersonen = input.nextInt();
                lift.verlaat(aantalPersonen);
            }
            System.out.println("Maak uw keuze:");
            System.out.println("1. Ga naar verdieping ...");
            System.out.println("2. Betreed de lift...");
            System.out.println("3. Verlaat de lift...");
            System.out.println("4. Stoppen");
            keuze = input.nextInt();
        }
    }
}
