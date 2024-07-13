package model.enums;

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
}