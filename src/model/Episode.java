package model;

public class Episode {
    private String title;
    private String preview;

    public Episode(String title, String preview) {
        this.title = title;
        this.preview = preview;
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
}
