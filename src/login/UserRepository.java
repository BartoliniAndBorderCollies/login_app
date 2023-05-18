package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {

    public void create(String userName, String password, String email) {
        String createUser = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(createUser)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        String deleteUser = "DELETE FROM users WHERE login = ?";

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(deleteUser)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        String updateUser = "UPDATE users SET login = ?, password = ?, email = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(updateUser)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
