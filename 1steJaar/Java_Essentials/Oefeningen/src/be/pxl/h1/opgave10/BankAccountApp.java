package be.pxl.h1.opgave10;

public class BankAccountApp {
    public static void main(String[] args) {
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
    }
}
