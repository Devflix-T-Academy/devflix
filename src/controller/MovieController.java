package controller;

import controller.interfaces.MediaController;
import model.Movie;
import repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieController implements MovieRepository, MediaController {
    private List<Movie> movies;

    public MovieController() {
        this.movies = new ArrayList<>();
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public List<Movie> listMovies() {
        return new ArrayList<>(movies);
    }

    @Override
    public Movie findMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void removeMovieByTitle(String title) {
        Movie movie = findMovieByTitle(title);
        if (movie != null) {
            movies.remove(movie);
        } else {
            System.out.println("Filme não encontrado!");
        }
    }

    @Override
    public void updateMovie(Movie updatedMovie) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(updatedMovie.getTitle())) {
                movie.setGenre(updatedMovie.getGenre());
                movie.setDate(updatedMovie.getDate());
                movie.setDuration(updatedMovie.getDuration());
                return;
            }
        }
        System.out.println("Filme não encontrado para atualização!");
    }

    public void listAllMovies() {
        if (movies.isEmpty()) {
            System.out.println("Não há filmes registrados!");
        } else {
            for (Movie movie : movies) {
                movie.displayDetails();
            }
        }
    }

    public Movie getMovieByTitle(String title) {
        return findMovieByTitle(title);
    }

    @Override
    public List<Movie> getMostWatched() {
        return List.of();
    }

    @Override
    public List<Movie> getBestRated() {
        return List.of();
    }
}
