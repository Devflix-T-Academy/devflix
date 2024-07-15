package model.enums;

public enum Genre {
    Ação("Ação"),
    Comédia("Comédia"),
    Drama("Drama"),
    Terror("Terror"),
    Romance("Romance"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    Fantasia("Fantasia"),
    Suspense("Suspense"),
    Documentário("Documentário"),
    Animação("Animação");

    private String genreName;

    Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }
}

