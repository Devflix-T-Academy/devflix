package model;

import model.enums.Genre;
import model.interfaces.Media;
import util.CounterIds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Series extends Media {
    List<Season> seasonList;

    public Series(String title, LocalDate date, Genre genre) {
        super(CounterIds.getNextSeriesId(), title, date, genre);
        this.seasonList = new ArrayList<>();
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }

    public void addSeason(Season season) {
        this.seasonList.add(season);
    }

    public void setSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;
    }

    @Override
    public String toString() {
        return "ID - " + this.getId() +
                "\nTítulo - " + this.getTitle() +
                "\nData de lançamento - " + this.getDate() +
                "\nGênero - " + this.getGenre().getGenreName() ;
    }
}
