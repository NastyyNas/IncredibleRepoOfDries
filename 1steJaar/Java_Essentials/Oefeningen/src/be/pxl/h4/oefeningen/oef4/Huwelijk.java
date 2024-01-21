package be.pxl.h4.oefeningen.oef4;

import java.time.LocalDate;

public class Huwelijk {
    private Persoon persoon1;
    private Persoon persoon2;
    private LocalDate huwelijksDatum;

    public Huwelijk(int dag, int maand, int jaar, Persoon persoon1, Persoon persoon2){
        this.huwelijksDatum = LocalDate.of(jaar, maand, dag);
        this.persoon1 = persoon1;
        this.persoon2 = persoon2;
        persoon2.setAdres(persoon1.getAdres());
        System.out.printf("%s %s en %s %s zijn gehuwd op %td %<tB %<tY", persoon1.getVoornaam(), persoon1.getNaam(), persoon2.getVoornaam(), persoon2.getNaam(), huwelijksDatum);
    }


}
