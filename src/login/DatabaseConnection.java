package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/login_app";
        String username = //TODO: nie wiem gdzie to się ustawiało
                String password =  //TODO: nie pamietam, gdzie to sie ustawiało
        return DriverManager.getConnection(url, username, password);
    }

}
