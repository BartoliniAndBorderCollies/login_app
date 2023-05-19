package login;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private static final String WELCOME_MESSAGE = "Welcome to login application.";
    private static final String ASK_FOR_LOGIN_AND_PASSWORD = """
            Please insert your login and password:
            """;
    private static final String LOGIN = "Login:";
    private static final String PASSWORD = "Password:";

    private final MenuService menuService;
    private final View view;

    public MenuController(MenuService menuService, View view) {
        this.menuService = menuService;
        this.view = view;
    }

    public void register() {
        String login = askForTextInput(LOGIN);
        String password = askForTextInput(PASSWORD);

        view.update(menuService.register(login, password));
    }

    private String askForTextInput(String message) {
        Scanner scanner = new Scanner(System.in);

        view.update(message);
        return scanner.nextLine();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        view.update(WELCOME_MESSAGE);
        // opcja wyboru, co chcesz zrobić
        switch (scanner.nextInt()) {
            case 1:
            case 2:
            default:
        }
        register();
    }
}
