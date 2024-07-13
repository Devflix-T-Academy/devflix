package model;

import java.util.ArrayList;
import java.util.List;

public  class Season {
    private List<Episode> episodeList;

    public Season(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public Season() {
        this.episodeList = new ArrayList<>();
    }

    public void addEpisode(Episode episode){
        this.episodeList.add(episode);
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }
}
