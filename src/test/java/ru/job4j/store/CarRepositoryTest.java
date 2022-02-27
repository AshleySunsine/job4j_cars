package ru.job4j.store;

import org.junit.Test;
import ru.job4j.models.Car;
import ru.job4j.models.CarBody;
import ru.job4j.models.CarMark;

import static org.junit.Assert.*;

public class CarRepositoryTest {
   @Test
    public void whenAdd() {
       CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
       int bodyId = carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
       int markId = carMarkAndBodyRepository.addCarMark(new CarMark("mark_1"));
       Car car = new Car(markId, bodyId, "d:/a");

        CarRepository carRepository = new CarRepository();
        int addId = carRepository.addCar(car);

        Car carFromBase = carRepository.getCarById(addId);

        assertEquals(car, carFromBase);
    }

    @Test
    public void whenDeleteCar() {
        CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        int bodyId = carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
        int markId = carMarkAndBodyRepository.addCarMark(new CarMark("mark_1"));
        Car car = new Car(markId, bodyId, "d:/a");
        CarRepository carRepository = new CarRepository();
        int addId = carRepository.addCar(car);

        Car beforeDelete = carRepository.getCarById(addId);
        assertEquals(beforeDelete, car);
        carRepository.deleteCar(addId);

        Car afterDelete = carRepository.getCarById(addId);
        assertNull(afterDelete);
    }

}