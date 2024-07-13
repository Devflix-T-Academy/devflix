package view.movies;

import controller.show.MovieController;
import model.show.Movie;
import util.Cores;
import view.DevflixMenu;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MoviesMenu {
    static MovieController movieController = new MovieController();
    static Scanner scanner = new Scanner(System.in);

    public static void adminMenu() {
        int option;

        while (true) {
            adminOptions();

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nPor favor, digite um número inteiro válido!");
                scanner.nextLine();
                option = 0;
            }

            if (option == 6) {
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
                default:
                    DevflixMenu.invalidOption();
                    DevflixMenu.keyPress();
                    break;
            }
        }
    }

    public static void clientMenu() {
        int option;

        while (true) {
            clientOptions();

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nPor favor, digite um número inteiro válido!");
                scanner.nextLine();
                option = 0;
            }

            if (option == 3) {
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
                default:
                    DevflixMenu.invalidOption();
                    DevflixMenu.keyPress();
                    break;
            }
        }
    }

    private static void adminOptions() {
        System.out.println(Cores.TEXT_RED_BOLD + "*****************************************************");
        System.out.println("                                                     ");
        System.out.println("                      Filmes                         ");
        System.out.println("                                                     ");
        System.out.println("*****************************************************");
        System.out.println("                                                     ");
        System.out.println("            1 - Adicionar Filme                       ");
        System.out.println("            2 - Listar Todos os Filmes               ");
        System.out.println("            3 - Buscar Filme por Título              ");
        System.out.println("            4 - Atualizar Filme                      ");
        System.out.println("            5 - Remover Filme                        ");
        System.out.println("            6 - Sair                                 ");
        System.out.println("                                                     ");
        System.out.println("*****************************************************");
        System.out.println("Digite sua opção:                                   " + Cores.TEXT_RESET);
    }

    private static void clientOptions() {
        System.out.println(Cores.TEXT_RED_BOLD + "*****************************************************");
        System.out.println("                                                     ");
        System.out.println("                      Filmes                         ");
        System.out.println("                                                     ");
        System.out.println("*****************************************************");
        System.out.println("                                                     ");
        System.out.println("            1 - Listar Todos os Filmes               ");
        System.out.println("            2 - Buscar Filme por Título              ");
        System.out.println("            3 - Sair                                 ");
        System.out.println("                                                     ");
        System.out.println("*****************************************************");
        System.out.println("Digite sua opção:                                   " + Cores.TEXT_RESET);
    }

    private static void newMovieOption() {
        int duration;
        int year;
        String title;
        String genre;
        System.out.println(Cores.TEXT_WHITE + "Adicionar Filme\n");

        System.out.println("Digite o título: ");
        scanner.nextLine();
        title = scanner.nextLine();
        System.out.println("Digite o gênero: ");
        genre = scanner.nextLine();
        System.out.println("Digite o ano: ");
        year = scanner.nextInt();
        System.out.println("Digite a duração em minutos: ");
        duration = scanner.nextInt();

        Movie newMovie = new Movie(title, LocalDate.now(), genre, duration);
        movieController.addMovie(newMovie);
    }

    private static void listMoviesOption() {
        System.out.println(Cores.TEXT_WHITE + "Listar Todos os Filmes\n");
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
        String genre;
        int duration;
        String title;
        System.out.println(Cores.TEXT_WHITE + "Atualizar Filme\n");

        System.out.println("Digite o título do filme a ser atualizado: ");
        scanner.nextLine();
        title = scanner.nextLine();

        Movie movieToUpdate = movieController.getMovieByTitle(title);
        if (movieToUpdate != null) {
            System.out.println("Digite o novo gênero: ");
            genre = scanner.nextLine();
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


}
