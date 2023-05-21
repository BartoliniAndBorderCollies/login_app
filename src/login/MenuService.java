package login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuService {
    private static final String ERROR_USERNAME_ALREADY_EXIST = "This user already exist.";
    private static final String USER_NO_EXIST = "This user does not exist.";
    private static final String ERROR_PASSWORD_INVALID = "Invalid password.";
    private static final String SUCCESS_REGISTRATION = "Registration completed!";
    private static final String EMAIL_ALREADY_EXIST = "This email address already exist.";
    private static final String LOGIN_SUCCESSFUL = "Login successful";
    private static final String DELETE_FAILED = "Delete operation failed. No such user name/password.";
    private static final String DELETE_CONFIRMATION = "User has been deleted.";

    private final UserRepository userRepository;

    public MenuService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfLoginExist(String userName) {
        return userRepository.checkIfLoginExist(userName);
    }

    public boolean checkIfCorrectPassword(String password) {
        return userRepository.checkIfCorrectPassword(password);
    }

    public List<String> register(String login, String password, String email) {
        List<String> lines = registerValidation(login, password);
        if (lines.isEmpty()) {
            userRepository.register(login, password, email);
            lines.add(SUCCESS_REGISTRATION);
        }
        return lines;
    }

    public List<String> login(String login, String password) {
        List<String> lines = loginValidation(login, password);
        if (lines.isEmpty()) {
            lines.add(LOGIN_SUCCESSFUL);
        }
        return lines;
    }

    public List<String> registerValidation(String login, String password) {
        List<String> lines = new ArrayList<>();

        if (checkIfLoginExist(login)) {
            lines.add(ERROR_USERNAME_ALREADY_EXIST);
        }

        if (checkIfCorrectPassword(password)) {
            lines.add(ERROR_PASSWORD_INVALID);
        }
        return lines;
    }

    public List<String> loginValidation (String login, String password) {
        List<String> lines = new ArrayList<>();

        if(!checkIfLoginExist(login)) {
            lines.add(USER_NO_EXIST);
        }

        if(!checkIfCorrectPassword(password)) {
            lines.add(ERROR_PASSWORD_INVALID);
        }

        return lines;
    }

    public List<String> delete(String userName, String password) {
        List<String> lines = new ArrayList<>();

        if(!loginValidation(userName, password).isEmpty()) {
            lines.add(DELETE_FAILED);
        }

        if(!checkIfCorrectPassword(password)) {
            lines.add(ERROR_PASSWORD_INVALID);
        }

        if(lines.isEmpty()) {
            userRepository.delete(findUser(userName));
            lines.add(DELETE_CONFIRMATION);
        }

        return lines;
    }



    public User findUser(String name) throws IllegalArgumentException {
        return userRepository.find(name).orElseThrow(IllegalArgumentException::new);
    }

    //Dodałem powyższą metodę drugą już na szukanie findUser(), po to, żeby metoda ta zwracała mi User
    // potrzebowalem Usera do metody delete() na bazie danych (userRepository)
    //bo pierwsza metoda find(), która jest w klasie bazy danych zwraca Optional<User>, a Optionala nie mogłem użyc
    // potrzebowałem samego User user. żeby to osiagnąć muszę obsłużyć optionala poprzez .orElseThrow



    public void update(String userName, String password, String email) {
        userRepository.update(findUserUpdate(userName, password,email));
    }
    public User findUserUpdate(String name, String password, String email) {
        return userRepository.find(name).orElseThrow();
    }

    public boolean checkIfEmailExist(String email) {
        if(userRepository.checkIfEmailExist(email)) {
            System.out.println(EMAIL_ALREADY_EXIST);
            return true;
        }
        return false;
    }


}
