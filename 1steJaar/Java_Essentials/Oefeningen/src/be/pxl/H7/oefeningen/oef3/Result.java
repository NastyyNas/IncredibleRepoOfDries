package be.pxl.H7.oefeningen.oef3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

public class Result {
    public static final DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String name;
    private LocalDateTime timestamp;
    private double result;


    public Result(String name, LocalDateTime timestamp, double result){
        this.name = name;
        this.timestamp = timestamp;
        this.result = result;
    }

    public Result(String[] highscore){
        this(highscore[1], LocalDateTime.parse(highscore[0], parser), Double.parseDouble(highscore[2]));
    }

    public double getResult() {
        return result;
    }
    public String toFile(){
        return parser.format(timestamp) + "#" + name + "#" + result;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d uren geleden)", formatter.format(timestamp), name, ChronoUnit.HOURS.between(timestamp, LocalDateTime.now()));
    }
}
