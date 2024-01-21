package be.pxl.h3.oefeningen.oef2;

public class MuntApp {
    public static void main(String[] args) {
        Munt[] munten = new Munt[10];
        Munt britse_pond = new Munt("Britse pond", 0.985);
        munten[0] = britse_pond;
        Munt euro = new Munt();
        munten[1] = euro;
        Munt dollar = new Munt("dollar", 1.287);
        munten[2] = dollar;
        Munt russische_roebel = new Munt("Russische roebel", 86.95);
        munten[3] = russische_roebel;
        for(int i = 0; i < munten.length; i++){
            if(munten[i] != null){
                System.out.printf("%." + Munt.getAfronding() + "f %s%n", munten[i].getKoers(), munten[i].getNaam());
            }
        }
        System.out.printf("overzicht koersen tov %s: 1 %s = %n", munten[0].getNaam(), munten[0].getNaam());
        for(int i = 1; i < munten.length - 1; i++){
            if(munten[i] != null){
                double koers = munten[i].getKoers() + munten[i].getKoers() * (1 - britse_pond.getKoers());
                System.out.printf("%." + Munt.getAfronding() + "f %s%n", koers, munten[i].getNaam());
            }

        }




    }
}
