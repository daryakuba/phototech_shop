package ru.camera_store.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.camera_store.models.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

@Component
@Service
public class UserService {
    private final SessionFactory sessionFactory;
    private Session session;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
       session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    @Transactional
    public void addUser(User user) {
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public List<User> getUsers() {
        return session.createQuery("select u from User u", User.class).list();
    }

    public User getUser(long id) {
        return session.createQuery("select u from User u where u.id ='" + id + "'", User.class).getSingleResult();
    }
    public User getUser(String password, String login) {
        return (session.createQuery("select u from User u where u.login ='" + login + "' u.password ='" + password + "'", User.class).getSingleResult());
    }

    public Long getIdByLogin(String login) {
        return getUser(login).getId();
    }

    public User getUser(String login) {
        return (session.createQuery("select u from User u where u.login ='" + login + "'", User.class).getSingleResult());
    }

    @Transactional
    public void deleteUser(long id) {
        session.beginTransaction();

        User temp = session.load(User.class, id);
        session.delete(temp);

        session.getTransaction().commit();
    }
}
