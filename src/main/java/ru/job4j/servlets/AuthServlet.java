package ru.job4j.servlets;

import ru.job4j.models.Author;
import ru.job4j.models.CarBody;
import ru.job4j.models.CarMark;
import ru.job4j.store.AuthorRepository;
import ru.job4j.store.CarMarkAndBodyRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /**
       * Это для заполнения таблиц кузовов и марок. Используется для реальной базы. ЭТО НУЖНО!
       * CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        carMarkAndBodyRepository.addCarBody(new CarBody("Body_1"));
        carMarkAndBodyRepository.addCarBody(new CarBody("Body_2"));
        carMarkAndBodyRepository.addCarBody(new CarBody("Body_3"));
        carMarkAndBodyRepository.addCarMark(new CarMark("Mark_1"));
        carMarkAndBodyRepository.addCarMark(new CarMark("Mark_2"));
        carMarkAndBodyRepository.addCarMark(new CarMark("Mark_3"));**/

        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        AuthorRepository authorRepository = new AuthorRepository();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Author user = authorRepository.getAuthorByEmail(email);
        if ((user != null) && (user.getPassword().equals(password))) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}