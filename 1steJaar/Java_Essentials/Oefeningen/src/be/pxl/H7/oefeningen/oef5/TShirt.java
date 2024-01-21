package be.pxl.H7.oefeningen.oef5;

public class TShirt {
    private TShirtSize size;
    private Color color;
    private int amountOrdered;

    public TShirt(TShirtSize size, Color color){
        this.size = size;
        this.color = color;
    }

    public void setAmountOrdered(int amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public TShirtSize getSize() {
        return size;
    }

    public void setSize(TShirtSize size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }
}
