package model.show;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public abstract class Show {
    static int id;
    protected String title;
    protected LocalDate date;
    protected String genre;
    private int watched;
    protected static List<String> genreList = Arrays.asList("Terror", "Drama", "Romance", "Suspense", "Ação", "Comédia");

    public Show(int id, String title, LocalDate date, String genre) {
        Show.id = id + 1;
        this.title = title;
        this.date = date;
        this.genre = genre;
        this.watched = 0;
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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Show.id = id;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public static void setGenreList(List<String> genreList) {
        Show.genreList = genreList;
    }

    public void displayDetails() {
        System.out.println("Title: " + getTitle());
        System.out.println("Genre: " + getGenre());
    }
}
