package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {

    public void create(String userName, String password, String email) {
        String createUser = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(createUser)) {

                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String userName) {

    }

    public void update(String userName, String password) {

    }

    public void find(String userName) {

    }

    public boolean checkIfUserExist(String userName) {

        return true;

    }

    public boolean checkIfCorrectPassword(String password) {
        return true;

    }




}
