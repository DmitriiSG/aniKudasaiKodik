package org.example.anikudasaikodik.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "anime")
@Entity
public class Anime {

    @Id
    private Long id;

    private String name;
    private String russian;
    private String url;
    private String kind;
    private Double score;
    private String status;
    private Integer episodes;

    @Column(name = "episodes_aired")
    private Integer episodesAired;

    @Column(name = "aired_on")
    private LocalDate airedOn;

    @Column(name = "released_on")
    private LocalDate releasedOn;

    private String rating;

    private String licenseNameRu;
    private Integer duration;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "description_html", columnDefinition = "TEXT")
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

    // 🔗 связи
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screenshots> screenshots = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "anime_genre",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "anime_studios",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private List<Studios> studios = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "anime_character",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<AnimeCharacter> animeCharacters = new ArrayList<>();

    public Anime() {
    }

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

    public LocalDate getAiredOn() {
        return airedOn;
    }

    public void setAiredOn(LocalDate airedOn) {
        this.airedOn = airedOn;
    }

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Screenshots> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Screenshots> screenshots) {
        this.screenshots = screenshots;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Studios> getStudios() {
        return studios;
    }

    public void setStudios(List<Studios> studios) {
        this.studios = studios;
    }

    public List<AnimeCharacter> getCharacters() {
        return animeCharacters;
    }

    public void setCharacters(List<AnimeCharacter> animeCharacters) {
        this.animeCharacters = animeCharacters;
    }
}
