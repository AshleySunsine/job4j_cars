package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Car;

import java.util.function.Function;

public class CarRepository {
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

    public CarRepository() {
    }

    public int addCar(Car car) {
        return this.tx(session -> {
            Integer carId = (Integer) session.save(car);
            return carId;
        });
    }

    public boolean deleteCar(int id) {
        this.tx(session -> {
            session.createQuery("delete from Car where id = :aid")
                    .setParameter("aid", id)
                    .executeUpdate();
            return true;
        });
        return true;
    }

    public Car getCarById(int carId) {
        return (Car) this.tx(session -> {
            return session.createQuery("from Car where id = :adId")
                    .setParameter("adId", carId)
                    .uniqueResult();
        });
    }
}
