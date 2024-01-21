package be.pxl.h1.opgave11;


import java.util.Scanner;

public class BankAccountApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNaam("Alberto vermicelli");
        bankAccount.setCode("BE");
        bankAccount.setControleCijfers(12);
        bankAccount.setNummer(123456789012L);
        if (bankAccount.isGeldig()){
            System.out.println("geldige bankrekeningnummer");

        }else{
            System.out.println("geen geldige bankrekeningnummer");
        }
        System.out.println("geef het startbedrag:");
        double startBedrag = scanner.nextDouble();
        bankAccount.setBedrag(startBedrag);
        System.out.println("wil je geld afhalen (J/N)");
        String antwoord = scanner.next();
        while (antwoord.equals("j") && bankAccount.getBedrag() != 0){
            System.out.printf("Hoeveel geld wil je afhalen (max. %.2f)?%n", bankAccount.getBedrag());
            double bedrag = scanner.nextDouble();
            bankAccount.haalaf(bedrag);
            System.out.println("wil je geld afhalen (J/N)");
            antwoord = scanner.next();
        }
    }
}
