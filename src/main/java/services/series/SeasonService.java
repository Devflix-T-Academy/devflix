package services.series;

import model.Episode;
import model.Season;
import model.Series;

import java.util.List;

public class SeasonService {
    public SeasonService() {
    }

    public int seasonListSize(List<Season> seasonList){
        return seasonList.size();
    }

    public void printSeasons(List<Season> seasonList){
        int seasonListSize = seasonListSize(seasonList);

        for (int i = 0; i < seasonListSize; i++) {
            System.out.println("Temporada " + (i + 1));
        }
    }

    public void printSeason(Season season){
        int episodeListSize = season.getEpisodeList().size();
        for (int i = 0; i < episodeListSize; i++) {
            System.out.println("Episódio - " + (i+1) +
                    " Título - " + season.getEpisodeList().get(i).getTitle());
        }
    }

    public Season getSeason(List<Season> seasonList, int id) {
        return seasonList.get(id);
    }

    public boolean seasonListIsEmpty(List<Season> seasonList){
        return seasonList.isEmpty();
    }

    public void addSeason(Series series, List<Episode> episodeList){
        series.getSeasonList().add(new Season(episodeList));
    }

    public void removeSeason(Series series, Season season){
        series.getSeasonList().remove(season);
    }

}
