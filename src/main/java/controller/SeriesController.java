package controller;

import controller.interfaces.MediaController;
import model.Series;
import model.enums.Genre;
import services.series.EpisodeService;
import services.series.SeasonService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SeriesController implements MediaController {
    public static List<Series> seriesList;
    Scanner scanner = new Scanner(System.in);
    private SeasonService seasonService = new SeasonService();

    public SeriesController() {
    }

    public void seriesOptions() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("""
                     Séries
                                \s
                     1 - Adicionar série
                     2 - Atualizar série
                     3 - Remover série
                     4 - Detalhar série
                     5 - Voltar ao menu principal
                                \s
                     Sua opção:
                    \s""");
            switch (scanner.nextInt()) {
                case 1 -> {
                    addSeries();
                    validInput = true;
                    seriesOptions();
                }
                case 2 -> {
                    int seriesIndex = getSeriesIndex();
                    updateOptions(seriesList.get(seriesIndex));
                    validInput = true;
                    seriesOptions();
                }
                case 3 -> {
                    int seriesIndex = getSeriesIndex();
                    removeSeries(seriesList.get(seriesIndex));
                    validInput = true;
                    seriesOptions();
                }
                case 4 -> printSeriesDetails();
                case 5 -> {
                    //método do menu principal
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }

    //fazer por paginação
    public void printSeries() {
        int seriesSize = seriesList.size();
        System.out.println("\nSéries disponíveis\n");
        for (int i = 0; i < seriesSize; i++) {
            System.out.println((i + 1) + " - " + seriesList.get(i).getTitle());
        }
    }

    public String genreOptions() {
        List<String> genreList = Arrays.stream(Genre.values()).map(Genre::getGenreName).toList();
        int genreSize = genreList.size();
        String genre = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Generos disponíveis");
            for (int i = 0; i < genreSize; i++) {
                System.out.println((i + 1) + " - " + genreList.get(i));
            }
            System.out.println("\nSua opção: ");
            int option = scanner.nextInt();
            if (option > 0 && option <= genreSize) {
                validInput = true;
                genre = genreList.get(option);
            } else {
                System.out.println("Opção inválida, por favor, tente novamente\n");
            }
        }
        return genre;
    }

    public void printSeriesDetails() {
        printSeries();
        int serieIndex = getSeriesIndex();
        System.out.println(seriesList.get(serieIndex).toString());
    }

    public int getSeriesIndex() {
        boolean validInput = false;
        int seriesSize = seriesList.size();
        int seriesOption = 0;

        while (!validInput) {
            printSeries();
            System.out.println("Sua opção");
            int option = scanner.nextInt() - 1;

            if (option > 0 && option <= seriesSize) {
                seriesOption = option - 1;
                validInput = true;
            } else {
                System.out.println("Opção inválida, por favor, tente novamente\n");
            }
        }

        return seriesOption;
    }

    public void addSeries() {
        boolean validInput = false;
        String title = "";
        LocalDate date = null;
        String genre = "";

        while (!validInput) {
            System.out.println("Digite o título da série: ");
            title = scanner.next();
            System.out.println("Digite a data de lançamento da série no formato aaaa-mm-dd: ");
            try {
                date = LocalDate.parse(scanner.next());
            } catch (DateTimeParseException e) {
                System.out.println("Data no formato inválido, por favor, tente novamente");
            }

            genre = genreOptions();
            validInput = true;
        }

        seriesList.add(new Series(title, date, Genre.valueOf(genre)));
        System.out.println("Série: " + title + " adicionada com sucesso");
    }

    public void updateOptions(Series series) {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                     Atualizar série
                                \s
                     1 - Título
                     2 - Data de lançamento
                     3 - Título e data de lançamento
                     4 - Temporadas
                                \s
                     Sua opção:
                    \s""");
            switch (scanner.nextInt()) {
                case 1 -> {
                    updateDate(series);
                    validInput = true;
                }
                case 3 -> {
                    updateSeries(series);
                    validInput = true;
                }
                case 4 -> {
                    seasonService.seasonOptions(series);
                    validInput = true;
                }
                default -> System.out.println("Invalid option, try again please");
            }
        }
    }

    public void updateSeries(Series series) {
        updateTitle(series);
        updateDate(series);
    }

    public void removeSeries(Series series) {
        if (series != null && seriesList.remove(series)) {
            System.out.println("Série: " + series.getTitle() + " removida");
        }
    }

    public Series getSeries(int id) {
      return  seriesList.stream()
              .filter(serie -> serie.getId() == id)
              .findFirst().orElse(null);
    }

    public boolean existTitle(String title) {
        Optional<Series> seriesWithExistentTitle = seriesList.stream()
                .filter(series -> series.getTitle().equals(title))
                .findFirst();

        return seriesWithExistentTitle.isEmpty();
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
        seriesList.remove(serie);
        System.out.println("Series: " + serie.getTitle() + " removed");

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


    @Override
    public List<Series> getMostWatched() {
        return List.of();
    }

    @Override
    public List<Series> getBestRated() {
        return List.of();
    }
}