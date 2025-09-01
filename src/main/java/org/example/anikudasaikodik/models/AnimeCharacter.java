package org.example.anikudasaikodik.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "anime_character")
public class AnimeCharacter {

    @Id
    private Long id;

    // Имя оригинальное (например японское)
    @Column(nullable = false)
    private String name;

    // Русское имя (если есть)
    private String russian;

    // Краткое описание персонажа
    @Column(columnDefinition = "TEXT")
    private String description;

    // Картинка
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    // Связь многие-ко-многим с аниме
    @ManyToMany(mappedBy = "animeCharacters")
    private List<Anime> animes = new ArrayList<>();

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    // --- геттеры/сеттеры ---

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

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }

    // public Set<Manga> getMangas() {
    //    return mangas;
    //}

    // public void setMangas(Set<Manga> mangas) {
    //    this.mangas = mangas;
    //}
}
