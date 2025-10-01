package org.example.anikudasaikodik.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.anikudasaikodik.dto.kodikDTO.KodikAnimeDTO;


import java.time.LocalDateTime;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "anime")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String title; //русское название
    private String type; //фильм или сериал
    @Column(columnDefinition = "text")
    private String link; //плеер
    private Integer year; //год

    //id сторонних сервисов
    @Column(unique = true)
    private Long shikimoriId;
    @Column(unique = true)
    private Long kinopoiskId;

    private String imdbId;
    @Column(unique = true)
    private String kodikId;





    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer lastEpisode;

    private Integer episodesCount;

    @ElementCollection
    @Column(name = "screenshot")
    private List<String> screenshots;

    private String animeStatus;



    @Column(columnDefinition = "text")
    private String posterUrl;

    @Column(columnDefinition = "text")
    private String animeDescription;
    //рейтинги
    private String kinopoiskRating;
    private String imdbRating;
    private Double shikimoriRating;

    private String ratingMpaa;


    private LocalDateTime nextEpisodeAt; //Время выхода следующей серии
    private Integer episodesTotal; //Количество эпизодов
    private Integer episodesAired; //Количество уже вышедших эпизодов

    @ManyToMany
    @JoinTable(
            name = "anime_genres",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> animeGenres = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "anime_studio",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private Set<AnimeStudios> animeStudios = new HashSet<>();

}

