package be.pxl.h1.opgave9;

public class Thermometer {
    private double temperatuur;

    public double getTemperatuur() {
        return temperatuur;
    }

    public void setTemperatuur(double temperatuur) {
        this.temperatuur = temperatuur;
    }
    public double getFarenheit(){
        return 9.0 / 5.0 * temperatuur + 32;
    }
}
