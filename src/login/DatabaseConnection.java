package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/login_app";
        String username = "login_app_user";
        String password =  "Bartek123456";
        return DriverManager.getConnection(url, username, password);

        //TODO: nie mam automatycznego zamykania?
    }

}
