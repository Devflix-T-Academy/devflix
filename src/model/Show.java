package model;

import java.time.LocalDate;

public abstract class Show {
    static int id;
    String title;
    LocalDate date;
    String genre;

    public Show(int id, String title, LocalDate date, String genre) {
        Show.id = id + 1;
        this.title = title;
        this.date = date;
        this.genre = genre;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Show.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
