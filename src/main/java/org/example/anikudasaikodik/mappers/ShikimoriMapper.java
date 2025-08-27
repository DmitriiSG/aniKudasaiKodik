package org.example.anikudasaikodik.mappers;

import lombok.Data;
import org.example.anikudasaikodik.dto.shikimoriDTO.AnimeDTO;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Image;
import org.springframework.stereotype.Component;

@Component
public class ShikimoriMapper {

    public Anime mapToEntity(AnimeDTO dto) {
        Anime anime = new Anime();
        anime.setId(dto.getId());
        anime.setName(dto.getName());
        anime.setRussian(dto.getRussian());
        anime.setKind(dto.getKind());
        anime.setEpisodes(dto.getEpisodes());
        anime.setStatus(dto.getStatus());
        anime.setScore(dto.getScore() != null ? String.valueOf(dto.getScore()) : null);

        if (dto.getImage() != null) {
            Image img = new Image();
            img.setOriginal(dto.getImage().getOriginal());
            img.setPreview(dto.getImage().getPreview());
            img.setX96(dto.getImage().getX96());
            anime.setImage(img);
        }

        return anime;
    }
}
