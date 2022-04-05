package ru.job4j.todo;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManagerFactory;


@SpringBootApplication
public class Main {
//    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//            .configure().build();


//    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
