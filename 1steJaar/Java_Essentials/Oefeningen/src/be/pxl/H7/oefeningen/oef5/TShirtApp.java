package be.pxl.H7.oefeningen.oef5;


import java.util.ArrayList;
import java.util.Scanner;

public class TShirtApp {
    public static void main(String[] args) {
        ArrayList<TShirt> tshirt = new ArrayList<>();
        for(TShirtSize size: TShirtSize.values()){
            for(Color color: Color.values()){
                tshirt.add(new TShirt(size, color));
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("geef je naam in: ");
        String naam = input.nextLine();
        int borstomtrek;
        int kleur;
        while(!naam.equals("stop")){
            System.out.println("geef je borstomtrek in: ");
            borstomtrek = input.nextInt();
            System.out.println("geef de kleur in: ");
            kleur = input.nextInt();
            System.out.println("geef je naam in: ");
            naam = input.nextLine();
        }
        




    }
}
