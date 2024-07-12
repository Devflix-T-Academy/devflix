package controller;

import model.Season;
import model.Series;

import java.util.List;
import java.util.Scanner;

public class SeasonController {
    Scanner scanner = new Scanner(System.in);

    public SeasonController(){}

    public void removeSeason(Series series){

    }

    public void doUpdateSeason(Series series){
        boolean validInput = false;
        int seasonSize = series.getSeasonList().size();

        while (!validInput){
            System.out.println("Update season");
            for (int i = 0; i < seasonSize; i++) {
                System.out.println("Season " + (i+1));
            }
            System.out.println("Your option: ");

            int option = scanner.nextInt();

            if(option > 0 && option <= seasonSize){
                updateEpisode(series.getSeasonList());
            }
            else{
                System.out.println("Invalid option, please try again\n");
            }
        }
    }

    public void updateEpisode(List<Season> season){
        EpisodeController episodeController = new EpisodeController();
        episodeController.doUpdateSeries(season);
    }
}