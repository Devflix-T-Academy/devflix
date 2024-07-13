package view;

import com.google.gson.JsonObject;
import controller.MovieController;
import model.Movie;
import model.enums.Genre;
import util.Cores;
import util.ScanValidation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DevflixMenu {
    public static void adminMenu() {
        MovieController movieController = new MovieController();
        Scanner scanner = new Scanner(System.in);

        int option;
        String title, genre;
        int year, duration;

        while (true) {
            System.out.println("""
                            """ + Cores.TEXT_RED_BOLD + """
                            *****************************************************
                                                                                 
                                                Devflix Menu                     
                                                                                 
                            *****************************************************
                                                                                 
                                        1 - Adicionar Filme                       
                                        2 - Listar Todos os Filmes               
                                        3 - Buscar Filme por Título              
                                        4 - Atualizar Filme                      
                                        5 - Remover Filme
                                        6 - Assistir Filme    
                                        7 - Sair                                 
                                                                                 
                            *****************************************************
                            Digite sua opção:                                   """ + Cores.TEXT_RESET);

            option = ScanValidation.getValidIntInput(scanner);

            if (option == 7) {
                System.out.println(Cores.TEXT_RED_BOLD + "\nDevflix - Seu Gerenciador de Filmes!");
                about();
                scanner.close();
                System.exit(0);
            }

            switch (option) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE + "Adicionar Filme\n");

                    System.out.println("Digite o título: ");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    System.out.println("Digite o gênero: ");
                    genre = scanner.nextLine();
                    Genre genre1 = Genre.valueOf(genre);
                    System.out.println("Digite o ano: ");
                    year = scanner.nextInt();
                    System.out.println("Digite a duração em minutos: ");
                    duration = scanner.nextInt();

                    Movie newMovie = new Movie(title, LocalDate.now(), genre1, duration);
                    movieController.addMovie(newMovie);

                    keyPress();
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE + "Listar Todos os Filmes\n");
                    movieController.listAllMovies();
                    keyPress();
                    break;
                case 3:
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

                    keyPress();
                    break;
                case 4:
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
                        Genre genre2 = Genre.valueOf(genre);

                        movieToUpdate.setGenre(genre2);
                        movieToUpdate.setDate(date);
                        movieToUpdate.setDuration(duration);
                        movieController.updateMovie(movieToUpdate);
                    } else {
                        System.out.println("Filme não encontrado.");
                    }

                    keyPress();
                    break;

                case 5:
                    System.out.println(Cores.TEXT_WHITE + "Remover Filme\n");

                    System.out.println("Digite o título do filme a ser removido: ");
                    scanner.nextLine();
                    title = scanner.nextLine();
                    movieController.removeMovieByTitle(title);

                    keyPress();
                    break;
                case 6:
                    movieController.listAllMovies();
                    keyPress();
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

                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n" + Cores.TEXT_RESET);
                    keyPress();
                    break;
            }
        }
    }

    public static void about() {
        System.out.println(Cores.TEXT_RED_BOLD + "\n*********************************************************");
        System.out.println("Projeto desenvolvido por: ");
        System.out.println("T-Academy");
        System.out.println("github.com/Devflix-T-Academy");
        System.out.println("*********************************************************");
    }

    public static void keyPress() {
        try {
            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de Enter!");
        }
    }
}
