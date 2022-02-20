package ru.job4j.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "advrt")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    @NotNull
    @Column(nullable = false)
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "bodytype_id")
    @NotNull
    @Column(nullable = false)
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "pathsOfFoto_id")
    private Foto fotos;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    @Column(nullable = false)
    private Author author;

    public Advert() {
    }

    public Advert(int id, String description, Mark mark, BodyType bodyType, Foto fotos, Author author) {
        this.id = id;
        this.description = description;
        this.mark = mark;
        this.bodyType = bodyType;
        this.fotos = fotos;
        this.author = author;

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

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Foto getFotos() {
        return fotos;
    }

    public void setFotos(Foto fotos) {
        this.fotos = fotos;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
        return id == advert.id && Objects.equals(description, advert.description) && Objects.equals(mark, advert.mark) && Objects.equals(bodyType, advert.bodyType) && Objects.equals(fotos, advert.fotos) && Objects.equals(author, advert.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, mark, bodyType, fotos, author);
    }
}
