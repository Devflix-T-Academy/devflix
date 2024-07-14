package util;

public class CounterIds {
    private static Long USER_ID = 0L;
    private static Long SERIES_ID = 0L;
    private static Long SEASON_ID = 0L;
    private static Long EPISODE_ID = 0L;
    private static Long MOVIE_ID = 0L;

    public static synchronized Long getNextUserId() {
        return ++USER_ID;
    }

    public static synchronized Long getNextSeriesId() {
        return ++SERIES_ID;
    }

    public static synchronized Long getNextSeasonId() {
        return ++SEASON_ID;
    }

    public static synchronized Long getNextEpisodeId() {
        return ++EPISODE_ID;
    }

    public static synchronized Long getNextMovieId() {
        return ++MOVIE_ID;
    }

    public static Long getCurrentUserId() {
        return USER_ID;
    }

    public static Long getCurrentSeriesId() {
        return SERIES_ID;
    }

    public static Long getCurrentSeasonId() {
        return SEASON_ID;
    }

    public static Long getCurrentEpisodeId() {
        return EPISODE_ID;
    }

    public static Long getCurrentMovieId() {
        return MOVIE_ID;
    }
}
