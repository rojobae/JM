package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Statement statement = new Util().getStatement(Util.getConnection());
    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS User (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL," +
            "lastName VARCHAR(255) NOT NULL," +
            "age TINYINT UNSIGNED);";
    private static final String SQL_DROP = "DROP TABLE User;";
    private static final String SQL_DELETE_ALL = "DELETE FROM User;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM User;";

    public void createUsersTable() {
        try {
            statement.executeUpdate(SQL_CREATE);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate(SQL_DROP);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sqlInsert = "INSERT INTO User (name, lastName, age) VALUES ({0}, {1}, {2});";
            statement.executeUpdate(String.format(sqlInsert, name, lastName, age));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void removeUserById(long id) {
        try {
            String sqlDrop = "DELETE FROM User WHERE id = {0};";
            statement.executeUpdate(String.format(sqlDrop, id));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                users.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getByte(3)));
            }
            return users;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate(SQL_DELETE_ALL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }
}
