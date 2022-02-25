package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Author;

import java.util.function.Function;

public class AuthorRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public AuthorRepository() {
    }

    public int addAuthor(Author author) {
        return this.tx(session -> {
            Integer authorId = (Integer) session.save(author);
            return authorId;
        });
    }

    public boolean deleteAuthor(int id) {
        this.tx(session -> {
            session.createQuery("delete from Author where id = :aid")
                    .setParameter("aid", id)
                    .executeUpdate();
            return true;
        });
        return true;
    }

    public Author getAuthorById(int authorId) {
        return (Author) this.tx(session -> {
            return session.createQuery("from Author where id = :adId")
                    .setParameter("adId", authorId)
                    .uniqueResult();
        });
    }
}
