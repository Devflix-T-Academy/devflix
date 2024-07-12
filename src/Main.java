import controller.UserController;
import model.Admin;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();

        userController.create(new Admin("admin", "admin@email.com", "password"));


    }
}