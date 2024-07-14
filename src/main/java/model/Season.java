package model;

import org.w3c.dom.css.Counter;
import util.CounterIds;

import java.util.ArrayList;
import java.util.List;

public  class Season {
    private List<Episode> episodeList;
    private Long id;
    public Season(List<Episode> episodeList) {
        this.episodeList = episodeList;
        this.id = CounterIds.getNextEpisodeId();
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

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
