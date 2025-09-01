package org.example.anikudasaikodik.models;

import jakarta.persistence.*;

@Entity
@Table(name = "screenshots")
public class Screenshots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String original;
    private String preview;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    public Screenshots() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
