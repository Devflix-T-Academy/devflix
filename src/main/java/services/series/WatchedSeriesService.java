package services.series;

import model.*;
import model.interfaces.Watchable;
import util.Cores;

import java.util.*;
import java.util.stream.Collectors;


public class WatchedSeriesService implements Watchable<Series> {
    private static Map<Integer, List<Series>> series;


    public WatchedSeriesService(List<Series> list) {
        this.series = new HashMap<>();
        this.createList(list);
    }

    @Override
    public void showList() {
        series.forEach((key, value) -> {
            System.out.println(Cores.TEXT_RED_BOLD + "Vezes assistidas: " + key + Cores.TEXT_RESET );
            value.forEach(movie-> {
                movie.displayDetails();
                System.out.println();
            });
        });
    }

    @Override
    public void createList(List<Series> list) {
        series = list.stream()
                .collect(Collectors.groupingBy(Series :: getWatched));
    }

}
