package be.pxl.h1.opgave8;

public class PlaneetApp {
    public static void main(String[] args) {
        Planeet mars = new Planeet();
        mars.setNaam("mars");
        mars.setDiameter(6792);
        mars.setAfstandZon(227900000);
        Planeet neptunus = new Planeet();
        neptunus.setNaam("neptunus");
        neptunus.setDiameter(49528);
        neptunus.setAfstandZon(1495100000);

        if (neptunus.getAfstandZon() < mars.getAfstandZon()){
            System.out.println("Neptunus ligt dichter bij de zon dan Mars");
        }else{
            System.out.println("Mars ligt dichter bij de zon dan Neptunus");
        }

        System.out.println("de afstand van Mars tot de zon uitgedrukt in AE is " + mars.getAfstandAE());
        System.out.println("de afstand van Neptunus tot de zon uitgedrukt in AE is " + neptunus.getAfstandAE());
    }


}
