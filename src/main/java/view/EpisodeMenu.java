package view;

import model.Episode;
import model.Series;
import services.series.EpisodeService;
import util.ScanValidation;

import java.util.List;
import java.util.Scanner;

public class EpisodeMenu {
    EpisodeService episodeService = new EpisodeService();
    int episodeInt = 0;

    public void show(Series series, int seasonId){
        SeasonMenu seasonMenu = new SeasonMenu();
        List<Episode> episodeList = series.getSeasonList().get(seasonId).getEpisodeList();

        boolean active = true;

        while (active) {
            System.out.println("""
                    Episódios
                               \s
                    1 - Adicionar episódio
                    2 - Atualizar episódio
                    3 - Remover episódio
                    4 - Detalhar episódio
                    5 - Voltar para o menu de temporada
                               \s
                    Sua opção:
                   \s""");
            switch (ScanValidation.getValidIntInput(new Scanner(System.in))) {
                case 1 -> {
                    newEpisodeOption(episodeList);
                    active = false;
                    show(series, seasonId);
                }
                case 2 -> {
                    if (episodeList.isEmpty()) {
                        System.out.println("Lista de episódios vazia!\n");
                    }
                    else {
                        updateOptions(series, episodeList, seasonId);
                        active = false;
                        show(series, seasonId);
                    }
                }
                case 3 -> {
                    if (episodeList.isEmpty()) {
                        System.out.println("Lista de episódios vazia!\n");
                    }
                    else {
                        removeEpisodeOption(episodeList);
                        active = false;
                        show(series, seasonId);
                    }
                }
                case 4 -> {
                    if (episodeList.isEmpty()) {
                        System.out.println("Lista de episódios vazia!\n");
                    }
                    else {
                        printEpisodeDetails(episodeList);
                    }
                }
                case 5 ->{
                    seasonMenu.show(series);
                    active = false;
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }

    public void printEpisodeDetails(List <Episode> episodeList) {
        System.out.println(pickAEpisode(episodeList).toString());
    }

    public Episode pickAEpisode(List <Episode> episodeList){
        EpisodeService episodeService = new EpisodeService();

        System.out.println("Selecione um episódio");
        episodeService.printEpisodes(episodeList);
        System.out.println("Sua opção");
        int option = ScanValidation.getValidIntBetweenInput(new Scanner(System.in), 1, episodeList.size());
        episodeInt = option;

        return episodeService.getEpisode(episodeList, (option-1));
    }

    public void newEpisodeOption(List<Episode> episodeList){
        Episode newEpisode = makeEpisode(episodeList.size() + 1);
        episodeService.addEpisode(episodeList, newEpisode);
    }

    public Episode makeEpisode(Integer number){
        String title = "";
        String preview = "";

        System.out.println("Digite o título do episódio : ");
        title = ScanValidation.getValidStringInput(new Scanner(System.in), 1);
        System.out.println("Digite o resumo do episódio : ");
        preview = ScanValidation.getValidStringInput(new Scanner(System.in), 25);
        return new Episode(title, preview, number);
    }

    public int getEpisodeIndex(List<Episode> episodeList){
        int episodesSize = episodeList.size();
        EpisodeService episodeService = new EpisodeService();

        episodeService.printEpisodes(episodeList);
        System.out.println("Sua opção");
        int option = ScanValidation.getValidIntBetweenInput(new Scanner(System.in), 1, episodesSize);

        return (option-1);
    }

    public void removeEpisodeOption(List<Episode> episodeList){
        int episodeIndex = getEpisodeIndex(episodeList);
        episodeService.removeEpisode(episodeList, episodeIndex);
        System.out.println("Episódio removido com sucesso\n");
    }

    public void updateEpisode(List<Episode> episodeList, int episodeIndex){
        updateTitle(episodeList, episodeIndex);
        updatePreview(episodeList, episodeIndex);
    }

    public void updateTitle(List<Episode> episodeList, int episodeIndex){
        System.out.println("Digite o título do episódio : ");
        String title = ScanValidation.getValidStringInput(new Scanner(System.in), 1);
        episodeService.updateTitle(episodeList, episodeIndex, title);
    }

    public void updatePreview(List<Episode> episodeList, int episodeIndex){
        System.out.println("Digite o resumo do episódio : ");
        String preview = ScanValidation.getValidStringInput(new Scanner(System.in), 25);
        episodeService.updatePreview(episodeList, episodeIndex, preview);
    }

    public void updateOptions(Series series, List<Episode> episodeList, int seasonId){
        int episodeIndex = getEpisodeIndex(episodeList);

        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                   \s
                    Atualizar Episódio
                               \s
                    1 - Título
                    2 - Resumo
                    3 - Título e resumo
                    4 - Detalhar episódio
                    5 - Voltar ao menu de episódios
                               \s
                    Sua opção:
                   \s""");
            switch (ScanValidation.getValidIntInput(new Scanner(System.in))) {
                case 1 -> {
                    updateTitle(episodeList, episodeIndex);
                    validInput = true;
                }
                case 2 -> {
                    updatePreview(episodeList, episodeIndex);
                    validInput = true;
                }
                case 3 -> {
                    updateEpisode(episodeList, episodeIndex);
                    validInput = true;
                }
                case 4 -> {
                    show(series, seasonId);
                    validInput = true;
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }
}
