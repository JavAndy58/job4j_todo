package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Item;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemStore {

//    private final SessionFactory sf;

    List<Item> items = new ArrayList<>();
    int number = 0;

    public List<Item> findAll() {
        return items;
    }

    public Item create(Item item) {
        item.setId(++number);
        items.add(item);
        return null;
    }


//    public ItemStore(SessionFactory sf) {
//        this.sf = sf;
//    }
//
//    public List<Item> findAll() {
//        Session session = sf.openSession();
//        session.beginTransaction();
//        List result = session.createQuery("from ru.job4j.todo.model.Item").list();
//        session.getTransaction().commit();
//        return result;
//    }
//
//    public Item create(Item item) {
//        Session session = sf.openSession();
//        session.beginTransaction();
//        session.save(item);
//        session.getTransaction().commit();
//        session.close();
//        return item;
//    }




}
