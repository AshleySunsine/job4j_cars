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

import java.util.List;
import java.util.function.Function;

public class CarMarkAndBodyRepository {
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

    public CarMarkAndBodyRepository() {
    }

    public int addCarMark(CarMark mark) {
        return this.tx(session -> {
            Integer carMarkId = (Integer) session.save(mark);
            return carMarkId;
        });
    }

    public boolean deleteCarMark(int id) {
        this.tx(session -> {
            session.createQuery("delete from CarMark where id = :aid")
                    .setParameter("aid", id)
                    .executeUpdate();
            return true;
        });
        return true;
    }
    public int addCarBody(CarBody carBody) {
        return this.tx(session -> {
            Integer carId = (Integer) session.save(carBody);
            return carId;
        });
    }

    public boolean deleteCarBody(int id) {
        this.tx(session -> {
            session.createQuery("delete from CarBody where id = :aid")
                    .setParameter("aid", id)
                    .executeUpdate();
            return true;
        });
        return true;
    }

    public List<CarMark> getAllCarMarks() {
        List<CarMark> ads;
        ads = this.tx(session -> session.createQuery("select a from CarMark a ", CarMark.class)
                .getResultList());
        return ads;
    }

    public List<CarBody> getAllCarBodies() {
        List<CarBody> ads;
        ads = this.tx(session -> session.createQuery("select a from CarBody a ", CarBody.class)
                .getResultList());
        return ads;
    }
}
