package login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuService {
    private static final String ERROR_USERNAME_ALREADY_EXIST = "This user already exist.";
    private static final String ERROR_PASSWORD_INVALID = "Invalid password.";
    private static final String SUCCESS_REGISTRATION = "Registration completed!";

    private final UserRepository userRepository;

    public MenuService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public boolean checkIfLoginExist(String userName) {
        return userRepository.checkIfLoginExist(userName);
    }

    public boolean checkIfCorrectPassword(String password) {
        // walidacja, że hasło powinno mieć x znaków, albo coś tam
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

    public List<String> registerValidation(String login, String password) {
        List<String> lines = new ArrayList<>();

        if (checkIfLoginExist(login)) {
            lines.add(ERROR_USERNAME_ALREADY_EXIST);
        }

        if (!checkIfCorrectPassword(password)) {
            lines.add(ERROR_PASSWORD_INVALID);
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

    public void delete(String userName) {
        userRepository.delete(findUser(userName));
    }


}
