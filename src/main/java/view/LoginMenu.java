package view;

import controller.UserController;
import model.enums.Role;
import model.User;

import java.util.Scanner;

public class LoginMenu {
    static Scanner scanner = new Scanner(System.in);
    static UserController userController = new UserController();
    static User currentUser;

    public static void show() {

        System.out.println("DEVFLIX - Bem-vindo!");

        boolean flag = true;
        while (flag) {
            System.out.println("Escolha uma opção:");

            System.out.println("1 - Registrar");
            System.out.println("2 - Entrar");
            System.out.println("3 - Sair");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public static void register() {
        String name = promptName();
        String email = promptEmail();
        String password = promptPassword();

        User user = new User(name, email, password, Role.CLIENT);
        userController.create(user);

        System.out.println("Cadastro realizado com sucesso!\n");

        System.out.println("Efetue o login");
        login();
    }

    public static String promptName() {
        String name = "";

        while(name.isEmpty()) {
            System.out.println("Informe seu nome:");
            name = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Nome não pode ficar em branco");
            }
        }

        return name;
    }

    public static String promptEmail() {
        String email = "";

        while(email.isEmpty()) {
            System.out.println("Informe seu e-mail:");
            email = scanner.nextLine();

            if (email.isEmpty()) {
                System.out.println("E-mail não pode ficar em branco");
            }
        }

        return email;
    }

    public static String promptPassword() {
        String password = "";

        while(password.isEmpty()) {
            System.out.println("Informe uma senha:");
            password = scanner.nextLine();

            if (password.isEmpty()) {
                System.out.println("Senha não pode ficar em branco");
            }
        }

        return password;
    }

    public static void login() {
        System.out.println("E-mail:");
        String email = scanner.nextLine();
        System.out.println("Senha:");
        String password = scanner.nextLine();

        currentUser = userController.authenticate(email, password);

        if (currentUser != null) {
            DevflixMenu.mainMenu();
        }
    }
}