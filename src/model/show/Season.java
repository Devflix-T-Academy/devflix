package model.show;

import java.util.ArrayList;
import java.util.List;

public class Season {
    private List<Episode> episodeList;

    public Season() {
        this.episodeList = new ArrayList<>();
    }

    public void addEpisode(Episode episode){
        this.episodeList.add(episode);
    }
}
