package controller;

import model.Movie;
import model.Watched;

import java.util.*;
import java.util.stream.Collectors;


public class WatchedMovieController implements Watched<Movie> {
    private Map<Integer, Movie> movies;

    public WatchedMovieController(List<Movie> list) {
        this.movies = new HashMap<>();
        this.criarLista(list);
    }

    public void imprimirLista(){
        movies.entrySet().stream().forEach(value -> {
            System.out.println(value.getKey() + value.getValue().getTitle());
        });
    }

    @Override
    public void criarLista(List<Movie> list) {
        movies = list.stream()
                .collect(Collectors.toMap(
                        Movie::getWatched,
                        movie->movie));
    }



}

