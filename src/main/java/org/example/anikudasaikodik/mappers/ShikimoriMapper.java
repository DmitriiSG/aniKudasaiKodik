package org.example.anikudasaikodik.mappers;

import org.example.anikudasaikodik.dto.shikimoriDTO.*;
import org.example.anikudasaikodik.models.*;
import org.example.anikudasaikodik.models.AnimeCharacter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShikimoriMapper {



    // Маппинг только скелета (id, name, russian)
    public Anime mapSkeleton(AnimeDTO dto) {
        Anime anime = new Anime();
        anime.setId(dto.getId());
        anime.setName(dto.getName());
        anime.setRussian(dto.getRussian());
        return anime;
    }
    public void updateAnimeFromDTO(Anime anime, AnimeDTO dto) {
        anime.setKind(dto.getKind());
        anime.setEpisodes(dto.getEpisodes());
        anime.setStatus(dto.getStatus());
        anime.setScore(dto.getScore());
        anime.setAnons(dto.getAnons());
        anime.setDescription(dto.getDescription());
        anime.setDescriptionHtml(dto.getDescriptionHtml());
        anime.setDescriptionSource(dto.getDescriptionSource());
        anime.setDuration(dto.getDuration());
        anime.setEpisodesAired(dto.getEpisodesAired());
        anime.setFavoured(dto.getFavoured());
        anime.setFranchise(dto.getFranchise());
        anime.setLicenseNameRu(dto.getLicenseNameRu());
        anime.setMyanimelistId(dto.getMyanimelistId());
        anime.setNextEpisodeAt(dto.getNextEpisodeAt());
        anime.setOngoing(dto.getOngoing());
        anime.setRating(dto.getRating());
        anime.setReleasedOn(dto.getReleasedOn() != null ? LocalDate.parse(dto.getReleasedOn()) : null);
        anime.setAiredOn(dto.getAiredOn() != null ? LocalDate.parse(dto.getAiredOn()) : null);
        anime.setThreadId(dto.getThreadId());
        anime.setTopicId(dto.getTopicId());
        anime.setUpdatedAt(dto.getUpdatedAt());
        anime.setUrl(dto.getUrl());

        // Image
        if (dto.getImageDTO() != null) {
            if (anime.getImage() == null) {
                anime.setImage(mapImageDTOToEntity(dto.getImageDTO()));
            } else {
                Image img = anime.getImage();
                img.setOriginal(dto.getImageDTO().getOriginal());
                img.setPreview(dto.getImageDTO().getPreview());
                img.setX48(dto.getImageDTO().getX48());
                img.setX96(dto.getImageDTO().getX96());
            }
        }

        // Screenshots
        if (dto.getScreenshotsDTO() != null) {
            anime.getScreenshots().clear();
            for (ScreenshotsDTO sDto : dto.getScreenshotsDTO()) {
                Screenshots s = mapScreenshotDTOToEntity(sDto);
                s.setAnime(anime);
                anime.getScreenshots().add(s);
            }
        }

        // Genres
        if (dto.getGenresDTO() != null) {
            anime.getGenres().clear();
            for (GenreDTO gDto : dto.getGenresDTO()) {
                Genre g = mapGenreDTOToEntity(gDto);
                anime.getGenres().add(g);
            }
        }

        // Studios
        if (dto.getStudiosDTO() != null) {
            anime.getStudios().clear();
            for (StudiosDTO stDto : dto.getStudiosDTO()) {
                Studios st = mapStudioDTOToEntity(stDto);
                anime.getStudios().add(st);
            }
        }

        // Characters

    }

    // Полный маппинг (детали)
    public Anime mapAnimeDTOToEntity(AnimeDTO dto) {
        Anime anime = mapSkeleton(dto);
        anime.setKind(dto.getKind());
        anime.setEpisodes(dto.getEpisodes());
        anime.setStatus(dto.getStatus());
        anime.setScore(dto.getScore());
        anime.setAnons(dto.getAnons());
        anime.setDescription(dto.getDescription());
        anime.setDescriptionHtml(dto.getDescriptionHtml());
        anime.setDescriptionSource(dto.getDescriptionSource());
        anime.setDuration(dto.getDuration());
        anime.setEpisodesAired(dto.getEpisodesAired());
        anime.setFavoured(dto.getFavoured());
        anime.setFranchise(dto.getFranchise());
        anime.setLicenseNameRu(dto.getLicenseNameRu());
        anime.setMyanimelistId(dto.getMyanimelistId());
        anime.setNextEpisodeAt(dto.getNextEpisodeAt());
        anime.setOngoing(dto.getOngoing());
        anime.setRating(dto.getRating());
        anime.setReleasedOn(dto.getReleasedOn() != null ? LocalDate.parse(dto.getReleasedOn()) : null);
        anime.setAiredOn(dto.getAiredOn() != null ? LocalDate.parse(dto.getAiredOn()) : null);
        anime.setThreadId(dto.getThreadId());
        anime.setTopicId(dto.getTopicId());
        anime.setUpdatedAt(dto.getUpdatedAt());
        anime.setUrl(dto.getUrl());

        // Image
        if (dto.getImageDTO() != null) {
            anime.setImage(mapImageDTOToEntity(dto.getImageDTO()));
        }

        // Screenshots
        if (dto.getScreenshotsDTO() != null) {
            List<Screenshots> screenshots = new ArrayList<>();
            for (ScreenshotsDTO sDto : dto.getScreenshotsDTO()) {
                Screenshots s = mapScreenshotDTOToEntity(sDto);
                s.setAnime(anime); // важная обратная связь
                screenshots.add(s);
            }
            anime.setScreenshots(screenshots);
        }

        // Genres
        if (dto.getGenresDTO() != null) {
            List<Genre> genres = new ArrayList<>();
            for (GenreDTO gDto : dto.getGenresDTO()) {
                Genre g = mapGenreDTOToEntity(gDto);
                genres.add(g);
            }
            anime.setGenres(genres);
        }

        // Studios
        if (dto.getStudiosDTO() != null) {
            List<Studios> studios = new ArrayList<>();
            for (StudiosDTO stDto : dto.getStudiosDTO()) {
                Studios st = mapStudioDTOToEntity(stDto);
                studios.add(st);
            }
            anime.setStudios(studios);
        }

        // Characters


        return anime;
    }


    public Image mapImageDTOToEntity(ImageDTO dto) {
        Image image = new Image();
        image.setOriginal(dto.getOriginal());
        image.setPreview(dto.getPreview());
        image.setX96(dto.getX96());
        image.setX48(dto.getX48());
        return image;
    }

    public Genre mapGenreDTOToEntity(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setId(dto.getId());
        return genre;
    }

    public Screenshots mapScreenshotDTOToEntity(ScreenshotsDTO dto) {
        Screenshots screenshots = new Screenshots();
        screenshots.setOriginal(dto.getOriginal());
        screenshots.setPreview(dto.getPreview());
        screenshots.setAnime(dto.getAnime());
        screenshots.setId(dto.getId());

        return screenshots;
    }

    public Studios mapStudioDTOToEntity(StudiosDTO dto) {
        Studios studios = new Studios();
        studios.setId(dto.getId());
        studios.setName(dto.getName());
        studios.setImage(dto.getImageDTO());
        return studios;
    }

}
