package ru.job4j.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PrimitiveIterator;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int markId;

    private int bodyTypeId;

    private String markName;
    private String bodyType;

    @Access(AccessType.FIELD)
    private String pathsToFoto;

    private static final Gson GSON = new GsonBuilder().create();

    public Car() {
    }

    public Car(int markId, int bodyTypeId, String pathsToFoto) {
        this.markId = markId;
        this.bodyTypeId = bodyTypeId;
        this.pathsToFoto = pathsToFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(int bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setPathsToFoto(String pathsToFoto) {
        this.pathsToFoto = pathsToFoto;
    }

    public String getPathsToFoto() {
        return pathsToFoto;
    }

    public void addFoto(String foto) {
        pathsToFoto = foto;
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
        return id == car.id && markId == car.markId && bodyTypeId == car.bodyTypeId && Objects.equals(pathsToFoto, car.pathsToFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, markId, bodyTypeId, pathsToFoto);
    }
}
