package ru.job4j.todo.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
public class UserStore implements TransactionStore {

    private final SessionFactory sf;

    public UserStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> create(User user) {
        try {
            tx(session -> session.save(user), sf);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public Optional<User> findByEmailAndPwd(String email, String password) {
        return tx(session -> session.createQuery(
                    "from User u where u.email = :email and u.password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResultOptional(), sf);
    }
}
