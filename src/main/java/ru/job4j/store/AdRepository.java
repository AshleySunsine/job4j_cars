package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Advert;

import java.util.ArrayList;
import java.util.Date;
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

    public int addAdvert(Advert advert) {
       return this.tx(session -> {
            Integer advertId = (Integer) session.save(advert);
            return advertId;
        });
    }

    public boolean deleteAdvert(int id) {
        this.tx(session -> {
            session.createQuery("delete from Advert where id = :aid")
                    .setParameter("aid", id)
                    .executeUpdate();
            return true;
        });
        return true;
    }

    public Advert getAdvertById(int advertId) {
       return (Advert) this.tx(session -> {
          return session.createQuery("from Advert where id = :adId")
                   .setParameter("adId", advertId)
                  .uniqueResult();
        });
    }

    public List<Advert> getAdvertByDay() {
        List<Advert> ads;
        ads = this.tx(session -> {
            Date calend = new Date(System.currentTimeMillis() - 86400000);
            return session.createQuery("select a from Advert a "
                   + "join fetch a.author "
                   + "where (a.created >= :calend)", Advert.class)
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
                    + "where a.car.pathsToFoto is not NULL", Advert.class).getResultList();
        });
        return ads;
    }

    public List<Advert> getAdvertByMark(String markName) {
        List<Advert> ads;
        ads = this.tx(session -> session.createQuery("select a from Advert a "
                + "join fetch a.author "
                + "where a.car.markName = :aMarkName", Advert.class)
                .setParameter("aMarkName", markName)
                .getResultList());
        return ads;
    }

}
