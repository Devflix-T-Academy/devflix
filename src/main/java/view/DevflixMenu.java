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
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        int option;
        while (true) {
            System.out.println("""
                            """ + Cores.TEXT_RED_BOLD + """
                            *****************************************************
                                                                                 
                                                Devflix Menu                     
                                                                                 
                            *****************************************************
                                                                                 
                                                1 - Filmes                       
                                                2 - Séries               
                                                3 - Sair              
                                                                                 
                            *****************************************************
                            Digite sua opção:                                   """ + Cores.TEXT_RESET);

            option = ScanValidation.getValidIntInput(scanner);

            if (option == 3) {
                System.out.println(Cores.TEXT_RED_BOLD + "\nDevflix - Seu Gerenciador de Filmes!");
                about();
                scanner.close();
                System.exit(0);
            }

            switch (option) {
                case 1:
                    if (LoginMenu.currentUser.isAdmin()) {
                        MoviesMenu.adminMenu();
                    } else {
                        MoviesMenu.clientMenu();
                    }
                case 2:
                    if (LoginMenu.currentUser.isAdmin()) {
                        // TODO: menu de séries do Admin
                    } else {
                        // TODO: menu de séries do Cliente
                    }
                case 3:
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
