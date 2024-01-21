package be.pxl.h8.oefeningen.extraOefening;

import java.time.LocalDate;

public interface Verkoopbaar {
    double MINPRIJSPERM2 = 83;
    void wijsEenNotarisToe(String naam, LocalDate datum);
    void doeEenBod(double prijs, LocalDate datum);
}
