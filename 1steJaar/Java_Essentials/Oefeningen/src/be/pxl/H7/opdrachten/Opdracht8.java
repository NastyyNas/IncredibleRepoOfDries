package be.pxl.H7.opdrachten;

public class Opdracht8 {
    public static void main(String[] args) {
        int[][] array = new int[4][6];
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; i++)
            array[i][j] = (i+1)* (j+1);
        }
        for(int [] rij: array){
            for(int getal : rij){
                System.out.print(getal + "\t");
            }
            System.out.println();
        }


    }
}
