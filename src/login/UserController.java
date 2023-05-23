package login;

import java.util.Scanner;

public class UserController {
    private static final String WELCOME_MESSAGE = "Welcome to login application.";
    private static final String LOGIN = "Login:";
    private static final String PASSWORD = "Password:";
    private static final String EMAIL = "Email:";
    private static final String SHOW_MENU = """
            Choose what you want to do, type the answer:
            1. Register account
            2. Login
            3. Update account
            4. Delete account
            5. Exit program
            """;
    private static final String ANSWER_REQUIREMENT = "Not appropriate number. Try again.";
    private static final String DELETE_QUESTION = "What user you want to delete?";
    private static final String LOGIN_FIRST = "First you need to log in.";
    private static final String UPDATE_PROCEDURE = "Enter new user name, new password and new email address";
    private static final String UPDATE_CONFIRMATION = "Update successful";
    private static final String INVALID_USERNAME_OR_PASSWORD = "Invalid user name or password";

    private static final String THIS_EMAIL_EXIST = "This email already exist.";
    private final UserService userService;
    private final View view;

    public UserController(UserService userService, View view) {
        this.userService = userService;
        this.view = view;
    }

    public void register() {
        String login = askForTextInput(LOGIN);
        String password = askForTextInput(PASSWORD);
        String email = askForTextInput(EMAIL);

        view.update(userService.register(login, password, email));
    }

    public void login() {
        String login = askForTextInput(LOGIN);
        String password = askForTextInput(PASSWORD);

        view.update(userService.login(login, password));
    }

    public void delete() {
        view.update(DELETE_QUESTION);
        String login = askForTextInput(LOGIN);
        String password = askForTextInput(PASSWORD);

        view.update(userService.delete(login, password));
    }

    public void update() {
        view.update(LOGIN_FIRST);
        String oldLogin = askForTextInput(LOGIN);
        String password = askForTextInput(PASSWORD);

        if(!userService.loginValidation(oldLogin, password).isEmpty()) {
            view.update(INVALID_USERNAME_OR_PASSWORD);
            return;
        }

        view.update(UPDATE_PROCEDURE);
        String newLogin = askForTextInput(LOGIN);
        String newPassword = askForTextInput(PASSWORD);
        String newEmail = askForTextInput(EMAIL);

        userService.update(newLogin, newPassword, newEmail, oldLogin);
        view.update(UPDATE_CONFIRMATION);
    }


    private String askForTextInput(String message) {
        Scanner scanner = new Scanner(System.in);

        view.update(message);
        return scanner.nextLine();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;
        view.update(WELCOME_MESSAGE);

        do {
            view.update(SHOW_MENU);

            switch (scanner.nextInt()) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> update();
                case 4 -> delete();
                case 5 -> repeat = false;
                default -> view.update(ANSWER_REQUIREMENT);
            }
        } while (repeat);
    }
}
