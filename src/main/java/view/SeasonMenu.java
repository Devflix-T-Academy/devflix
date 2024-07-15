package view;

import model.Episode;
import model.Season;
import model.Series;
import services.series.SeasonService;
import util.ScanValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeasonMenu {
    int seasonInt = 0;

    public void show(Series series) {
        SeriesMenu seriesMenu = new SeriesMenu();
        SeasonService seasonService = new SeasonService();

        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                    \s
                     Temporadas
                                \s
                     1 - Adicionar temporada
                     2 - Atualizar temporada
                     3 - Remover temporada
                     4 - Detalhar temporada
                     5 - Voltar para o menu de série
                                \s
                     Sua opção:
                    \s""");
            switch (ScanValidation.getValidIntInput(new Scanner(System.in))) {
                case 1 -> {
                    newSeasonOption(series);
                    validInput = true;
                    show(series);
                }
                case 2 -> {
                    if (seasonService.seasonListIsEmpty(series.getSeasonList())) {
                        System.out.println("Lista de temporadas vazia!\n");
                    }
                    else {
                        pickASeason(series.getSeasonList());
                        updateSeasonOption(series);
                        validInput = true;
                        show(series);
                    }
                }
                case 3 -> {
                    if (seasonService.seasonListIsEmpty(series.getSeasonList())) {
                        System.out.println("Lista de temporadas vazia!\n");
                    }
                    else {
                        Season season = pickASeason(series.getSeasonList());
                        removeSeasonOption(series, season);
                        validInput = true;
                        show(series);
                    }
                }
                case 4 -> {
                    if (seasonService.seasonListIsEmpty(series.getSeasonList())) {
                        System.out.println("Lista de temporadas vazia!\n");
                    }
                    else {
                        Season season = pickASeason(series.getSeasonList());
                        printSeasonDetails(season);
                        validInput = true;
                        show(series);
                    }
                }
                case 5 -> {
                    seriesMenu.show();
                    validInput = true;
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }

    public void printSeasonDetails(Season season) {
        SeasonService seasonService = new SeasonService();

        System.out.println("Temporada - " + seasonInt);
        seasonService.printSeason(season);
    }

    public Season pickASeason(List <Season> seasonList){
        SeasonService seasonService = new SeasonService();

        System.out.println("Selecione uma temporada");
        seasonService.printSeasons(seasonList);
        System.out.println("Sua opção");
        int option = ScanValidation.getValidIntBetweenInput(new Scanner(System.in), 1, seasonList.size());
        seasonInt = option;

        return seasonService.getSeason(seasonList , (option-1));
    }

    public void newSeasonOption(Series series) {
        EpisodeMenu episodeMenu = new EpisodeMenu();
        List<Episode> newEpisodeList = new ArrayList<>();

        System.out.println("Quantos episódios a temporada terá: ");
        int episodeNum = ScanValidation.getValidIntInput(new Scanner(System.in));

        for (int i = 0; i < episodeNum; i++) {
            newEpisodeList.add(episodeMenu.makeEpisode(i + 1));
        }

        SeasonService seasonService = new SeasonService();
        seasonService.addSeason(series, newEpisodeList);
        System.out.println("Temporada adicionada com sucesso");
    }

    public void updateSeasonOption(Series series) {
        EpisodeMenu episodeMenu = new EpisodeMenu();

        episodeMenu.show(series, (seasonInt-1));
    }

    public void removeSeasonOption(Series series, Season season) {
        SeasonService seasonService = new SeasonService();
        seasonService.removeSeason(series, season);
        System.out.println("Temporada removida com sucesso\n");
    }
}
