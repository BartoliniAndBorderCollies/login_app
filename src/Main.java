import login.*;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService, view);

        userController.run();
    }
}