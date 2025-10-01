package org.example.anikudasaikodik.mappers;

import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.dto.kodikDTO.KodikAnimeDTO;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.AnimeStudios;
import org.example.anikudasaikodik.models.Genre;
import org.example.anikudasaikodik.repositories.AnimeStudiosRepository;
import org.example.anikudasaikodik.repositories.GenreRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class KodikMapper {

    private final GenreRepository genreRepository;
    private final AnimeStudiosRepository animeStudiosRepository;

    public Anime toEntity(KodikAnimeDTO dto, Anime existing) {
        Anime anime = existing != null ? existing : new Anime();
        anime.setShikimoriId(safeParseLong(dto.getShikimoriId()));
        anime.setKodikId(dto.getKodikId());
        anime.setKinopoiskId(safeParseLong(dto.getKinopoiskId()));
        anime.setTitle(dto.getTitle());

        anime.setLink(dto.getLink());
        anime.setType(dto.getType());
        anime.setYear(dto.getYear());
        anime.setLastEpisode(dto.getLastEpisode());
        anime.setScreenshots(dto.getScreenshots());


        if (dto.getMaterialData() != null) {
            anime.setAnimeDescription(dto.getMaterialData().getAnimeDescription());
            anime.setPosterUrl(dto.getMaterialData().getPosterUrl());


            //рейтинги
            anime.setShikimoriRating(safeParseDouble(dto.getMaterialData().getShikimorRating()));
            anime.setKinopoiskRating(dto.getMaterialData().getKinopoiskRating());
            anime.setImdbRating(dto.getMaterialData().getImdbRating());

            //жанры   сначала проверка есть ли такой жанр
            if (dto.getMaterialData().getAnimeGenres() != null) {
                Set<Genre> genres = new HashSet<>();
                for (String gName : dto.getMaterialData().getAnimeGenres()) {
                    Genre genre = genreRepository.findByName(gName)
                            .orElseGet(() -> genreRepository.save(new Genre(null, gName)));
                    genres.add(genre);
                }
                anime.setAnimeGenres(genres);
            }

            //студия  сначала проверка есть ли такая студия
            if (dto.getMaterialData().getAnimeStudios() != null) {
                Set<AnimeStudios> studios = new HashSet<>();
                for (String sName : dto.getMaterialData().getAnimeStudios()) {
                    AnimeStudios animeStudio = animeStudiosRepository.findByName(sName)
                            .orElseGet(() -> animeStudiosRepository.save(new AnimeStudios(null, sName)));
                    studios.add(animeStudio);
                }
                anime.setAnimeStudios(studios);
            }
            anime.setAnimeStatus(dto.getMaterialData().getAnimeStatus());
            anime.setCreatedAt(safeParseDate(dto.getCreatedAt()));
            anime.setEpisodesAired(dto.getMaterialData().getEpisodesAired());
            anime.setEpisodesTotal(dto.getMaterialData().getEpisodesTotal());
            anime.setImdbId(dto.getImdbId());
            anime.setNextEpisodeAt(safeParseDate(dto.getMaterialData().getNextEpisodeAt()));
            anime.setRatingMpaa(dto.getMaterialData().getRatingMpaa());


        }

        if (dto.getUpdatedAt() != null) {
            try {
                anime.setUpdatedAt(OffsetDateTime.parse(dto.getUpdatedAt()).toLocalDateTime());
            } catch (Exception e) {

                System.out.println("⚠ Не удалось распарсить updatedAt: " + dto.getUpdatedAt());
            }
        }


        if (dto.getLastEpisode() != null) {
            anime.setLastEpisode(dto.getLastEpisode());
        }

        return anime;
    }


    private Long safeParseLong(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠ Ошибка парсинга long: " + value);
            return null;
        }
    }


    private Double safeParseDouble(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠ Ошибка парсинга double: " + value);
            return null;
        }
    }
    private LocalDateTime safeParseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        try {
            if (dateStr.endsWith("Z")) {
                // "2025-10-26T00:24:55Z" → UTC
                return OffsetDateTime.parse(dateStr).toLocalDateTime();
            } else {
                return LocalDateTime.parse(dateStr);
            }
        } catch (Exception e) {
            System.out.println("⚠ Ошибка парсинга даты: " + dateStr);
            return null;
        }
    }
}
