
package model;

import java.time.LocalDate;

public class Movie extends Show{
        private int duration;


    public Movie(int id, String title, LocalDate date, String genre, int watched, int duration) {
        super(id, title, date, genre, watched);
        this.duration = duration;
    }

    public int getDuration() {
            return duration;
    }

    public void setDuration(int duration) {
            this.duration = duration;
    }
    }

