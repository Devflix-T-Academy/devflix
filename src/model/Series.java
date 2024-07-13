package model;

import model.enums.Genre;
import model.interfaces.Media;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Series extends Media {
    List<Season> seasonList;

    public Series(String title, LocalDate date, Genre genre) {
        super(title, date, genre);
        this.seasonList = new ArrayList<>();
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }

    public void addSeason(Season season) {
        this.seasonList.add(season);
    }

}
