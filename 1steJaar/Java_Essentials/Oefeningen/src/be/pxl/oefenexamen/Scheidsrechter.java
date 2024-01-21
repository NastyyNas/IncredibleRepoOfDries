package be.pxl.oefenexamen;
/* naam: */
public class Scheidsrechter extends Persoon{
    public Scheidsrechter(String id, String naam){
        super(id, naam);
    }

    @Override
    public String toString() {
        return super.toString() + "<ref>";
    }
}

