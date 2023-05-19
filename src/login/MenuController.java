package login;

import java.util.Scanner;

public class MenuController {

    private final MenuService menuService;
    private final View view;

    public MenuController(MenuService menuService, View view) {
        this.menuService = menuService;
        this.view = view;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        view.insertUsernameAndPassword();
        System.out.println("User name: ");
        String userName = scanner.nextLine();
        if(!menuService.checkIfUserExist(userName)) {
            System.out.println("This user already exist.");
            return;
        }
        System.out.println("Password: ");
        String password = scanner.nextLine();

        if(!menuService.checkIfCorrectPassword(password)) {
            System.out.println("Invalid password.");
        }
    }
}
