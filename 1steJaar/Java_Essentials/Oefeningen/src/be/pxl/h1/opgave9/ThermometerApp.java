package be.pxl.h1.opgave9;

import javax.management.StandardEmitterMBean;
import java.util.Scanner;

public class ThermometerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Thermometer thermometer = new Thermometer();
        System.out.println("geef de temperatuur in Celsius in: ");
        double celsius = scanner.nextDouble();
        thermometer.setTemperatuur(celsius);
        System.out.println("De temperqtuur in Farenheit is " + thermometer.getFarenheit());
    }
}
