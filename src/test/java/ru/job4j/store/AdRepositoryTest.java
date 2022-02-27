package ru.job4j.store;

import org.junit.Test;
import ru.job4j.models.*;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class AdRepositoryTest {
   @Test
    public void whenAdd() {
        CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        int bodyId = carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
        int markId = carMarkAndBodyRepository.addCarMark(new CarMark("mark_1"));
        Car car = new Car(markId, bodyId, "d:/a");
        Author author = new Author("author1", "aaa", "aaa");
        String description = "description1";

        Advert advert = new Advert(0, description, car, author);

        AdRepository adRepository = new AdRepository();
        int addId = adRepository.addAdvert(advert);

        Advert advertFromBase = adRepository.getAdvertById(addId);

        assertEquals(advert, advertFromBase);
    }

    @Test
    public void whenGetByMark() {
        Author author = new Author("author1", "aaa", "aaa");
        String description = "description1";

        CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        int bodyId = carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
        int markId = carMarkAndBodyRepository.addCarMark(new CarMark("mark_1"));
        Car car = new Car(markId, bodyId, "d:/a");
        Advert advert = new Advert(0, description, car, author);
        AdRepository adRepository = new AdRepository();

        adRepository.addAdvert(advert);
        List<Advert> advertsFromBase = adRepository.getAdvertByMarkId(markId);

        assertEquals(advert, advertsFromBase.get(0));
    }

    @Test
    public void whenDeleteAdvert() {
        Author author = new Author("author1", "aaa", "aaa");
        String description = "description1";

        CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        int bodyId = carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
        int markId = carMarkAndBodyRepository.addCarMark(new CarMark("mark_1"));
        Car car = new Car(markId, bodyId, "d:/a");

        Advert advert = new Advert(0, description, car, author);
        AdRepository adRepository = new AdRepository();

        int addId = adRepository.addAdvert(advert);
        List<Advert> listBeforeDelete = adRepository.getAdvertByMarkId(markId);
        assertThat(listBeforeDelete.toArray().length, is(1));

        adRepository.deleteAdvert(addId);

        List<Advert> listAfterDelete = adRepository.getAdvertByMarkId(markId);
        assertThat(listAfterDelete.toArray().length, is(0));
    }

    @Test
    public void whenGetByDay() {
        Author author = new Author("author1", "aaa", "aaa");
        String description = "description1";

        CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        int bodyId = carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
        int markId = carMarkAndBodyRepository.addCarMark(new CarMark("mark_1"));
        Car car = new Car(markId, bodyId, "d:/a");

        Advert advert = new Advert(0, description, car, author);
        System.out.println(advert.getCreated());
        AdRepository adRepository = new AdRepository();

        adRepository.addAdvert(advert);
        List<Advert> advertsFromBase = adRepository.getAdvertByDay();

        assertThat(advertsFromBase.toArray().length, is(1));
    }


}