package ru.job4j.todo.store;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;

@Repository
public class CategoryDBStore implements TransactionStore {
    private final SessionFactory sf;

    public CategoryDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Category> findAll() {
        return tx(session ->
                session.createQuery("from Category").list(), sf);
    }
}
