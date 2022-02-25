package ru.job4j.store;

import org.junit.Test;
import ru.job4j.models.Car;
import static org.junit.Assert.*;

public class CarRepositoryTest {
    @Test
    public void whenAdd() {
        Car car = new Car("mark1", "body1", "d:/a");

        CarRepository carRepository = new CarRepository();
        int addId = carRepository.addCar(car);

        Car carFromBase = carRepository.getCarById(addId);

        assertEquals(car, carFromBase);
    }

    @Test
    public void whenDeleteCar() {
        Car car = new Car("mark1", "body1", "d:/a");
        CarRepository carRepository = new CarRepository();
        int addId = carRepository.addCar(car);

        Car beforeDelete = carRepository.getCarById(addId);
        assertEquals(beforeDelete, car);
        carRepository.deleteCar(addId);

        Car afterDelete = carRepository.getCarById(addId);
        assertNull(afterDelete);
    }

}