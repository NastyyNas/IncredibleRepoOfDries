package be.pxl.h4.oefeningen.oef4;

public class Gemeente {
    private int postcode;
    private String gemeenteNaam;

    public Gemeente(int postcode, String gemeenteNaam){
        this.postcode = postcode;
        this.gemeenteNaam = gemeenteNaam;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        while(postcode > 9999){
            postcode = postcode/10;

        }
        while(postcode < 1000){
            postcode = postcode * 10;
        }
        this.postcode = postcode;
    }

    public String getGemeenteNaam() {
        return gemeenteNaam;
    }

    public void setGemeenteNaam(String gemeenteNaam) {
        this.gemeenteNaam = gemeenteNaam;
    }

    @Override
    public String toString() {
        return postcode + " " + gemeenteNaam;
    }
}
