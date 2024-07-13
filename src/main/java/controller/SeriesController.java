package controller;

import controller.interfaces.MediaController;
import model.Series;
import services.series.EpisodeService;
import services.series.SeasonService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SeriesController implements MediaController {
    public static List<Series> seriesList;
    Scanner scanner = new Scanner(System.in);
    private EpisodeService episodeService = new EpisodeService();

    public SeriesController() {

    }

    public void addSeries(Series series) {
        seriesList.add(series);
        System.out.println("Series: " + series.getTitle() + " added successfully");
    }

    public void removeSeries(int id) {
        Series serie = getSeries(id);
        if (serie == null) {
            System.out.println("Series with id: " + id + " not found");
            return;
        }
        this.seriesList.remove(serie);
        System.out.println("Series: " + serie.getTitle() + " removed");

    }


    public void updateSeries(int id) {
        Series serie = getSeries(id);

        if (serie == null) {
            System.out.println("Series with id: " + id + " not found");
            return;
        }
        updateShowOptions(serie);
    }


    public Series getSeries(int id) {
        return seriesList.stream()
                .filter(serie -> serie.getId() == id)
                .findFirst().orElse(null);
    }

    public void updateShowOptions(Series series) {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                     Update series
                                \s
                     1 - Title
                     2 - Release date
                     3 - Seasons
                                \s
                     Your option:
                    \s""");
            switch (scanner.nextInt()) {
                case 1 -> {
                    updateTitle(series);
                    validInput = true;
                }
                case 2 -> {
                    updateDate(series);
                    validInput = true;
                }
                case 3 -> {
                    updateSeasons(series);
                    validInput = true;
                }
                default -> System.out.println("Invalid option, try again please");
            }
        }
    }

    public void updateTitle(Series series) {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the new title: ");
            String newTitle = scanner.next();

            if (!newTitle.isEmpty() && existTitle(series.getTitle())) {
                series.setTitle(newTitle);
                System.out.println("New title added successfully");
                validInput = true;
            } else if (!existTitle(series.getTitle())) {
                System.out.println("There is already a series with that title, please try again");
            } else {
                System.out.println("Invalid title, please try again");
            }
        }
    }

    public boolean existTitle(String title) {
        Optional<Series> seriesWithExistentTitle = seriesList.stream()
                .filter(series -> series.getTitle().equals(title))
                .findFirst();

        return seriesWithExistentTitle.isEmpty();
    }

    public void updateDate(Series series) {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the new date with the format yyyy-mm-dd: ");
            try {
                LocalDate date = LocalDate.parse(scanner.next());
                series.setDate(date);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again");
            }
        }
    }

    public void updateSeasons(Series series) {
        SeasonService seasonController = new SeasonService();
        seasonController.doUpdateSeason(series);
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