package be.pxl.oefenexamen;
/* naam: */
public abstract class Persoon{
    private String id;
    private String naam;
    public Persoon(String id, String naam){
        StringBuilder nieuwId = new StringBuilder();
        for(int i = 0; i < id.length(); i++){
            if(Character.isLetter(id.charAt(i)) || Character.isDigit(id.charAt(i))){
                if(nieuwId.length() < 3){
                    nieuwId.append(id.charAt(i));
                }
            }
        }
        this.id = nieuwId.toString();
        this.naam = naam;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + naam;
    }

    public String getNaam() {
        return naam;
    }

    public boolean equals(Object object){
        if(object instanceof Persoon){
            return true;
        }
        return false;
    }


}

