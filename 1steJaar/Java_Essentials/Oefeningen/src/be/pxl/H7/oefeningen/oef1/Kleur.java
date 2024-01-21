package be.pxl.H7.oefeningen.oef1;

public class Kleur {
    private int rood;
    private int groen;
    private int blauw;
    private String hex;

    public Kleur(int rood, int groen, int blauw){
        this.rood = rood;
        this.groen = groen;
        this.blauw = blauw;
        rgbToHex();
    }

    public Kleur(String hex){
        this.hex = hex;
        hexToRGB();
    }

    private void hexToRGB(){
        String rood = hex.substring(0,2);
        String groen = hex.substring(2, 4);
        String blauw = hex.substring(4, 6);
        int roodDecimal = Integer.parseInt(rood, 16);
        int groenDecimal = Integer.parseInt(groen, 16);
        int blauwDecimal = Integer.parseInt(blauw, 16);
        this.rood = roodDecimal;
        this.groen = groenDecimal;
        this.blauw = blauwDecimal;
    }

    private void rgbToHex(){
        String roodHex = Integer.toHexString(rood);
        String groenHex = Integer.toHexString(groen);
        String blauwHex = Integer.toHexString(blauw);
        this.hex = "#" + roodHex + groenHex + blauwHex;
    }

    public void setHex(String hex) {
        this.hex = hex;

    }

    public void setRGB(int rood, int groen, int blauw){
        this.rood = rood;
        this.groen = groen;
        this.blauw = blauw;
    }

    @Override
    public String toString() {
        if(hex.charAt(0) == '#'){
            return String.format("%s (%d, %d, %d)", hex, rood, groen, blauw);
        }else{
            return "ongeldig hex formaat";
        }
    }
}
