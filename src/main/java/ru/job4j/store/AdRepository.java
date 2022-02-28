package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Advert;
import ru.job4j.models.Car;
import ru.job4j.models.CarBody;
import ru.job4j.models.CarMark;

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
            Car car = advert.getCar();
            CarBody carBody = (CarBody) session.createQuery("from CarBody where id = :adId")
                    .setParameter("adId", car.getBodyTypeId())
                    .uniqueResult();
            CarMark carMark = (CarMark) session.createQuery("from CarMark where id = :adId")
                    .setParameter("adId", car.getMarkId())
                    .uniqueResult();
            System.out.println(carBody.getBodyType() + " " + carMark.getMark());
            car.setBodyType(carBody.getBodyType());
            car.setMarkName(carMark.getMark());
            Integer advertId = (Integer) session.save(advert);
            return advertId;

        });
    }

    public void setPhotoAdvert(int advertId, String photoPath) {
        this.tx(session -> {
            Advert advert = session.load(Advert.class, advertId);
            Car car = advert.getCar();
            car.addFoto(photoPath);
            return 1;
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

    public List<Advert> getAdvertByMarkId(int markId) {
        List<Advert> ads;
        ads = this.tx(session -> session.createQuery("select a from Advert a "
                + "join fetch a.author "
                + "where a.car.markId = :aMarkId", Advert.class)
                .setParameter("aMarkId", markId)
                .getResultList());
        return ads;
    }

    public List<Advert> getAllAdvert() {
        List<Advert> ads;
        ads = this.tx(session -> session.createQuery("select a from Advert a", Advert.class)
                .getResultList());
        return ads;
    }

}
