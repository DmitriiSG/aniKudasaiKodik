package org.example.anikudasaikodik.dto.kodikDTO;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KodikAnimeDTO {
    @JsonProperty("id")
    private String kodikId; //kodik id
    private String title; //русское название
    private String type; //фильм или сериал
    private String link; //плеер
    private Integer year; //год


    // id сторонних сервисов
    @JsonProperty("shikimori_id")
    private String shikimoriId;
    @JsonProperty("kinopoisk_id")
    private String kinopoiskId;
    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt; // будет строкой, конвертируем в LocalDateTime

    @JsonProperty("last_episode")
    private Integer lastEpisode;

    @JsonProperty("episodes_count")
    private Integer episodesCount;

    @JsonProperty("screenshots")
    private List<String> screenshots;



    @JsonProperty("material_data")
    private MaterialData materialData;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MaterialData {

        @JsonProperty("anime_status")
        private String animeStatus;

        @JsonProperty("poster_url")
        private String posterUrl;

        @JsonProperty("anime_description")
        private String animeDescription;

        @JsonProperty("anime_genres")
        private List<String> animeGenres;

        @JsonProperty("anime_studios")
        private List<String> animeStudios;

        //рейтинги
        @JsonProperty("kinopoisk_rating")
        private String kinopoiskRating;

        @JsonProperty("imdb_rating")
        private String imdbRating;

        @JsonProperty("shikimori_rating")
        private String shikimorRating;


        @JsonProperty("next_episode_at")
        private String nextEpisodeAt; //Время выхода следующей серии

        @JsonProperty("rating_mpaa")
        private String ratingMpaa;

        @JsonProperty("episodes_total")
        private Integer episodesTotal; //Количество эпизодов


        @JsonProperty("episodes_aired")
        private Integer episodesAired; //Количество уже вышедших эпизодов

    }
}