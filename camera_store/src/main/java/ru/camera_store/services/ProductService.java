package ru.camera_store.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.camera_store.models.Product;
import ru.camera_store.models.Category;

import javax.transaction.Transactional;

@Component
@Service
public class ProductService {
    private final SessionFactory sessionFactory;
    private Session session;

    public ProductService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public Category getCategoryByProduct(Long productId) {
        return session.createQuery("from Product where id =:id", Product.class)
                .setParameter("id",productId).getSingleResult().getCategory();
    }

    public List<Product> getProductsByCategory(Long id) {
        return session.createQuery("select p from Product p where p.category.id = '" + id + "'", Product.class).list();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    @Transactional
    public void addProduct(Product product) {
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
    }

    public List<Product> getProducts() {
        return session.createQuery("select p from Product p", Product.class).list();
    }

    public Product getProduct(long id) {
        return session.createQuery("select p from Product p where p.id ='" + id + "'", Product.class).getSingleResult();
    }

    @Transactional
    public void deleteProduct(long id) {
        session.beginTransaction();

        Product temp = session.load(Product.class, id);
        session.delete(temp);

        session.getTransaction().commit();
    }

    @Transactional
    public void updateProductStatus(Long product_id, String newStatus){
        session.beginTransaction();
        Product product = session.get(Product.class, product_id);
        product.setStatus(newStatus);
        //session.merge(product);
        //session.persist(product);
        //session.flush();

        session.update(product);
        session.getTransaction().commit();
    }
}