package model;

import model.enums.Genre;
import model.interfaces.Media;

import java.time.LocalDate;

public class Movie extends Media {
    private int duration;

    public Movie(String title, LocalDate date, Genre genre, int duration) {
        super(title, date, genre);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void displayDetails() {
        System.out.println("Título: " + getTitle());
        System.out.println("Gênero: " + getGenre());
        System.out.println("Duração: " + getDuration() + " minutos");
    }
}
