package view;

import controller.SeriesController;
import model.Series;
import model.enums.Genre;
import util.ScanValidation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SeriesMenu {
    SeriesController seriesController = new SeriesController();

    public void adminMenu() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                    \s
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
            switch (ScanValidation.getValidIntInput(new Scanner(System.in))) {
                case 1 -> {
                    newSeriesOption();
                    validInput = true;
                    adminMenu();
                }
                case 2 -> {
                    if (seriesController.seriesListIsEmpty()) {
                        System.out.println("Lista de séries vazia!\n");
                    }
                    else{
                        Series pickedSeries = pickASeries();
                        updateOptions(pickedSeries);
                        validInput = true;
                    }
                    adminMenu();
                }
                case 3 -> {
                    if (seriesController.seriesListIsEmpty()) {
                        System.out.println("Lista de séries vazia!\n");
                    }
                    else {
                        Series pickedSeries = pickASeries();
                        seriesController.removeSeries(pickedSeries);
                        validInput = true;
                    }
                    adminMenu();
                }
                case 4 -> {
                    if (seriesController.seriesListIsEmpty()) {
                        System.out.println("Lista de séries vazia\n!");
                    }
                    else {
                        printSeriesDetails();
                    }
                }
                case 5 -> {
                    DevflixMenu.mainMenu();
                    validInput = true;
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }

    public void newSeriesOption(){
        boolean validInput = false;
        String title = "";
        LocalDate date = null;
        int genre = 0;

        while (!validInput) {
            System.out.println("Digite o título da série: ");
            title = ScanValidation.getValidStringInput(new Scanner(System.in), 5);
            date = newDate();
            genre = genreOptions();
            validInput = true;
        }

        seriesController.addSeries(new Series(title, date, Genre.values()[genre]));
        System.out.println("Série: " + title + " adicionada com sucesso");
    }

    public int genreOptions(){
        List<String> genreList = Arrays.stream(Genre.values()).map(Genre::getGenreName).toList();
        int genreSize = genreList.size();

        System.out.println("\nGeneros disponíveis");
        for (int i = 0; i < genreSize; i++) {
            System.out.println((i + 1) + " - " + genreList.get(i));
        }

        System.out.println("\nSua opção: ");
        return ScanValidation.getValidIntBetweenInput(new Scanner(System.in), 1, genreSize);
    }

    public void printSeriesDetails() {System.out.println("\n" + pickASeries().toString());}

    public Series pickASeries(){
        System.out.println("Selecione uma série");
        seriesController.printSeries();
        System.out.println("Sua opção");
        int option = ScanValidation.getValidIntInput(new Scanner(System.in));

        return seriesController.getSeries(option);
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
                     5 - Voltar ao menu de séries
                                \s
                     Sua opção:
                    \s""");
                switch (ScanValidation.getValidIntInput(new Scanner(System.in))) {
                    case 1 -> {
                        updateTitleOption(series);
                        validInput = true;
                    }
                    case 2 -> {
                        updateDateOption(series);
                        validInput = true;
                    }
                    case 3 -> {
                        updateSeriesOption(series);
                        validInput = true;
                    }
                    case 4 -> {
                        SeasonMenu seasonMenu = new SeasonMenu();
                        seasonMenu.show(series);
                        validInput = true;
                    }
                    case 5 -> {
                        adminMenu();
                        validInput = true;
                    }
                    default -> System.out.println("Opção inválida, por favor, tente novamente");
                }
            }

    }

    public void updateTitleOption(Series series) {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Digite o novo título: ");
            String newTitle = ScanValidation.getValidStringInput(new Scanner(System.in), 4);

            if (!seriesController.existTitle(newTitle)) {
                seriesController.updateTitle(series, newTitle);
                System.out.println("Novo título adicionado com sucesso!\n");
                validInput = true;
            }
            else{
                System.out.println("Já existe uma série com esse título, por favor, tente novamente\n");
            }
        }
    }

    public void updateDateOption(Series series) {series.setDate(newDate());}

    public LocalDate newDate(){
        System.out.println("Digite o dia de lançamento da série: ");
        String day = ScanValidation.getValidDayInput(new Scanner(System.in));
        System.out.println("Digite o mês de lançamento da série: ");
        String month = ScanValidation.getValidMonthInput(new Scanner(System.in));
        System.out.println("Digite o ano de lançamento da série: ");
        String year = ScanValidation.getValidYearInput(new Scanner(System.in));

        return seriesController.formatDate((day + month + year));
    }

    public void updateSeriesOption(Series series) {
        updateTitleOption(series);
        updateDateOption(series);
    }

}
