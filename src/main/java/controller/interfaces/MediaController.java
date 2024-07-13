package controller.interfaces;

import java.util.List;

public interface MediaController<T> {
    public List<T> getMostWatched();
    public List<T> getBestRated();
}
