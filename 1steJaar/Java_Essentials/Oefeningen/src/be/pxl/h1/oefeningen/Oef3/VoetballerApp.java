package be.pxl.h1.oefeningen.Oef3;

import java.util.Scanner;

public class VoetballerApp {
    public static void main(String[] args) {
        Voetballer voetballer = new Voetballer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("naam: ");
        String naam = scanner.next();
        while (!(naam.equals("stop"))){
            voetballer.setNaam(naam);
            System.out.print("positie: ");
            voetballer.setType(scanner.next());
            System.out.print("beoordeling: ");
            voetballer.setBeoordeling(scanner.nextInt());
            System.out.print("Doelpunten: ");
            voetballer.setAantalDoelpunten(scanner.nextInt());
            System.out.print("leeftijd: ");
            voetballer.setLeeftijd(scanner.nextInt());
            System.out.println("prijs = " + voetballer.berekenPrijs());
            System.out.print("naam: ");
            naam = scanner.next();
        }

    }
}
