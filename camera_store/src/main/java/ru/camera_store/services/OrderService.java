package ru.camera_store.services;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.camera_store.models.Cart;
import ru.camera_store.models.Order;
import javax.transaction.Transactional;

@Component
@Service
public class OrderService {
    private final SessionFactory sessionFactory;
    private Session session;

    public OrderService(SessionFactory sessionFactory) {
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
    public void updateStatus(Long order_id, String newStatus) {
        session.beginTransaction();
        Order order = session.get(Order.class, order_id);
        order.setStatus(newStatus);

        session.update(order);

        //session.merge(order);
        //session.flush();
        session.getTransaction().commit();
    }

    @Transactional
    public void addOrder(Order order) {
        session.beginTransaction();
        session.saveOrUpdate(order);
        session.getTransaction().commit();
    }

    public List<Order> getUsersOrders(Long user_id) {
        return session.createQuery("select o from Order o where o.user ='" + user_id + "'", Order.class).list();
    }

    public List<Order> getUsersActOrders(Long user_id) {

        return session.createQuery("select o from Order o where o.user.id ='" + user_id + "' and o.status != 'Доставлен'", Order.class).list();
        //return session.createQuery("select p from Order p where p.user ='" + user_id + "'", Order.class).list();
    }

    public List<Order> getOrders() {
        return session.createQuery("select o from Order o", Order.class).list();
    }

    public Order getOrder(long id) {
        return session.createQuery("select o from Order o where o.id ='" + id + "'", Order.class).getSingleResult();
    }

    @Transactional
    public void deleteOrder(long id) {
        session.beginTransaction();
        session.createQuery("delete from Order where id = :id").setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
    }

    public Cart getCart(Long id) {
        return (session.createQuery("select u from Cart u where u.id ='" + id + "'", Cart.class).getSingleResult());
    }
}

