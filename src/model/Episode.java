package model.interfaces.series;

public  class Episode {
    private String title;
    private String preview;
    int number;

    public Episode(String title, String preview, int number) {
        this.title = title;
        this.preview = preview;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
