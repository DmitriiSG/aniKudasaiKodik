package org.example.anikudasaikodik.dto.shikimoriDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;


public class ShikimoriAnimeDTO {
        private Long id;

        private String name;
        private String russian;
        private String url;
        private String kind;
        private Double score;
        private String status;
        private Integer episodes;
        private Integer episodesAired;
        private String aired_on;
        private String released_on;
        private String rating;
        private String licenseNameRu;
        private Integer duration;
        private String description;
        private String descriptionHtml;
        private String descriptionSource;
        private String franchise;
        private Boolean favoured;
        private Boolean anons;
        private Boolean ongoing;
        private Long threadId;
        private Long topicId;
        private Long myanimelistId;
        private OffsetDateTime updatedAt;
        private OffsetDateTime nextEpisodeAt;
        @JsonProperty("image")
        private ShikimoriImageDTO imageDTO ;
        @JsonProperty("screenshots")
        private List<ShikimoriScreenshotsDTO> screenshotsDTO ;
        @JsonProperty("genres")
        private List<ShikimoriGenreDTO> genresDTO ;
        @JsonProperty("studios")
        private List<ShikimoriStudiosDTO> shikimoriStudiosDTO;


        public ShikimoriAnimeDTO() {}

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public Integer getEpisodesAired() {
        return episodesAired;
    }

    public void setEpisodesAired(Integer episodesAired) {
        this.episodesAired = episodesAired;
    }

    public String getAiredOn() {
        return aired_on;
    }

    public void setAired_on(String aired_on) {
        this.aired_on = aired_on;
    }

    public String getReleasedOn() {
        return released_on;
    }

    public void setReleased_on(String released_on) {
        this.released_on = released_on;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLicenseNameRu() {
        return licenseNameRu;
    }

    public void setLicenseNameRu(String licenseNameRu) {
        this.licenseNameRu = licenseNameRu;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getDescriptionSource() {
        return descriptionSource;
    }

    public void setDescriptionSource(String descriptionSource) {
        this.descriptionSource = descriptionSource;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public Boolean getFavoured() {
        return favoured;
    }

    public void setFavoured(Boolean favoured) {
        this.favoured = favoured;
    }

    public Boolean getAnons() {
        return anons;
    }

    public void setAnons(Boolean anons) {
        this.anons = anons;
    }

    public Boolean getOngoing() {
        return ongoing;
    }

    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getMyanimelistId() {
        return myanimelistId;
    }

    public void setMyanimelistId(Long myanimelistId) {
        this.myanimelistId = myanimelistId;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getNextEpisodeAt() {
        return nextEpisodeAt;
    }

    public void setNextEpisodeAt(OffsetDateTime nextEpisodeAt) {
        this.nextEpisodeAt = nextEpisodeAt;
    }

    public ShikimoriImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ShikimoriImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }

    public List<ShikimoriScreenshotsDTO> getScreenshotsDTO() {
        return screenshotsDTO;
    }

    public void setScreenshotsDTO(List<ShikimoriScreenshotsDTO> screenshotsDTO) {
        this.screenshotsDTO = screenshotsDTO;
    }

    public List<ShikimoriGenreDTO> getGenresDTO() {
        return genresDTO;
    }

    public void setGenresDTO(List<ShikimoriGenreDTO> genresDTO) {
        this.genresDTO = genresDTO;
    }

    public List<ShikimoriStudiosDTO> getStudiosDTO() {
        return shikimoriStudiosDTO;
    }

    public void setStudiosDTO(List<ShikimoriStudiosDTO> shikimoriStudiosDTO) {
        this.shikimoriStudiosDTO = shikimoriStudiosDTO;
    }


}
