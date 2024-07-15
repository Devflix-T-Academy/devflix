package services.series;

import model.Episode;

import java.util.List;

public class EpisodeService {
    public void addEpisode(List<Episode> episodeList, Episode episode){
        episodeList.add(episode);
    }

    public void updateTitle(List<Episode> episodeList, int episodeIndex, String title){
        episodeList.get(episodeIndex).setTitle(title);
    }

    public void updatePreview(List<Episode> episodeList, int episodeIndex, String preview){
        episodeList.get(episodeIndex).setPreview(preview);
    }

    public void removeEpisode(List<Episode> episodeList, int episodeIndex){
        episodeList.remove(episodeIndex);
    }

    public void printEpisodes(List<Episode> episodeList){
        int episodeListSize = episodeList.size();

        for (int i = 0; i < episodeListSize; i++) {
            System.out.println("Episódio " + (i + 1) + " Título - " + episodeList.get(i).getTitle());
        }
    }

    public Episode getEpisode(List<Episode> episodeList, int episodeIndex){return episodeList.get(episodeIndex);}
}
