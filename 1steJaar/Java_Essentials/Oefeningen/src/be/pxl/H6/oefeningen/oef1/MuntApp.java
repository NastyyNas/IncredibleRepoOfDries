package be.pxl.H6.oefeningen.oef1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MuntApp {
    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        Scanner input = new Scanner(System.in);
        System.out.println("geef een bedrag in: ");
        int euro = (int) (input.nextDouble() * 100);
        int teller = 0;
        for(Munt munt: Munt.values()){
            while (munt.getWaarde() <= euro){
                euro -= munt.getWaarde();
                teller++;
            }
            if(teller != 0){
                output.append(munt + ": " + teller + "\n");
                teller = 0;
            }

        }
        System.out.println(output.toString());

    }


}
