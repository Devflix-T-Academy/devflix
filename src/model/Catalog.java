package model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    // private List<Series> series;
    private List<Movie> movies;

    public Catalog() {
        // this.series = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    // public void addSeries(Series series) {
    //     this.series.add(series);
    // }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    // public List<Series> getSeries() {
    //     return series;
    // }

    public List<Movie> getMovies() {
        return movies;
    }
}
