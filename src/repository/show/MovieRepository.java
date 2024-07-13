package repository.show;

import model.show.Movie;

import java.util.List;

public interface MovieRepository {
    void addMovie(Movie movie);
    List<Movie> listMovies();
    Movie findMovieByTitle(String title);
    void removeMovieByTitle(String title);
    void updateMovie(Movie movie);
}
