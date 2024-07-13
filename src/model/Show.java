package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public abstract class Show {
    protected String title;
    protected LocalDate date;
    protected String genre;
    protected static List<String> genreList = Arrays.asList("Terror", "Drama", "Romance", "Suspense", "Ação", "Comédia");

    public Show(String title, LocalDate date, String genre) {
        this.title = title;
        this.date = date;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static List<String> getGenreList() {
        return genreList;
    }

    public static void addGenre(String genre) {
        Show.genreList.add(genre);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
