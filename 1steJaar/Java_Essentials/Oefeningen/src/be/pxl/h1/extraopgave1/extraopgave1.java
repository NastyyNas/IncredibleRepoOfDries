package be.pxl.h1.extraopgave1;

import java.util.Scanner;

public class extraopgave1 {
    public static void main(String[] args) {
        for (int getal = 0; getal <= 100; getal += 10){
            System.out.println(getal + "\t");
        }
        System.out.println();


        Scanner scanner = new Scanner(System.in);
        System.out.println("geef de grootte van de driehoek in");
        int grootte = scanner.nextInt();
        System.out.println("geef de beginletter in");
        char letter = scanner.next().charAt(0);
        for (int i = 1; i <= grootte; i++){
            for (int j = 1; j <= i; j++){
                System.out.printf("%c ", letter);
                letter++;
                if (letter > 'Z'){
                    letter = 'A';
                }
            }
            System.out.println();
        }


        for (char let = 'A'; let <= 'Z'; let++){
            System.out.print(let);
        }
        System.out.println();
    }
}
