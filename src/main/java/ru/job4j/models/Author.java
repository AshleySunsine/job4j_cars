package ru.job4j.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Advert> advrtList = new ArrayList<>();

    public Author() {
    }

    public Author(int id, String name, List<Advert> advrtList) {
        this.id = id;
        this.name = name;
        this.advrtList = advrtList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Advert> getAdvrtList() {
        return advrtList;
    }

    public void setAdvrtList(List<Advert> advrtList) {
        this.advrtList = advrtlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return id == author.id && Objects.equals(name, author.name) && Objects.equals(advrtList, author.advrtList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, advrtList);
    }
}