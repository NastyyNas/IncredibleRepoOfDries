package be.pxl.h1.ExtraOpgave2;

public class extraopgave2 {
    public static void main(String[] args) {
        // maak een array van 20 getallen en vul deze met veelvouden van 7
        int[] array1 = new int[20];
        for (int i = 0; i < array1.length; i++){
            array1[i] = i * 7;
        }
        // druk de array in volgorde af
        System.out.println(array1); // drukt de referentie af
        for (int getal : array1){
            System.out.printf("%4d", getal);
        }
        System.out.println();
        // maak een array van lengte 10 waarin je volgende waarden plaatst 1, 3, 5, 7, 9, 11, 13, 17, 19
        int[] array2 = new int[10];
        for (int i = 1; i <= 19; i += 2){
            array2[i/2] = i;
        }
        // druk deze waarden achterstevoren af
        for (int i = array2.length - 1; i >= 0; i--){
            System.out.printf("%4d", array2[i]);
        }
        System.out.println();
    }
}
