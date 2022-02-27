package ru.job4j.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bodyType;

    public CarBody() {
    }

    public CarBody(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBody carBody = (CarBody) o;
        return id == carBody.id && Objects.equals(bodyType, carBody.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bodyType);
    }
}
