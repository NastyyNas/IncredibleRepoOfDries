package be.pxl.H7.opdrachten;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Opdracht1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("geef een eerste getal");
        String getal1String = scanner.next();
        System.out.println("geef een tweede getal");
        String getal2String = scanner.next();

        Integer getal1 = Integer.valueOf(getal1String);
        int getal2 = Integer.parseInt(getal2String);
        getal1 = getal1 + getal2;
        System.out.println(getal1);
        System.out.println(Integer.SIZE);
        System.out.println(Integer.BYTES);

        ArrayList <Integer> list = new ArrayList<>();
        list.add(0); list.add(1); list.add(2);
        list.set(0, list.get(0) + 5);
    }


}
