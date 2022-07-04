package ru.job4j.todo.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import java.util.List;

@Repository
public class ItemStore implements TransactionStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.Item").list(), sf
        );
    }

    public Item findById(Integer id) {
        return this.tx(
                session -> session.get(Item.class, id), sf
        );
    }

    public void updateById(Integer id) {
         this.tx(
                session -> {
                    session.createQuery("update Item i set i.done = true where i.id = :fId")
                            .setParameter("fId", id)
                            .executeUpdate();
                    return new Object();
                }, sf
        );
    }

    public void deleteById(Integer id) {
        this.tx(
                session -> {
                    session.createQuery("delete from Item where id = :fId")
                            .setParameter("fId", id)
                            .executeUpdate();
                    return new Object();
                }, sf
        );
    }

    public List<Item> findCompleted() {
        return this.tx(
                session -> session.createQuery("from Item where done = true").list(), sf
        );
    }

    public List<Item> findNew() {
        return this.tx(
                session -> session.createQuery("from Item i order by i.created desc").list(), sf
        );
    }

    public Item create(Item item, List<String> idsCat) {

        this.tx(
                session -> {
                    for (String id : idsCat) {
                        Category category = session.find(Category.class, Integer.parseInt(id));
                        item.addCategories(category);
                    }
                    return session.save(item);
                }, sf);
        return item;
    }

    public void update(Item item) {
        this.tx(
                session -> {
                    session.update(item);
                    return new Object();
                }, sf
        );
    }
}
