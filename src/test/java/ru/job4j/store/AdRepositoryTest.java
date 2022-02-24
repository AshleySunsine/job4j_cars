package ru.job4j.store;

import org.junit.Test;
import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AdRepositoryTest {
    @Test
    public void whenAdd() {
        Author author = new Author(0, "author1");
        String description = "description1";
        Mark mark = new Mark(0, "mark1");
        BodyType bodyType = new BodyType(0, "body1");
        Foto foto = new Foto(0, "d:/a");
        Advert advert = new Advert(0, description, mark, bodyType, foto, author);

        AdRepository adRepository = new AdRepository();
        adRepository.addAdvert(advert);

        List<Advert> list = adRepository.getAdvertByMark("mark1");
        list.forEach(System.out::println);

    }



}