package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.job4j.models.Advert;
import ru.job4j.store.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IndexServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();

        AdRepository adRepository = new AdRepository();
        List<Advert> advertList = new ArrayList<>();
                advertList = adRepository.getAllAdvert();
        System.out.println("************  " + advertList);
        List<JsonObject> jsonList = new ArrayList<>();
        for (var i : advertList) {
         JsonObject json = new JsonObject();
         json.addProperty("advertId", i.getId());
         json.addProperty("advertDescript", i.getDescription());
         json.addProperty("advertCreate", i.getCreated());
         json.addProperty("advertAuthorName", i.getAuthor().getName());
         json.addProperty("advertAuthorEmail", i.getAuthor().getEmail());
         json.addProperty("advertCarMark", i.getCar().getMarkName());
         json.addProperty("advertCarbody", i.getCar().getBodyType());
         jsonList.add(json);
        }

        System.out.println(jsonList);
        output.write((jsonList.toString()).getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
