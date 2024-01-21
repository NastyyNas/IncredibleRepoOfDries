package be.pxl.h2.oefeningen.oef2;

public class BankrekeningApp {
    public static void main(String[] args) {
        Bankrekening bankrekening1 = new Bankrekening("000-3333333333-77", "Dries", 100, 1.2);
        Bankrekening bankrekening2 = new Bankrekening();
        System.out.printf("bankrekening geopend met saldo %.2f euro \n", bankrekening1.getSaldo());
        bankrekening1.neemOp(50);
        System.out.println();
        bankrekening1.print();
        System.out.println();
        bankrekening2.stort(bankrekening1.getSaldo());
        bankrekening1.neemOp(bankrekening2.getSaldo());

    }
}
