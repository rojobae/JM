package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Дима", "Иванов", (byte) 21);
        userService.saveUser("Лёша", "Семёнов", (byte) 19);
        userService.saveUser("Маша", "Симонова", (byte) 8);
        userService.saveUser("Коля", "Романов", (byte) 55);
        for (User user:
             userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
