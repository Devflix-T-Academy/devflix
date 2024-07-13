package view;

import util.Cores;
import view.movies.MoviesMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DevflixMenu {
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        int option;

        while (true) {
            System.out.println(Cores.TEXT_RED_BOLD + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                    Devflix Menu                     ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                    1 - Filmes                       ");
            System.out.println("                    2 - Séries                       ");
            System.out.println("                    3 - Sair                         ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Digite sua opção:                                   " + Cores.TEXT_RESET);

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nPor favor, digite um número inteiro válido!");
                scanner.nextLine();
                option = 0;
            }

            if (option == 3) {
                System.out.println(Cores.TEXT_RED_BOLD + "\nDevflix - Seu Gerenciador de Filmes!");
                about();
                scanner.close();
                System.exit(0);
            }

            switch (option) {
                case 1:
                    if (Main.currentUser.isAdmin()) {
                        MoviesMenu.adminMenu();
                    } else {
                        MoviesMenu.clientMenu();
                    }
                case 2:
                    System.out.println("implementar menu de séries");

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

}
