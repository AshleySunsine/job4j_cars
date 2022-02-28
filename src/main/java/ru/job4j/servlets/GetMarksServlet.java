package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.models.CarMark;
import ru.job4j.store.CarMarkAndBodyRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetMarksServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        CarMarkAndBodyRepository carMarkAndBodyRepository = new CarMarkAndBodyRepository();
        List<CarMark> marks = carMarkAndBodyRepository.getAllCarMarks();
        String marksJSON = GSON.toJson(marks);
        output.write(marksJSON.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
