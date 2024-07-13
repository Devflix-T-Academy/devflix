package model.enums;

public enum Genre {
    ACAO("Ação"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    TERROR("Terror"),
    ROMANCE("Romance"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    FANTASIA("Fantasia"),
    SUSPENSE("Suspense"),
    DOCUMENTARIO("Documentário"),
    ANIMACAO("Animação");

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

