package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT UNSIGNED);").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
            Util.getSessionFactory().close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS user;").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
            Util.getSessionFactory().close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(String.format(
                    "INSERT INTO user (name, lastName, age) VALUES (\"%s\", \"%s\", %d);",
                    name, lastName, age)).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
            Util.getSessionFactory().close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(String.format(
                    "DELETE FROM user WHERE id = %d;", id)).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
            Util.getSessionFactory().close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            List<User> users =  session.createSQLQuery("SELECT * FROM user;").addEntity(User.class).list();
            transaction.commit();
            return users;
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
            Util.getSessionFactory().close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM user;").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            hibernateException.getStackTrace();
            throw hibernateException;
        } finally {
            session.close();
            Util.getSessionFactory().close();
        }
    }
}
