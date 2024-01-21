package be.pxl.H7.opdrachten;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Opdracht7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("geef je geboortedatum in via d-M-yyyy");
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate geboortedatum = LocalDate.parse(scanner.nextLine(), parser);
        Period levensduur = Period.between(geboortedatum, LocalDate.now());
        System.out.printf("Je bent %d jaren %d maanden %d dagen oud. %n", levensduur.getYears(), levensduur.getMonths(), levensduur.getDays());
        System.out.printf("Of je bent %d maanden oud%n", ChronoUnit.MONTHS.between(geboortedatum, LocalDate.now()));

        LocalDate verjaardag = geboortedatum.withYear(LocalDate.now().getYear());
    }
}
