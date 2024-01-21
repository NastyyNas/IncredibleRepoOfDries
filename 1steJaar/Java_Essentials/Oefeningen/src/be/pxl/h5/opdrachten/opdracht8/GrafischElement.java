package be.pxl.h5.opdrachten.opdracht8;

public abstract class GrafischElement {
    private int x;
    private int y;
    private static int teller;

    public GrafischElement(int x, int y){
        this.x = x;
        this.y = y;
        teller++;
    }

    public void setPositie(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract double getOppervlakte();

    public abstract double getOmtrek();

    public static int getTeller() {
        return teller;
    }
}
