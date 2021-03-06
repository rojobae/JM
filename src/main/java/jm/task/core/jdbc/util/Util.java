package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/user";

    // Получение соединения с базой данных
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    // Получение statement для работы с базой данных
    public static Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }
}
