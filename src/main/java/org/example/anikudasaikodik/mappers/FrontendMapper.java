package org.example.anikudasaikodik.mappers;

import org.example.anikudasaikodik.dto.frontendDTO.FrontendAnimeDTO;
import org.example.anikudasaikodik.dto.frontendDTO.FrontendGenreDTO;
import org.example.anikudasaikodik.dto.frontendDTO.FrontendImageDTO;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Genre;
import org.example.anikudasaikodik.models.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FrontendMapper {
    public FrontendMapper() {

    }
    public FrontendAnimeDTO animeToFrontendDTO(Anime anime) {
        FrontendAnimeDTO frontendAnimeDTO = new FrontendAnimeDTO();
        if (anime != null) {
            frontendAnimeDTO.setId(anime.getId());
            frontendAnimeDTO.setName(anime.getName());
            frontendAnimeDTO.setDescription(anime.getDescription());
            frontendAnimeDTO.setScore(anime.getScore());
            frontendAnimeDTO.setStatus(anime.getStatus());
            frontendAnimeDTO.setKind(anime.getKind());
            frontendAnimeDTO.setEpisodes(anime.getEpisodes());
            frontendAnimeDTO.setImageDTO(imageToFrontendDTO(anime.getImage()));
            frontendAnimeDTO.setGenresDTO(genresToFrontendDTO(anime.getGenres())); // ✅ правильный вызов

        }
        return frontendAnimeDTO;
    }

    public FrontendImageDTO imageToFrontendDTO(Image image) {
        FrontendImageDTO frontendImageDTO = new FrontendImageDTO();
        if (image != null) {
            frontendImageDTO.setPreview(image.getPreview());
            frontendImageDTO.setOriginal(image.getOriginal());
            frontendImageDTO.setX96(image.getX96());
            frontendImageDTO.setX48(image.getX48());
        }
        return frontendImageDTO;
    }
    public List<FrontendGenreDTO> genresToFrontendDTO(List<Genre> genres) {
        if (genres == null) return List.of();
        return genres.stream()
                .map(this::genreToFrontendDTO)
                .collect(Collectors.toList());
    }
    public FrontendGenreDTO genreToFrontendDTO(Genre genre) {
        if (genre == null) return null;
        FrontendGenreDTO frontendGenreDTO = new FrontendGenreDTO();
        frontendGenreDTO.setId(genre.getId());
        frontendGenreDTO.setName(genre.getName());
        frontendGenreDTO.setRussian(genre.getRussian());
        frontendGenreDTO.setEntry_type(genre.getEntryType());
        frontendGenreDTO.setKind(genre.getKind());
        return frontendGenreDTO;
    }
}
