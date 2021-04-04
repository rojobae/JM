package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT UNSIGNED);";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
           throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user;";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String sql = String.format(
                    "INSERT INTO user (name, lastName, age) VALUES (\"%s\", \"%s\", %d);", name, lastName, age);
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String sql = String.format("DELETE FROM user WHERE id = %d;", id);
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String sql = "SELECT * FROM user;";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            ArrayList<User> users = new ArrayList<User>(query.list());
            transaction.commit();
            return users;
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM user;";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
        }
    }
}
