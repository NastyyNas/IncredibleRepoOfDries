package be.pxl.h4.oefeningen.oef1;

import java.util.Scanner;

public class BandApp {
    public static void main(String[] args) {
        Muzikant[] muzikanten = new Muzikant[3];
        Scanner input = new Scanner(System.in);
        int teller = 0;
        while(teller != 3){
            System.out.println("naam: ");
            String naam = input.next();
            System.out.println("instrument: ");
            String instrument = input.next();
            System.out.println("wat voor geluid maakt je instrument? ");
            String geluid = input.next();
            muzikanten[teller] = new Muzikant(naam, new Instrument(instrument, geluid));
            teller++;
        }
        Band band = new Band("pxl digital", muzikanten);
        band.speel(20);


    }
}
