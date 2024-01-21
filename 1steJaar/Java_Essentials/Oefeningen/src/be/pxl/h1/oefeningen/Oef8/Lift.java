package be.pxl.h1.oefeningen.Oef8;

public class Lift {
    private int aantalVerdiepingen = 10;
    private int huidigeVerdieping = 0;
    private int aantalToegelatenPersonen = 5;
    private int huidigAantalPersonen;

    public void setAantalVerdiepingen(int aantalVerdiepingen) {
        this.aantalVerdiepingen = aantalVerdiepingen;
    }

    public void setAantalToegelatenPersonen(int aantalToegelatenPersonen) {
        this.aantalToegelatenPersonen = aantalToegelatenPersonen;
    }

    public void betreed(int aantalPersonen){
        if(huidigAantalPersonen + aantalPersonen > aantalToegelatenPersonen){
            System.out.printf("de lift zit vol er is nog maar plek voor %d persoon/personen", aantalToegelatenPersonen - huidigAantalPersonen);

        }else{
            huidigAantalPersonen += aantalPersonen;
        }
    }

    public void verlaat(int aantalPersonen){
        if(aantalPersonen > huidigAantalPersonen){
            System.out.printf("er kan niet zoveel man verlaten er zijn maar %d personen in de lift", huidigAantalPersonen);
        }else{
            huidigAantalPersonen -= aantalPersonen;
        }
    }

    public void gaNaar(int verdieping){
        if(verdieping > aantalVerdiepingen){
            System.out.println("er zijn niet zo veel verdiepingen");
        }else if (verdieping > huidigeVerdieping){
            for (int i = huidigeVerdieping; i <= verdieping; i++){
                System.out.println("---");
                System.out.println("|" + i + "|");
                System.out.println("---");
            }
            huidigeVerdieping = verdieping;
        }else{
            for (int i = huidigeVerdieping; i >= verdieping; i--){
                System.out.println("---");
                System.out.println("|" + i + "|");
                System.out.println("---");
            }
        }
    }

}
