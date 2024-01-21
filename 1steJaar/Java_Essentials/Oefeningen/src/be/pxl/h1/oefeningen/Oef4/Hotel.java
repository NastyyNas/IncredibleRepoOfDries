package be.pxl.h1.oefeningen.Oef4;

public class Hotel {
    private String hotelcode;
    private int aantalSterren;
    private char kindercode;

    public String getHotelcode() {
        return hotelcode;
    }

    public int getAantalSterren() {
        return aantalSterren;
    }

    public char getKindercode() {
        return kindercode;
    }

    public void setHotelcode(String hotelcode) {
        this.hotelcode = hotelcode;
    }

    public void setAantalSterren(int aantalSterren) {
        this.aantalSterren = aantalSterren;
    }

    public void setKindercode(char kindercode) {
        this.kindercode = kindercode;
    }

    public String getSterren() {
        String sterren = "";
        for (int i = 1; i <= aantalSterren; i++) {
            sterren += "*";
        }
        return sterren;
    }

    public double getPrijs() {
        double prijs = 0;
        if (hotelcode.equals("HI")) {
            prijs = 70;
        } else if (aantalSterren >= 4) {
            prijs = 60;
        } else if (aantalSterren > 2) {
            if (hotelcode.equals("BR") || hotelcode.equals("AN")) {
                prijs = 60;
            } else {
                prijs = 56;
            }
        } else {
            prijs = 56;
        }

        return prijs;
    }

    public double getPrijsKind() {
        double prijs = 0;
        if (kindercode == 'A' && hotelcode.equals("HA") || aantalSterren <= 2) {
            prijs = getPrijs() / 2;
        }
        return prijs;
    }
}
