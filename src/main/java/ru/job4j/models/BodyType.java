package ru.job4j.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodytype")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String bodyTypeName;

    public BodyType() {
    }

    public BodyType(int id, String bodyTypeName) {
        this.id = id;
        this.bodyTypeName = bodyTypeName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyType bodyType = (BodyType) o;
        return id == bodyType.id && Objects.equals(bodyTypeName, bodyType.bodyTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bodyTypeName);
    }
}
