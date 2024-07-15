package services.movie;

import model.interfaces.Watchable;
import model.Movie;

import java.util.*;
import java.util.stream.Collectors;
import util.Cores;


public class WatchedMovieService implements Watchable<Movie> {
    private static Map<Integer, List<Movie>> movies;

    public WatchedMovieService(List<Movie> list) {
        this.movies = new HashMap<>();
        this.createList(list);
    }

    @Override
    public void showList(){
        movies.forEach((key, value) -> {
            System.out.println(Cores.TEXT_RED_BOLD + "Vezes assistidas: " + key + Cores.TEXT_RESET );
            value.forEach(movie-> {
                movie.displayDetails();
                System.out.println();
            });
        });
    }

    @Override
    public void createList(List<Movie> list) {
        movies = list.stream()
                .collect(Collectors.groupingBy(Movie :: getWatched));
    }



}

