package util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.MovieController;
import controller.SeriesController;
import controller.UserController;
import model.*;
import model.enums.Genre;
import model.enums.Role;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    private UserController userController = new UserController();
    private final String currentDirectory = System.getProperty("user.dir");

    public void mock() {
        MovieController.movies = getMovies();
        SeriesController.seriesList = getSeries();
        UserController.users = getUsers();
    }

    private List<User> getUsers() {
        String jsonFilePath = "/src/main/resources/user-data.json";
        JsonArray usersJsonArray = getFile(jsonFilePath, "users");
        List<User> users = new ArrayList<>();

        for (JsonElement element : usersJsonArray) {
            JsonObject userJson = element.getAsJsonObject();

            String username = userJson.get("name").getAsString();
            String email = userJson.get("email").getAsString();
            String password = userJson.get("password").getAsString();
            Role role = Role.valueOf(userJson.get("role").getAsString());

            User user = new User(username, email, password, role);

            users.add(user);
        }

        return users;
    }


    private List<Movie> getMovies() {
        String jsonFilePath = "/src/main/resources/movie-data.json";
        JsonArray moviesJsonArray = getFile(jsonFilePath, "movies");
        List<Movie> movies = new ArrayList<>();

        for (JsonElement element : moviesJsonArray) {
            JsonObject movieJson = element.getAsJsonObject();

            String title = movieJson.get("title").getAsString();
            String dateStr = movieJson.get("date").getAsString();
            LocalDate date = LocalDate.parse(dateStr);
            String genreStr = movieJson.get("genre").getAsString();
            Genre genre = Genre.valueOf(genreStr);
            int duration = movieJson.get("duration").getAsInt();

            Movie movie = new Movie(title, date, genre, duration);

            movies.add(movie);
        }

        return movies;
    }


    private List<Series> getSeries() {
        String jsonFilePath = "/src/main/resources/series-data.json";
        JsonArray seriesJsonArray = getFile(jsonFilePath, "series");
        List<Series> seriesList = new ArrayList<>();

        for (JsonElement element : seriesJsonArray) {
            JsonObject seriesJson = element.getAsJsonObject();

            String title = seriesJson.get("title").getAsString();
            String dateStr = seriesJson.get("date").getAsString();
            LocalDate date = LocalDate.parse(dateStr);
            String genreStr = seriesJson.get("genre").getAsString();
            Genre genre = Genre.valueOf(genreStr);

            Series series = new Series(title, date, genre);

            JsonArray seasonListJson = seriesJson.getAsJsonArray("seasonList");
            for (JsonElement seasonElement : seasonListJson) {
                JsonObject seasonJson = seasonElement.getAsJsonObject();
                JsonArray episodeListJson = seasonJson.getAsJsonArray("episodeList");

                Season season = new Season();
                for (JsonElement episodeElement : episodeListJson) {
                    JsonObject episodeJson = episodeElement.getAsJsonObject();
                    String episodeTitle = episodeJson.get("title").getAsString();
                    String episodePreview = episodeJson.get("preview").getAsString();
                    int episodeNumber = episodeJson.get("number").getAsInt();
                    Episode episode = new Episode(episodeTitle, episodePreview, episodeNumber);
                    season.addEpisode(episode);
                }

                series.addSeason(season);
            }

            seriesList.add(series);
        }

        return seriesList;

    }

    private JsonArray getFile(String jsonFilePath, String item) {
        try (Reader reader = new FileReader(currentDirectory + jsonFilePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            return jsonObject.getAsJsonArray(item);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

}
