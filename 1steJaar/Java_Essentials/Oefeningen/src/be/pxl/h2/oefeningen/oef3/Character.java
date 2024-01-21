package be.pxl.h2.oefeningen.oef3;

import java.util.Arrays;

public class Character {
    private static int count;
    private static final int MAX_TITLES = 3;
    private String firstName;
    private String lastName;
    private String nickName;
    private String house;
    private int firstSeason;
    private int lastSeason;
    private int episodes;
    private String portrayedBy;
    private int numberOfTitles;
    private String[] titles = new String[MAX_TITLES];

    public Character(String firstName, String lastName, String house, String portrayedby){
        this(firstName, lastName, "", house, 0, 0, 0, portrayedby);
    }
    public Character(String firstName, String lastName, String nickName, String house, String portrayedby){
        this.firstName = firstName;
        this.lastName = lastName;
        this.house = house;
        this.portrayedBy = portrayedby;
        this.nickName = nickName;
        count++;
    }
    public Character(String firstName, String lastName, String nickName, String house, int firstSeason, int lastSeason, int episodes, String portrayedby){
        this.firstName = firstName;
        this.lastName = lastName;
        this.house = house;
        this.portrayedBy = portrayedby;
        this.nickName = nickName;
        this.firstSeason = firstSeason;
        this.lastSeason = lastSeason;
        this.episodes = episodes;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public void setData(int firstSeason, int lastSeason, int episodes){
        this.firstSeason = firstSeason;
        this.lastSeason = lastSeason;
        this.episodes = episodes;
    }

    public void addTitle(String title){
        if (numberOfTitles < MAX_TITLES){
            this.titles[numberOfTitles] = title;
            numberOfTitles++;
        }
    }

    @Override
    public String toString() {
        String output = String.format("%s \"%s\" %s of house %s \n", firstName, nickName, lastName, house);
        for(String title: titles){
            if (title != null)
            output += String.format("*** \"%s\" \n", title);
        }
        output += String.format("Played by: %s in season %d-%d (%d episodes)", portrayedBy, firstSeason, lastSeason, episodes);
        return output;
    }
}
