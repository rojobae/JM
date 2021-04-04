package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS user (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL," +
            "lastName VARCHAR(255) NOT NULL," +
            "age TINYINT UNSIGNED);";
    private static final String SQL_DROP = "DROP TABLE user;";
    private static final String SQL_DELETE_ALL = "DELETE FROM user;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM user;";

    public void createUsersTable() {
        try (Statement statement = new Util().getStatement(Util.getConnection())) {
            statement.executeUpdate(SQL_CREATE);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = new Util().getStatement(Util.getConnection())) {
            statement.executeUpdate(SQL_DROP);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = new Util().getStatement(Util.getConnection())) {
            statement.executeUpdate(String.format(
                    "INSERT INTO user (name, lastName, age) VALUES (\"%s\", \"%s\", %d);", name, lastName, age));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = new Util().getStatement(Util.getConnection())) {
            statement.executeUpdate(String.format("DELETE FROM user WHERE id = %d;", id));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = new Util().getStatement(Util.getConnection())) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
            }
            return users;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        try (Statement statement = new Util().getStatement(Util.getConnection())) {
            statement.executeUpdate(SQL_DELETE_ALL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }
}
