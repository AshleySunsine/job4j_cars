package ru.job4j.store;

import org.junit.Test;
import ru.job4j.models.Author;
import ru.job4j.models.Car;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    @Test
    public void whenAdd() {
        Author author = new Author(0, "author1");
        UserRepository userRepository = new UserRepository();
        int addId = userRepository.addUser(author);

        Author authorFromBase = userRepository.getUserById(addId);

        assertEquals(author,authorFromBase);
    }

    @Test
    public void whenDeleteAuthor() {
        Author author = new Author(0, "author1");
        UserRepository userRepository = new UserRepository();
        int addId = userRepository.addUser(author);

        Author authorFromBase = userRepository.getUserById(addId);
        assertEquals(authorFromBase, author);

        userRepository.deleteUser(addId);
        Author afterDelete = userRepository.getUserById(addId);
        assertNull(afterDelete);
    }
}