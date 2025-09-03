package org.example.anikudasaikodik.dto.frontendDTO;

import org.example.anikudasaikodik.models.Anime;

public class FrontendScreenshotsDTO {
    private Long id;
    private String original;
    private String preview;
    private Anime anime;


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
