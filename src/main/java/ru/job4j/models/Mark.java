package ru.job4j.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String mark_name;

    public Mark() {
    }

    public Mark(int id, String mark_name) {
        this.id = id;
        this.mark_name = mark_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark_name() {
        return mark_name;
    }

    public void setMark_name(String mark_name) {
        this.mark_name = mark_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return id == mark.id && Objects.equals(mark_name, mark.mark_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark_name);
    }
}
