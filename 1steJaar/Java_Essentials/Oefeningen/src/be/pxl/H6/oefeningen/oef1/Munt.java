package be.pxl.H6.oefeningen.oef1;

public enum Munt {
    TWEE(200), EEN(100), VIJFITG(50), TWINTIG(20), TIEN(10), VIJF(5), TWEECENT(2), EENCENT(1);

    private int waarde;

    Munt(int waarde) {
        this.waarde = waarde;
    }

    public int getWaarde() {
        return waarde;
    }
}
