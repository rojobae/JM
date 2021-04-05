package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getStatement(Util.getConnection())) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT UNSIGNED);");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getStatement(Util.getConnection())) {
            statement.executeUpdate("DROP TABLE IF EXISTS user;");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.getStatement(Util.getConnection())) {
            statement.executeUpdate(String.format(
                    "INSERT INTO user (name, lastName, age) VALUES (\"%s\", \"%s\", %d);", name, lastName, age));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Statement statement = Util.getStatement(Util.getConnection())) {
            statement.executeUpdate(String.format("DELETE FROM user WHERE id = %d;", id));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = Util.getStatement(Util.getConnection())) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");

            while (resultSet.next()) {
                users.add(new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
            }

            return users;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getStatement(Util.getConnection())) {
            statement.executeUpdate("DELETE FROM user;");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }
}
