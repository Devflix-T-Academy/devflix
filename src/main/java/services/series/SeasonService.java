package services.series;

import model.Season;
import model.Series;

import java.util.List;
import java.util.Scanner;

public class SeasonService {
    Scanner scanner = new Scanner(System.in);

    public SeasonService(){}

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
        EpisodeService episodeController = new EpisodeService();
        episodeController.doUpdateSeries(season);
    }
}
