package ru.job4j.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String markName;
    private String bodyTypeName;

    @Access(AccessType.FIELD)
    private String pathsToFoto;

    private static final Gson GSON = new GsonBuilder().create();

    public Car() {
    }

    public Car(String markName, String bodyTypeName, String pathsToFoto) {
        this.markName = markName;
        this.bodyTypeName = bodyTypeName;
        this.pathsToFoto = pathsToFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyTypeName() {
        return bodyTypeName;
    }

    public void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public ArrayList<String> getPathsToFoto() {
        return GSON.fromJson(pathsToFoto, ArrayList.class);
    }

    public void addFoto(String foto) {
        ArrayList<String> fotos = GSON.fromJson(pathsToFoto, ArrayList.class);
        fotos.add(foto);
        pathsToFoto = GSON.toJson(fotos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(markName, car.markName) && Objects.equals(bodyTypeName, car.bodyTypeName) && Objects.equals(pathsToFoto, car.pathsToFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, markName, bodyTypeName, pathsToFoto);
    }

    @Override
    public String toString() {
        return "Car{"
               + "id=" + id
               + ", markName='" + markName + '\''
               + ", bodyTypeName='" + bodyTypeName + '\''
               + ", pathsToFoto='" + pathsToFoto + '\''
               + '}';
    }
}
