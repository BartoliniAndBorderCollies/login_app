import login.*;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        UserRepository userRepository = new UserRepository();
        MenuService menuService = new MenuService(userRepository);
        MenuController menuController = new MenuController(menuService, view);

        view.welcome();
        menuController.login();

    }
}