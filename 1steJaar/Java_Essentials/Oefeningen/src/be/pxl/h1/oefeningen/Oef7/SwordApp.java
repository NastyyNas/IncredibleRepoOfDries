package be.pxl.h1.oefeningen.Oef7;

public class SwordApp {
    public static void main(String[] args) {
        Sword[] sword = new Sword[3];
        sword[0] = new Sword();
        sword[0].setMaterial("wood");
        sword[0].setDurability(33);
        sword[1] = new Sword();
        sword[1].setMaterial("stone");
        sword[1].setDurability(500);
        sword[2] = new Sword();
        sword[2].setMaterial("diamond");
        sword[2].setDurability(1500);
        String biggestMat = sword[0].getMaterial();
        int biggestDur = sword[0].getDurability();
        for (int i = 1; i < sword.length; i++){
            if (sword[i].getDurability() > biggestDur){
                biggestMat = sword[i].getMaterial();
                biggestDur = sword[i].getDurability();
            }
        }
        System.out.printf("most durable sword is of %s ", biggestMat);
        System.out.println();

        System.out.printf("|%10s|", "Materiaal");
        System.out.printf("%15s|", "Duurzaamheid");
        System.out.println();
        for (int i = 0; i < sword.length; i++){
            System.out.printf("|%10s|", sword[i].getMaterial());
            System.out.printf("%15s|", sword[i].getDurability());
            System.out.println();
        }



    }
}
