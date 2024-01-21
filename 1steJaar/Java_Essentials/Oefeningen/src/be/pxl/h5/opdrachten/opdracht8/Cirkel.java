package be.pxl.h5.opdrachten.opdracht8;

import java.util.Objects;

public class Cirkel extends GrafischElement{
    private double straal;

    public Cirkel(int x, int y, double straal) {
        super(x, y);
        this.straal = straal;
    }


    @Override
    public double getOppervlakte() {
        return Math.PI * straal * straal;
    }

    @Override
    public double getOmtrek() {
        return 2 * Math.PI * straal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cirkel cirkel = (Cirkel) o;
        return cirkel.straal == straal && cirkel.getX() == getX() && cirkel.getY() == getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(straal, getX(), getY());
    }
}
