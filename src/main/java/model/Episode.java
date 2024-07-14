package model;

import util.CounterIds;

public  class Episode {
    private String title;
    private String preview;
    private Integer number;
    private Long id;

    public Episode(String title, String preview, Integer number) {
        this.title = title;
        this.preview = preview;
        this.number = number;
        this.id = CounterIds.getNextEpisodeId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
