package be.pxl.h1.opgave13;

import java.util.Scanner;

public class VerkoperApp {
    public static void main(String[] args) {
        Verkoper[] verkopers = new Verkoper[3];
        for (int j = 0; j < verkopers.length; j++) {
            verkopers[j] = new Verkoper();
            Scanner scanner = new Scanner(System.in);
            System.out.println("geef je naam in: ");
            String naam = scanner.next();
            verkopers[j].setNaam(naam);
            System.out.println("geef de cijfers van elke maand in 1 regel");
            for (int i = 1; i <= 12; i++) {
                verkopers[j].setVerkoopMaand(i, scanner.nextDouble());
            }
            scanner.nextLine(); // om enter toets te lezen
            System.out.printf("Totaal verkocht %.0f door verkoper %s%n", verkopers[j].getTotaalVerkocht(), verkopers[j].getNaam());
        }
    }
}
