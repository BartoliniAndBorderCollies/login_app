package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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

    public Optional<User> find(String userName) {
        User user = null;

        String findUser = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(findUser)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int foundUserId = resultSet.getInt("id");
                String foundLogin = resultSet.getString("login");
                String foundPassword = resultSet.getString("password");
                String foundEmail = resultSet.getString("email");
                user = new User(foundUserId, foundLogin, foundPassword, foundEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public boolean checkIfLoginExist(String userName) {
        return find(userName).isPresent();
    }

    public boolean checkIfCorrectPassword(String password) {
        return true;

    }


}
