package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Item;
import java.util.List;

@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.todo.model.Item").list();
        session.getTransaction().commit();
        return result;
    }

    public Item findById(Integer id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
    
    public List<Item> findCompleted() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item where done = true").list();
        session.getTransaction().commit();
        return result;
    }

    public List<Item> findNew() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item i order by i.created desc").list();
        session.getTransaction().commit();
        return result;
    }

    public Item create(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void update(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }
}
