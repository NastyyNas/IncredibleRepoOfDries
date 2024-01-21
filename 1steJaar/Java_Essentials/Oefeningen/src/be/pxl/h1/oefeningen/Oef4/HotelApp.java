package be.pxl.h1.oefeningen.Oef4;

import java.util.Scanner;

public class HotelApp {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef het aantal volwassenen: ");
        int aantalVolwassenen = scanner.nextInt();
        System.out.println("Geef het aantal kinderen: ");
        int aantalKinderen = scanner.nextInt();
        System.out.println("Geef de hotelcode: ");
        String hotelcode = scanner.next();
        hotel.setHotelcode(hotelcode);
        int aantalSterren = 0;
        String kindercode = "";
        String output = "";
        while (!hotelcode.equals("/")){
            System.out.println("Geef het aantal sterren: ");
            aantalSterren = scanner.nextInt();
            hotel.setAantalSterren(aantalSterren);
            System.out.println("Geef de kindercode: ");
            kindercode = scanner.next();

            output += String.format(hotelcode + "%5s %f %f %f \n", hotel.getSterren(), hotel.getPrijs(), hotel.getPrijsKind(), hotel.getPrijs() * aantalVolwassenen + hotel.getPrijsKind() * aantalKinderen);
            System.out.println("Geef de hotelcode: ");
            hotelcode = scanner.next();
            hotel.setHotelcode(hotelcode);
        }
        System.out.println(output);

    }
}
