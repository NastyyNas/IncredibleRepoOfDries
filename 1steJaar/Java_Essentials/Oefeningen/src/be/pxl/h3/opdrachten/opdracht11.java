package be.pxl.h3.opdrachten;
import java.time.LocalDate;

public class opdracht11 {
    public static void main(String[] args) {
        LocalDate geboortedatum = LocalDate.of(1966, 2, 6);
        System.out.println("A star was born on " + geboortedatum);
        System.out.printf("Day %d of the year %d %n", geboortedatum.getDayOfYear(), geboortedatum.getYear());
        System.out.printf("%s%n", geboortedatum.getDayOfWeek());
        boolean isLeap = geboortedatum.isLeapYear();
        System.out.println("was it a leap year?");
        if (isLeap){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
        LocalDate volgendeVerjaardag = geboortedatum.withYear(LocalDate.now().getYear());
        if (volgendeVerjaardag.isBefore(LocalDate.now())){
            volgendeVerjaardag = volgendeVerjaardag.plusYears(1);
        }
        System.out.println("next anniversary " + volgendeVerjaardag);
        System.out.println(LocalDate.now().compareTo(geboortedatum));

    }
}
