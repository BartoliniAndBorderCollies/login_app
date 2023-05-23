package login;

public class User {

    private int id;
    private String login;
    private String password;
    private String email;


    public User(int id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
