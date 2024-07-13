package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Series extends Show{
    private List<Season> seasonList;

    public Series(String title, LocalDate date, String genre) {
        super(title, date, genre);
        this.seasonList = new ArrayList<>();
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }

    public void addSeason(Season season) {
        this.seasonList.add(season);
    }

    public String toString(int index) {
        return "ID - " + (index+1) +
                "\nTítulo - " + title +
                "\nData de lançamento - " + date +
                "\nGênero - " + genre ;
    }
}
