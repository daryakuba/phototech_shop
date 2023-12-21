package ru.camera_store.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.camera_store.models.Category;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

@Component
@Service
public class CategoryService {
    private final SessionFactory sessionFactory;
    private Session session;

    public CategoryService(SessionFactory sessionFactory) {
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
    public void addCategory(Category category) {
        session.beginTransaction();
        session.saveOrUpdate(category);
        session.getTransaction().commit();
    }

    public List<Category> getCategories() {
        return session.createQuery("select c from Category c", Category.class).list();
    }

    public List<Category> getCategory(long id) {
        return session.createQuery("select c from Category c where c.id ='" + id + "'", Category.class).list();
    }

    @Transactional
    public void deleteCategory(long id) {
        session.beginTransaction();

        Category temp = session.load(Category.class, id);
        session.delete(temp);

        session.getTransaction().commit();
    }
}
