package ru.job4j.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Access(AccessType.FIELD)
    private String pathsToFoto;

    private static final Gson GSON = new GsonBuilder().create();

    public Foto() {
    }

    public Foto(int id, String jsonOfPathToFoto) {
        this.id = id;
        this.pathsToFoto = jsonOfPathToFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Foto foto = (Foto) o;
        return id == foto.id && Objects.equals(pathsToFoto, foto.pathsToFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pathsToFoto);
    }
}
