package controller;

import model.Episode;
import model.Season;
import model.Series;
import model.Show;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SeasonController {
    Scanner scanner = new Scanner(System.in);
    SeriesController serCon = new SeriesController();
    EpisodeController epiCon = new EpisodeController();

    public SeasonController(){}

    public void seasonOptions(Series series){
        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                    Séries
                               \s
                    1 - Adicionar temporada
                    2 - Atualizar temporada
                    3 - Remover temporada
                    4 - Voltar para o menu de série
                    5 - Voltar ao menu principal
                               \s
                    Sua opção:
                   \s""");
            switch (scanner.nextInt()) {
                case 1 -> {
                    addSeason(series);
                    validInput = true;
                    seasonOptions(series);
                }
                case 2 -> {
                    updateSeason(series);
                    validInput = true;
                    seasonOptions(series);
                }
                case 3 -> {
                    removeSeason(series);
                    validInput = true;
                    seasonOptions(series);
                }
                case 4 ->{
                    serCon.seriesOptions();
                    validInput = true;
                }
                case 5 -> {
                    //método do menu principal
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }

    public void addSeason(Series series){
        boolean validInput = false;
        int episodeNum = 0;
        String title;
        String preview;
        List<Episode> newEpisodeList = new ArrayList<>();

        while (!validInput) {
            try {
                System.out.println("Quantos episódios a série terá: ");
                episodeNum = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Número inválido, por favor, tente novamente\n");
            }
        }

        for (int i = 0; i < episodeNum; i++) {
            newEpisodeList.add(epiCon.makeEpisode());
        }

        series.getSeasonList().add(new Season(newEpisodeList));
        System.out.println("Temporada adicionada com sucesso");
    }

    public void removeSeason(Series series){
        boolean validInput = false;
        int seasonSize = series.getSeasonList().size();

        while (!validInput){
            System.out.println("Remove season");
            for (int i = 0; i < seasonSize; i++) {
                System.out.println("Season " + (i+1));
            }
            System.out.println("Your option: ");

            int option = scanner.nextInt();

            if(option > 0 && option <= seasonSize){
                series.getSeasonList().remove(option);
                System.out.println("Temporada: " + option + " removida com sucesso\n");
                serCon.updateOptions(series); //get back to series interface
                validInput = true;
            }
            else{
                System.out.println("Invalid option, please try again\n");
            }
        }
    }

    public void updateSeason(Series series){
        boolean validInput = false;
        int seasonSize = series.getSeasonList().size();

        while (!validInput){
            System.out.println("Atualizar temporada");
            for (int i = 0; i < seasonSize; i++) {
                System.out.println("Temporada " + (i+1));
            }
            System.out.println("Sua opção: ");

            int option = scanner.nextInt() - 1;

            if(option >= 0 && option < seasonSize){
                epiCon.episodeOptions(series, option);
                System.out.println("Temporada atualizada com sucesso\n");
                serCon.updateOptions(series); //get back to series interface
                validInput = true;
            }
            else{
                System.out.println("Opção inválida, por favor, tente novamente\n");
            }
        }
    }
}
