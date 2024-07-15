package view;

import controller.MovieController;
import model.Movie;
import model.enums.Genre;
import services.movie.WatchedMovieService;
import util.Cores;
import util.ScanValidation;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MoviesMenu {
    static MovieController movieController = new MovieController();
    static Scanner scanner = new Scanner(System.in);

    public static void adminMenu() {
        int option;

        while (true) {
            System.out.println("""
                """ + Cores.TEXT_RED_BOLD + """
                *****************************************************
                                                                     
                                        Filmes                     
                                                                     
                *****************************************************
                                                                     
                            1 - Adicionar Filme                       
                            2 - Listar Todos os Filmes               
                            3 - Buscar Filme por Título              
                            4 - Atualizar Filme
                            5 - Remover Filme
                            6 - Filmes Mais Assistidos
                            7 - Assistir Filme
                            8 - Sair
                                                                     
                *****************************************************
                Digite sua opção:                                   """ + Cores.TEXT_RESET);

            option = ScanValidation.getValidIntInput(scanner);

            if (option == 8) {
                System.out.println(Cores.TEXT_RED_BOLD + "\nDevflix - Seu Gerenciador de Filmes!");
                DevflixMenu.about();
                scanner.close();
                System.exit(0);
            }

            switch (option) {
                case 1:
                    newMovieOption();
                    DevflixMenu.keyPress();
                    break;
                case 2:
                    listMoviesOption();
                    DevflixMenu.keyPress();
                    break;
                case 3:
                    searchMovieOption();
                    DevflixMenu.keyPress();
                    break;
                case 4:
                    updateMovieOption();
                    DevflixMenu.keyPress();
                    break;
                case 5:
                    deleteMovieOption();
                    DevflixMenu.keyPress();
                    break;
                case 6:
                    mostWatchedOption();
                    DevflixMenu.keyPress();
                    break;
                case 7:
                    watchMovieOption();
                    DevflixMenu.keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n" + Cores.TEXT_RESET);
                    DevflixMenu.keyPress();
                    break;
            }
        }
    }

    public static void clientMenu() {
        int option;

        while (true) {
            System.out.println("""
                """ + Cores.TEXT_RED_BOLD + """
                *****************************************************
                                                                     
                                        Filmes                     
                                                                     
                *****************************************************
                                                                     
                            1 - Listar Todos os Filmes               
                            2 - Buscar Filme por Título 
                            3 - Filmes Mais Assistidos             
                            4 - Assistir Filme
                            5 - Sair
                                                                     
                *****************************************************
                Digite sua opção:                                   """ + Cores.TEXT_RESET);

            option = ScanValidation.getValidIntInput(scanner);

            if (option == 5) {
                System.out.println(Cores.TEXT_RED_BOLD + "\nDevflix - Seu Gerenciador de Filmes!");
                DevflixMenu.about();
                scanner.close();
                System.exit(0);
            }

            switch (option) {
                case 1:
                    listMoviesOption();
                    DevflixMenu.keyPress();
                    break;

                case 2:
                    searchMovieOption();
                    DevflixMenu.keyPress();
                    break;

                case 3:
                    mostWatchedOption();
                    DevflixMenu.keyPress();
                    break;

                case 4:
                    watchMovieOption();
                    DevflixMenu.keyPress();
                    break;

                default:
                    System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n" + Cores.TEXT_RESET);
                    DevflixMenu.keyPress();
                    break;
            }
        }
    }

    private static void newMovieOption() {
        int duration;
        int year;
        String title;
        Genre genre;
        System.out.println(Cores.TEXT_WHITE + "Adicionar Filme\n");

        System.out.println("Digite o título: ");
        title = scanner.nextLine();
        System.out.println("Escolha o gênero: ");
        genre = getGenreInput();
        System.out.println("Digite o ano: ");
        year = scanner.nextInt();
        System.out.println("Digite a duração em minutos: ");
        duration = scanner.nextInt();

        Movie newMovie = new Movie(title, LocalDate.now(), genre, duration);
        movieController.addMovie(newMovie);
    }

    private static Genre getGenreInput() {
        Genre selectedGenre = null;

        while (selectedGenre == null) {
            IntStream.range(0, Genre.values().length)
                    .forEach(i -> System.out.println((i + 1) + " - " + Genre.values()[i]));

            int option = ScanValidation.getValidIntInput(scanner);

            if (option >= 1 && option <= Genre.values().length) {
                selectedGenre = Genre.values()[option - 1];
            } else {
                System.out.println("Opção inválida");
            }
        }

        return selectedGenre;
    }

    private static void listMoviesOption() {
        System.out.println(Cores.TEXT_WHITE + "Listar Todos os Filmes\n" + Cores.TEXT_RESET);
        movieController.listAllMovies();
    }

    private static void searchMovieOption() {
        String title;
        System.out.println(Cores.TEXT_WHITE + "Buscar Filme por Título\n");

        System.out.println("Digite o título: ");
        scanner.nextLine();
        title = scanner.nextLine();
        Movie foundMovie = movieController.findMovieByTitle(title);

        if (foundMovie != null) {
            foundMovie.displayDetails();
        } else {
            System.out.println("Filme não encontrado!");
        }
    }

    private static void updateMovieOption() {
        int year;
        Genre genre;
        int duration;
        String title;
        System.out.println(Cores.TEXT_WHITE + "Atualizar Filme\n");

        System.out.println("Digite o título do filme a ser atualizado: ");
        scanner.nextLine();
        title = scanner.nextLine();

        Movie movieToUpdate = movieController.getMovieByTitle(title);
        if (movieToUpdate != null) {
            System.out.println("Escolha o novo gênero: ");
            genre = getGenreInput();
            System.out.println("Digite o novo ano: ");
            year = scanner.nextInt();
            System.out.println("Digite a nova duração em minutos: ");
            duration = scanner.nextInt();

            LocalDate date = LocalDate.now();

            movieToUpdate.setGenre(genre);
            movieToUpdate.setDate(date);
            movieToUpdate.setDuration(duration);
            movieController.updateMovie(movieToUpdate);
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    private static void deleteMovieOption() {
        String title;
        System.out.println(Cores.TEXT_WHITE + "Remover Filme\n");

        System.out.println("Digite o título do filme a ser removido: ");
        scanner.nextLine();
        title = scanner.nextLine();
        movieController.removeMovieByTitle(title);
    }

    private static void watchMovieOption() {
        String title;

        movieController.listAllMovies();
        DevflixMenu.keyPress();
        while (true){
            System.out.println("Qual filme deseja assistir?");
            title = ScanValidation.getValidStringInput(scanner, 1);
            Movie movie = movieController.getMovieByTitle(title);
            if (movie != null) {
                System.out.println("Assitindo filme: " + movie.getTitle());
                movie.setWatched(movie.getWatched() + 1);
                RatingMenu.Show(movie);
                break;
            }
            System.out.println("Filme não encontrado");
        }
    }

    private static void mostWatchedOption(){
        WatchedMovieService watchedMovie = new WatchedMovieService(movieController.listMovies());
        watchedMovie.showList();
    }
}
