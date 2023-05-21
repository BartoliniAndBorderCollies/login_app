package login;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String ERROR_USERNAME_ALREADY_EXIST = "This user already exist.";
    private static final String USER_NO_EXIST = "This user does not exist.";
    private static final String ERROR_PASSWORD_INVALID = "Invalid password.";
    private static final String SUCCESS_REGISTRATION = "Registration completed!";
    private static final String EMAIL_ALREADY_EXIST = "This email address already exist.";
    private static final String LOGIN_SUCCESSFUL = "Login successful";
    private static final String DELETE_FAILED = "Delete operation failed. No such user name/password.";
    private static final String DELETE_CONFIRMATION = "User has been deleted.";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfLoginExist(String userName) {
        return userRepository.checkIfLoginExist(userName);
    }

    public boolean checkIfCorrectPassword(String password) {
        // TODO: logika dla poprawności hasła.
        if (password.length() < 8) {
            return false;
        }
        return true;
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

    public List<String> loginValidation(String login, String password) {
        List<String> lines = new ArrayList<>();

        try {
            if (!findUser(login).getPassword().equals(password)) {
                // TODO lines.add()
            }
        } catch (IllegalArgumentException e) {
            // TODO lines.add();
        }

        return lines;
    }

    public List<String> delete(String login, String password) {
        List<String> lines = new ArrayList<>();

        if (!loginValidation(login, password).isEmpty()) {
            lines.add(DELETE_FAILED);
        }

        if (lines.isEmpty()) {
            userRepository.delete(findUser(login));
            lines.add(DELETE_CONFIRMATION);
        }
        return lines;
    }

    public User findUser(String login) throws IllegalArgumentException {
        return userRepository.findByLogin(login).orElseThrow(IllegalArgumentException::new);
    }

    //Dodałem powyższą metodę drugą już na szukanie findUser(), po to, żeby metoda ta zwracała mi User
    // potrzebowalem Usera do metody delete() na bazie danych (userRepository)
    //bo pierwsza metoda find(), która jest w klasie bazy danych zwraca Optional<User>, a Optionala nie mogłem użyc
    // potrzebowałem samego User user. żeby to osiagnąć muszę obsłużyć optionala poprzez .orElseThrow

    public void update(String login, String password, String email) {
        userRepository.update(findUserUpdate(login, password, email));
    }

    public User findUserUpdate(String login, String password, String email) {
        return userRepository.findByLogin(login).orElseThrow();
    }

    public boolean checkIfEmailExist(String email) {
        if (userRepository.checkIfEmailExist(email)) {
            // TODO System.out.println(EMAIL_ALREADY_EXIST);
            return true;
        }
        return false;
    }
}
