package org.example.anikudasaikodik.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "genres")
     private List<Anime> anime;

    public Genre() {}

    public Long getId() {
        return id;
    }

    public void setId(Long shikimoriId) {
        this.id = shikimoriId;
    }

    public List<Anime> getAnime() {
        return anime;
    }

    public void setAnime(List<Anime> anime) {
        this.anime = anime;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
