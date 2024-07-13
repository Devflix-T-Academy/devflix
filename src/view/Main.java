package view;

import controller.user.UserController;
import model.user.Role;
import model.user.User;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UserController userController = new UserController();
    static User currentUser;

    public static void main(String[] args) {
        User admin = new User("admin", "admin", "admin@admin.com", Role.ADMIN);
        User client = new User("client", "client", "client@client.com", Role.CLIENT);
        userController.create(admin);
        userController.create(client);

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
        System.out.println("Informe seu nome:");
        String name = scanner.nextLine();
        System.out.println("Informe seu e-mail:");
        String email = scanner.nextLine();
        System.out.println("Informe uma senha:");
        String password = scanner.nextLine();

        User user = new User(name, email, password, Role.CLIENT);
        userController.create(user);

        System.out.println("Cadastro realizado com sucesso!\n");

        System.out.println("Efetue o login");
        login();
    }

    public static void login() {
        System.out.println("E-mail:");
        String email = scanner.nextLine();
        System.out.println("Senha:");
        String password = scanner.nextLine();

        User user = userController.authenticate(email, password);
        if (user != null) {
            currentUser = user;
        }
    }
}