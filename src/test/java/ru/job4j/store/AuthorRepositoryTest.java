package ru.job4j.store;

import org.junit.Test;
import ru.job4j.models.Author;
import static org.junit.Assert.*;

public class AuthorRepositoryTest {
    @Test
    public void whenAdd() {
        Author author = new Author(0, "author1");
        AuthorRepository authorRepository = new AuthorRepository();
        int addId = authorRepository.addAuthor(author);

        Author authorFromBase = authorRepository.getAuthorById(addId);

        assertEquals(author,authorFromBase);
    }

    @Test
    public void whenDeleteAuthor() {
        Author author = new Author(0, "author1");
        AuthorRepository authorRepository = new AuthorRepository();
        int addId = authorRepository.addAuthor(author);

        Author authorFromBase = authorRepository.getAuthorById(addId);
        assertEquals(authorFromBase, author);

        authorRepository.deleteAuthor(addId);
        Author afterDelete = authorRepository.getAuthorById(addId);
        assertNull(afterDelete);
    }
}