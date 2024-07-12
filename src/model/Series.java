package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Series extends Show{
    List<Season> seasonList;

    public Series(int id, String title, LocalDate date, String genre) {
        super(id, title, date, genre);
        this.seasonList = new ArrayList<>();
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }

    public void addSeason(Season season) {
        this.seasonList.add(season);
    }

}
