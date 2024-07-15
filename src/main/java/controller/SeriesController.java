package controller;

import controller.interfaces.MediaController;
import model.Series;
import model.enums.Genre;
import services.series.EpisodeService;
import services.series.SeasonService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class SeriesController implements MediaController {
    public static List<Series> seriesList = new ArrayList<>();;

    public SeriesController() {
    }

    public void addSeries(Series series) {seriesList.add(series);}

    public void removeSeries(Series series) {
        if (series != null && seriesList.remove(series)) {
            System.out.println("Série: " + series.getTitle() + " removida");
        }
    }

    public static List<Series> getSeriesList() {
        return seriesList;
    }

    public Series getSeries(int id) {
      return  seriesList.stream()
              .filter(series -> series.getId() == id)
              .findFirst().orElse(null);
    }

    public Series getSeries(String title) {
        return  seriesList.stream()
                .filter(series -> series.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public void printSeries() {
        System.out.println("\nSéries disponíveis\n");
        SeriesController.seriesList.forEach(series -> System.out.println(series.getId() + " - " + series.getTitle()));
    }

    public boolean existTitle(String title) {
        Optional<Series> seriesWithExistentTitle = seriesList.stream()
                .filter(series -> series.getTitle().equals(title))
                .findFirst();

        return seriesWithExistentTitle.isPresent();
    }

    public void updateTitle(Series series, String title){
        series.setTitle(title);
    }

    public LocalDate formatDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean seriesListIsEmpty(){
        return SeriesController.seriesList.isEmpty();
    }

    @Override
    public List<Series> getMostWatched() {
        return List.of();
    }

    @Override
    public List<Series> getBestRated() {
        return List.of();
    }
}