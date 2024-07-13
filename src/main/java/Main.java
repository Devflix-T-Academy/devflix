import controller.UserController;
import util.MockData;
import view.LoginMenu;

public class Main {
    public static void main(String[] args) {
        MockData mockData = new MockData();
        mockData.mock();
        LoginMenu.show();
    }
}