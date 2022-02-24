package ru.job4j.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @NotNull
    private Author author;

    public Advert() {
    }

    public Advert(int id, String description, Car car, Author author) {
        this.id = id;
        this.description = description;
        this.car = car;
        this.author = author;
        this.created =  new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        return id == advert.id && Objects.equals(description, advert.description) && Objects.equals(created, advert.created) && Objects.equals(car, advert.car) && Objects.equals(author, advert.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, car, author);
    }

    @Override
    public String toString() {
        return "Advert{"
              + "id=" + id
              + ", description='" + description + '\''
              + ", created=" + created
              + ", car=" + car
              + ", author=" + author
              + '}';
    }
}
