package ru.job4j.servlets;

import ru.job4j.models.Advert;
import ru.job4j.models.Author;
import ru.job4j.models.Car;
import ru.job4j.store.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveAdvartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AdRepository adRepository = new AdRepository();

        Author author = (Author) req.getSession().getAttribute("user");
        String advartName = req.getParameter("advartName");
        String description = req.getParameter("description");
        int inputMark = Integer.parseInt(req.getParameter("inputMark"));
        int inputBody = Integer.parseInt(req.getParameter("inputBody"));

        Car car = new Car(inputMark, inputBody, "pathToFoto");
        Advert advart = new Advert(description, car, author);
        int advertId = adRepository.addAdvert(advart);
        System.out.println(advartName + description + inputMark + inputBody + advertId);
        req.getSession().setAttribute("advertId", advertId);
        req.getRequestDispatcher("PhotoUpload.jsp").forward(req, resp);
    }
}
