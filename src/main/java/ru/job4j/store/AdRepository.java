package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Advert;
import ru.job4j.models.Mark;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

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

    public boolean addAdvert(Advert advert) {
        this.tx(session -> {
            session.save(advert);
            return true;
        });
        return true;
    }

    public boolean deleteAdvert(int id) {
        this.tx(session -> {
            session.createQuery("delete from adverts where id = :aid")
                    .setParameter("aid", id)
                    .executeUpdate();
            return true;
        });
        return true;
    }

    public List<Advert> getAdvertByDay() {
        List<Advert> ads = new ArrayList<>();
        this.tx(session -> {
            Calendar calend = Calendar.getInstance();
            calend.add(Calendar.DATE, -1);
            return session.createQuery("select a from Advert a "
                   + "join fetch a.author "
                   + "join fetch a.bodyType "
                   + "join fetch a.fotos "
                   + "join fetch a.mark "
                   + "where a.created >= :calend", Advert.class)
                    .setParameter("calend", calend)
                    .getResultList();
        });
        return ads;
    }

    public List<Advert> getAdvertWithFotoOnly() {
        List<Advert> ads = new ArrayList<>();
        this.tx(session -> {
            return session.createQuery("select a from Advert a "
                    + "join fetch a.author "
                    + "join fetch a.bodyType "
                    + "join fetch a.fotos "
                    + "join fetch a.mark "
                    + "where a.fotos is not NULL", Advert.class).getResultList();
        });
        return ads;
    }

    public List<Advert> getAdvertByMark(String markName) {
        List<Advert> ads = new ArrayList<>();
        ads = this.tx(session -> session.createQuery("select a from Advert a "
                + "join fetch a.author "
                + "join fetch a.bodyType "
                + "join fetch a.fotos "
                + "join fetch a.mark "
                + "where a.mark.markName = :aMarkName", Advert.class)
                .setParameter("aMarkName", markName)
                .getResultList());
        return ads;
    }

}
