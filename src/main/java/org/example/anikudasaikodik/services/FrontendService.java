package org.example.anikudasaikodik.services;

import jakarta.persistence.criteria.*;
import org.example.anikudasaikodik.dto.frontendDTO.FrontendAnimeDTO;
import org.example.anikudasaikodik.dto.frontendDTO.FrontendGenreDTO;
import org.example.anikudasaikodik.mappers.FrontendMapper;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Genre;
import org.example.anikudasaikodik.repositories.AnimeRepository;
import org.example.anikudasaikodik.repositories.GenreRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FrontendService {
    private final AnimeRepository animeRepository;
    private final FrontendMapper frontendMapper;
    private final GenreService genreService;
    public FrontendService(AnimeRepository animeRepository, FrontendMapper frontendMapper, GenreService genreService) {

        this.animeRepository = animeRepository;
        this.frontendMapper = frontendMapper;
        this.genreService = genreService;
    }


    @Transactional
    public void save(Anime anime) {
        animeRepository.save(anime);
    }

    public void deleteById(Long id) {
        animeRepository.deleteById(id);
    }

    public Optional<FrontendAnimeDTO> findById(Long id) {
        return animeRepository.findById(id)
                .map(frontendMapper::animeToFrontendDTO);
    }

    public boolean existsById(Long id) {
        return animeRepository.existsById(id);
    }

    @Transactional
    public Page<FrontendAnimeDTO> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Anime> animePage = animeRepository.findAll(pageable);

        return animePage.map(frontendMapper::animeToFrontendDTO);
    }

    public Page<FrontendAnimeDTO> findOngoing(int page, int size) { Pageable pageable = PageRequest.of(page, size, org.springframework.data
            .domain.Sort.by("score")
            .descending());
        return animeRepository.findByOngoingTrue(pageable) .map(frontendMapper::animeToFrontendDTO); }


    @Transactional(readOnly = true) public Page<FrontendAnimeDTO> search( String query, List<String> genres, int page, int limit, Integer yearFrom, Integer yearTo ) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("score").descending());
        return animeRepository.findAll((root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query != null && !query.isBlank())
             { String normalizedQuery = "%" + query.toLowerCase().replace("ё", "е") + "%";
                 Expression<String> nameReplaced = cb.function("replace", String.class, cb.lower(root.get("name")), cb.literal("ё"), cb.literal("е"));
                 Expression<String> rusReplaced = cb.function("replace", String.class, cb.lower(root.get("russian")), cb.literal("ё"), cb.literal("е"));
                 predicates.add(cb.or( cb.like(nameReplaced, normalizedQuery), cb.like(rusReplaced, normalizedQuery) ));
             }

            if (yearFrom != null || yearTo != null) { LocalDate start = (yearFrom != null) ? LocalDate.of(yearFrom, 1, 1) : LocalDate.of(1900, 1, 1);
                LocalDate end = (yearTo != null) ? LocalDate.of(yearTo, 12, 31) : LocalDate.of(2100, 12, 31);
                predicates.add(cb.or( cb.between(root.get("releasedOn"), start, end), cb.isNull(root.get("releasedOn"))));

            }
            if (genres != null && !genres.isEmpty()) {
                List<String> normalizedGenres = genres.stream() .map(g -> g.toLowerCase().replace("ё", "е")).toList();
                Subquery<Long> subquery = cq.subquery(Long.class);
                Root<Anime> subRoot = subquery.from(Anime.class);
                Join<Anime, Genre> subJoin = subRoot.join("genres", JoinType.INNER);
                Expression<String> genreNameNormalized = cb.function("replace", String.class, cb.lower(subJoin.get("name")), cb.literal("ё"), cb.literal("е"));
                subquery.select(subRoot.get("id")).where(genreNameNormalized.in(normalizedGenres)).groupBy(subRoot.get("id")).having(cb.equal(cb.countDistinct(subJoin.get("id")), normalizedGenres.size()));
                predicates.add(root.get("id").in(subquery));
            }
            return cb.and(predicates.toArray(new Predicate[0])); }, pageable).map(frontendMapper::animeToFrontendDTO);
    }


    public Page<FrontendAnimeDTO> getRandomAnime() {
        Anime anime = animeRepository.findRandomAnime();

        List<FrontendAnimeDTO> dtoList = List.of(frontendMapper.animeToFrontendDTO(anime));

        return new PageImpl<>(dtoList, PageRequest.of(0, 1), 1);

    }

    @Transactional(readOnly = true) public List<FrontendGenreDTO> getFrontendGenre() {
        if (genreService == null) { throw new RuntimeException("GenreService is null!"); }

        List<Genre> genres = genreService.findAll();

        if (genres == null) { throw new RuntimeException("Genre list is null!"); }

        return genres.stream().map(frontendMapper::genreToFrontendDTO).toList(); }
}
