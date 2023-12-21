package ru.camera_store.services;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.camera_store.models.Cart;
import ru.camera_store.models.Product;

@Component
@Service
public class CartService {
    private final SessionFactory sessionFactory;
    private Session session;

    public CartService(SessionFactory sessionFactory) {
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
    public void addCart(Cart cart) {
        session.beginTransaction();
        session.saveOrUpdate(cart);
        session.getTransaction().commit();
    }

    @Transactional
    public void updateProducts(Long cart_id){
        session.beginTransaction();
        Cart cart = session.get(Cart.class, cart_id);
        List<Product> products = cart.getProducts();
        products.clear();
        cart.setProducts(products);
        //session.persist(cart);
        session.update(cart);
        //session.flush();
        session.getTransaction().commit();
    }

    public Cart getCart(Long id) {
        return (session.createQuery("select u from Cart u where u.id ='" + id + "'", Cart.class).getSingleResult());
    }

    @Transactional
    public void deleteCart(long id) {
        session.beginTransaction();
        Cart temp = session.load(Cart.class, id);
        session.delete(temp);
        session.getTransaction().commit();
    }

    @Transactional
    public void addProduct(Long cart_id, Long product_id){
        session.beginTransaction();
        Cart temp = session.get(Cart.class, cart_id);
        temp.setProduct(getProduct(product_id));
        session.update(temp);
        session.getTransaction().commit();
    }

    @Transactional
    public void removeProduct(Long cart_id, Long product_id){
        session.beginTransaction();
        Cart temp = session.get(Cart.class, cart_id);
        temp.removeProduct(getProduct(product_id));
        session.update(temp);
        session.getTransaction().commit();
    }

    public Product getProduct(long id) {
        return session.createQuery("select p from Product p where p.id ='" + id + "'", Product.class).getSingleResult();
    }

    public Cart getCart(long cart_id){
        return session.createQuery("select c from Cart c where c.id ='" + cart_id + "'", Cart.class).getSingleResult();
    }


    public Long getCartByUser(long id) {
        return session.createQuery("select c from Cart c where c.user.id ='" + id + "'", Cart.class).getSingleResult().getId();
    }
}