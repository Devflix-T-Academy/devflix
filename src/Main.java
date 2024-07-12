import controller.UserController;
import model.Role;
import model.User;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();

        User admin = new User("admin", "admin", "admin@admin.com", Role.ADMIN);
        User client = new User("client", "client", "client@client.com", Role.CLIENT);
        userController.create(admin);
        userController.create(client);
    }
}