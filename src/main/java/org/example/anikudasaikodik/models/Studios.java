package org.example.anikudasaikodik.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studios")
public class Studios {
    @Id
    private Long id;

    private String name;

    @Column(name = "filtered_name")
    private String filteredName;

    private Boolean real;


    private String  image;

    @ManyToMany(mappedBy = "studios")
    private List<Anime> animes = new ArrayList<>();

    public Studios() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilteredName() {
        return filteredName;
    }

    public void setFilteredName(String filteredName) {
        this.filteredName = filteredName;
    }

    public Boolean getReal() {
        return real;
    }

    public void setReal(Boolean real) {
        this.real = real;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }
    // геттеры/сеттеры
}
