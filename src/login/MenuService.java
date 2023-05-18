package login;

public class MenuService {

    private final UserRepository userRepository;

    public MenuService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public boolean checkIfUserExist (String userName) {
        return userRepository.checkIfUserExist(userName);
    }

    public boolean checkIfCorrectPassword(String password) {
        return userRepository.checkIfCorrectPassword(password);
    }
}
