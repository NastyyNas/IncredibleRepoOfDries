package be.pxl.h1.oefeningen.Oef6;

public class PhoneApp {
    public static void main(String[] args) {
        Phone phone = new Phone();

        String number = "";
        for (int i = 10; i < 31; i++){
            number = "01211234" + i;
            phone.call(number);
        }
        phone.call("070245245");
        System.out.printf("phone costs: %.2f", phone.getCost());
        phone.reset();
        System.out.println();
        System.out.printf("phone costs after reset: %.2f", phone.getCost());
    }
}
