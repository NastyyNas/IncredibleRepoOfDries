package be.pxl.H7.opdrachten;

import java.math.BigInteger;
import java.util.Scanner;

public class Opdracht2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("geef een getal in");
        int getal = scanner.nextInt();
        System.out.println(berekenFaculteit(getal));

        BigInteger faculteit = new BigInteger("1");
        for(int i = 2; i <= getal; i++){
            faculteit = faculteit.multiply(BigInteger.valueOf(i));
        }
        System.out.println(faculteit);
    }

    public static int berekenFaculteit(int getal){
        int faculteit = 1;
        for (int i = 2; i <= getal; i++){
            faculteit *= i;
        }
        return faculteit;
    }
}
