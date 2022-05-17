package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Item;
import java.util.List;
import java.util.function.Function;

@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.Item").list()
        );
    }

    public Item findById(Integer id) {
        return this.tx(
                session -> session.get(Item.class, id)
        );
    }

    public void updateById(Integer id) {
         this.tx(
                session -> {
                    session.createQuery("update Item i set i.done = true where i.id = :fId")
                            .setParameter("fId", id)
                            .executeUpdate();
                    return new Object();
                }
        );
    }

    public void deleteById(Integer id) {
        this.tx(
                session -> {
                    session.createQuery("delete from Item where id = :fId")
                            .setParameter("fId", id)
                            .executeUpdate();
                    return new Object();
                }
        );
    }

    public List<Item> findCompleted() {
        return this.tx(
                session -> session.createQuery("from Item where done = true").list()
        );
    }

    public List<Item> findNew() {
        return this.tx(
                session -> session.createQuery("from Item i order by i.created desc").list()
        );
    }

    public Item create(Item item) {
        return this.tx(
                session -> {
                    session.save(item);
                    return item;
                }
        );
    }

    public void update(Item item) {
        this.tx(
                session -> {
                    session.update(item);
                    return new Object();
                }
        );

    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
