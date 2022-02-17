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
    List<Advert> advrt_list = new ArrayList<>();

    public Author() {
    }

    public Author(int id, String name, List<Advert> advrt_list) {
        this.id = id;
        this.name = name;
        this.advrt_list = advrt_list;
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

    public List<Advert> getAdvrt_list() {
        return advrt_list;
    }

    public void setAdvrt_list(List<Advert> advrt_list) {
        this.advrt_list = advrt_list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(name, author.name) && Objects.equals(advrt_list, author.advrt_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, advrt_list);
    }
}
