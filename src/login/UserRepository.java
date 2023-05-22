package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {

    public void register(String userName, String password, String email) {
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

    public void update(String login, String password, String email, int id) {
        String updateUser = "UPDATE users SET login = ?, password = ?, email = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(updateUser)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> findByLogin(String login) {
        String selectSql = "SELECT * FROM users WHERE login = ?";
        return find(login, selectSql);
    }

    public Optional<User> findByEmail(String email) {
        String selectSql = "SELECT * FROM users WHERE email = ?";
        return find(email, selectSql);
    }

    private Optional<User> find(String parameter, String sqlQuery) {
        User user = null;

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                user = new User(id, login, password, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public boolean checkIfLoginExist(String login) {
        return findByLogin(login).isPresent();
    }

    public boolean checkIfEmailExist (String email) {
        return findByEmail(email).isPresent();
    }
}
