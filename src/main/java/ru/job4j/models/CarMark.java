package ru.job4j.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CarMark {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String mark;

    public CarMark() {
    }

    public CarMark(String mark) {
        this.mark = mark;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarMark carMark = (CarMark) o;
        return id == carMark.id && Objects.equals(mark, carMark.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark);
    }
}
