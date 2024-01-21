package be.pxl.h1.oefeningen.Oef2;

import java.util.Scanner;

public class ZwembadApp {
    public static void main(String[] args) {
        Zwembad zwembad = new Zwembad();
        Scanner scanner = new Scanner(System.in);
        System.out.print("geef de breedte van het zwembad: ");
        zwembad.setBreedte(scanner.nextDouble());
        System.out.print("geef de lengte van het zwembad: ");
        zwembad.setLengte(scanner.nextDouble());
        System.out.print("geef de diepte van het zwembad: ");
        zwembad.setDiepte(scanner.nextDouble());
        // afgekapt
        System.out.println("liter water: " + (int) zwembad.getVolumeWater());
        System.out.println("liter ontsmettingsmiddel " + (int) zwembad.getVolumeOntsmettingsmiddel());

        //afgerond
        System.out.printf("liter water: %.0f ", zwembad.getVolumeWater());
        System.out.println();
        System.out.printf("liter ontsmettingsmiddel: %.0f ", zwembad.getVolumeOntsmettingsmiddel());
    }
}
