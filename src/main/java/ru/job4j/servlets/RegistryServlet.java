package ru.job4j.servlets;

import ru.job4j.models.Author;
import ru.job4j.store.AuthorRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AuthorRepository authorRepository = new AuthorRepository();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Author user = authorRepository.getAuthorByEmail(email);
        if (user == null) {
            authorRepository.addAuthor(
                    new Author(name, email, password));
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Такой email уже существует");
            req.getRequestDispatcher("registry.jsp").forward(req, resp);
        }
    }
}