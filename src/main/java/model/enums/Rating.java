package model.enums;

import util.Cores;

public enum Rating {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private int stars;

    Rating(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return Cores.TEXT_YELLOW_BOLD_BRIGHT + switch (this) {
            case ONE_STAR -> "★☆☆☆☆";
            case TWO_STARS -> "★★☆☆☆";
            case THREE_STARS -> "★★★☆☆";
            case FOUR_STARS -> "★★★★☆";
            case FIVE_STARS -> "★★★★★";
        };
    }
}