package model.interfaces;

import model.enums.Genre;
import model.enums.Rating;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public abstract class Media {
    public static Long idCounter = 0L;
    private Long id;
    private String title;
    private LocalDate date;
    private Genre genre;
    private int watched;
    private List<Rating> ratings;

    public Media(String title, LocalDate date, Genre genre) {
        this.id = Media.idCounter++;
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

    public static Long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Long idCounter) {
        Media.idCounter = idCounter;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void showGenreList(){

    }

    public void displayDetails() {
        System.out.println("Title: " + getTitle());
        System.out.println("Genre: " + getGenre().getGenreName());
    }
}
