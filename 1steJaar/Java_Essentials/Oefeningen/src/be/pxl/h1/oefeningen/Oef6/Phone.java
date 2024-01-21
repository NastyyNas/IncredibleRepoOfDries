package be.pxl.h1.oefeningen.Oef6;

public class Phone {
    private String number;
    private String provider;
    private int numberOfCalls;
    private int numberOfFreeCalls;

    public void call(String number){
        System.out.println("Calling " + number);
        if (number.equals("112") || number.equals("102") || number.equals("070245245")){
            numberOfFreeCalls++;
        }else {
            numberOfCalls++;
        }

    }

    public double getCost(){
        double prijs = 0;
        if(provider.equals("Floximus")){
            prijs = numberOfCalls * 0.25;
            if (numberOfCalls >= 20){
                prijs *= 0.95;
            }
        }else{
            prijs = numberOfCalls * 0.21;
        }

        return prijs;
    }

    public void reset(){
        numberOfCalls = 0;
        numberOfFreeCalls = 0;

    }

}
