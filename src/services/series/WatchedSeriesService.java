package services.series;

import model.*;
import model.interfaces.Watchable;

import java.util.*;
import java.util.stream.Collectors;


public class WatchedSeriesService implements Watchable<Series> {
    private Map<Integer, Series> series;


    public WatchedSeriesService(List<Series> list) {
        this.series = new HashMap<>();
        this.criarLista(list);
    }

    @Override
    public void imprimirLista() {
        series.forEach((key, value) -> System.out.println(value.getTitle()));
    }

    @Override
    public void criarLista(List<Series> list) {
        series = list.stream()
                .collect(Collectors.toMap(
                        Series::getWatched,
                        series->series));
    }

}
