package controller;

import model.Episode;
import model.Series;

import java.util.List;
import java.util.Scanner;

public class EpisodeController {
    Scanner scanner = new Scanner(System.in);
    SeasonController seaCon = new SeasonController();

    public void episodeOptions(Series series, int seasonIndex){
        boolean validInput = false;
        List<Episode> episodeList = series.getSeasonList().get(seasonIndex).getEpisodeList();

        while (!validInput) {
            System.out.println("""
                    Séries
                               \s
                    1 - Adicionar episódio
                    2 - Atualizar episódio
                    3 - Remover episódio
                    4 - Voltar para o menu de temporada
                    5 - Voltar ao menu principal
                               \s
                    Sua opção:
                   \s""");
            switch (scanner.nextInt()) {
                case 1 -> {
                    addEpisode(episodeList);
                    validInput = true;
                    episodeOptions(series, seasonIndex);
                }
                case 2 -> {
                    int episodeIndex = getEpisodeIndex(episodeList);
                    updateOptions(series, episodeList, episodeIndex);
                    validInput = true;
                    episodeOptions(series, seasonIndex);
                }
                case 3 -> {
                    int episodeIndex = getEpisodeIndex(episodeList);
                    removeEpisode(episodeList, episodeIndex);
                    validInput = true;
                    episodeOptions(series, seasonIndex);
                }
                case 4 ->{
                    seaCon.seasonOptions(series);
                    validInput = true;
                }
                case 5 -> {
                    //método do menu principal
                }
                default -> System.out.println("Opção inválida, por favor, tente novamente");
            }
        }
    }

    public void printEpisodes(List<Episode> episodeList){
        int seriesSize = episodeList.size();

        System.out.println("Episódios disponíveis\n");
        for (int i = 0; i < seriesSize; i++) {
            System.out.println((i + 1) + " - " + episodeList.get(i).getTitle());
        }
    }

    public int getEpisodeIndex(List<Episode> episodeList){
        boolean validInput = false;
        int episodesSize = episodeList.size();
        int episodeOption = 0;

        while(!validInput) {
            printEpisodes(episodeList);
            System.out.println("Sua opção");
            int option = scanner.nextInt() - 1;

            if (option > 0 && option <= episodesSize) {
                episodeOption = option - 1;
                validInput = true;
            }
            else {
                System.out.println("Opção inválida, por favor, tente novamente\n");
            }
        }

        return episodeOption;
    }

    public void addEpisode(List<Episode> episodeList){
        Episode newEpisode = makeEpisode();
        episodeList.add(newEpisode);
    }

    public void updateOptions(Series series, List<Episode> episodeList, int episodeIndex){
        boolean validInput = false;

        while (!validInput) {
            System.out.println("""
                    Atualizar Episódio
                               \s
                    1 - Título
                    2 - Resumo
                    3 - Título e resumo
                               \s
                    Sua opção:
                   \s""");
            switch (scanner.nextInt()) {
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
                    seaCon.seasonOptions(series);
                    validInput = true;
                }
                default -> System.out.println("Invalid option, try again please");
            }
        }
    }

    public void updateEpisode(List<Episode> episodeList, int episodeIndex){
        updateTitle(episodeList, episodeIndex);
        updatePreview(episodeList, episodeIndex);
    }

    public void updateTitle(List<Episode> episodeList, int episodeIndex){
        System.out.println("Digite o título do episódio : ");
        episodeList.get(episodeIndex).setTitle(scanner.next());
    }

    public void updatePreview(List<Episode> episodeList, int episodeIndex){
        System.out.println("Digite o resumo do episódio : ");
        episodeList.get(episodeIndex).setPreview(scanner.next());
    }

    public void removeEpisode(List<Episode> episodeList, int episodeIndex){
        episodeList.remove(episodeIndex);
        System.out.println("Episódio removido com sucesso\n");
    }

    public Episode makeEpisode(){
        String title = "";
        String preview = "";

        System.out.println("Digite o título do episódio : ");
        title = scanner.next();
        System.out.println("Digite o resumo do episódio : ");
        preview = scanner.next();

        return new Episode(title, preview);
    }
}
