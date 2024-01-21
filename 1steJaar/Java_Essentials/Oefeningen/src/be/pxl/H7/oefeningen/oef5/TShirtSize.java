package be.pxl.H7.oefeningen.oef5;

public enum TShirtSize {
    XS(86), S(94), M(102), L(110), XL(118), XXL(128), XXXL(140);

    private int waarde;
    TShirtSize(int waarde){
        this.waarde = waarde;
    }
}
