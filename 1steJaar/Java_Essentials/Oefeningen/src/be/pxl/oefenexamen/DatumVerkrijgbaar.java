package be.pxl.oefenexamen;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DatumVerkrijgbaar{
    long berekenAantalMinutenNa(LocalDateTime Datum);
}
