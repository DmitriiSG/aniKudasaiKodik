package org.example.anikudasaikodik.services;

import jakarta.persistence.criteria.*;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Genre;
import org.example.anikudasaikodik.repositories.AnimeRepository;
import org.example.anikudasaikodik.repositories.GenreRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FrontendService {

    private final AnimeRepository animeRepository;
    private final GenreRepository genreRepository;

    public FrontendService(AnimeRepository animeRepository, GenreRepository genreRepository) {
        this.animeRepository = animeRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public Anime save(Anime anime) {
        return animeRepository.save(anime);
    }

    public void deleteById(Long id) {
        animeRepository.deleteById(id);
    }

    public Optional<Anime> findById(Long id) {
        return animeRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return animeRepository.existsById(id);
    }

    @Transactional
    public Page<Anime> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        return animeRepository.findAll(pageable);
    }

    public Page<Anime> findOngoing(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("shikimoriRating").descending());
        return animeRepository.findByAnimeStatus("ongoing", pageable);
    }

    @Transactional(readOnly = true)
     public Page<Anime> searchAnime(String query, List<String> genres, int page, int limit, Integer yearFrom, Integer yearTo) {
        // Pageable без Sort, чтобы сортировку задавать в Specification
        Pageable pageable = PageRequest.of(page - 1, limit);

        Specification<Anime> spec = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Поиск по названию
            if (query != null && !query.isBlank()) {
                String normalizedQuery = "%" + query.toLowerCase().replace("ё", "е") + "%";
                Expression<String> titleNormalized = cb.function("replace", String.class, cb.lower(root.get("title")), cb.literal("ё"), cb.literal("е"));
                predicates.add(cb.like(titleNormalized, normalizedQuery));
            }

            // Фильтр по годам
            if (yearFrom != null || yearTo != null) {
                int startYear = (yearFrom != null) ? yearFrom : 1900;
                int endYear = (yearTo != null) ? yearTo : 2100;
                predicates.add(cb.between(root.get("year"), startYear, endYear));
            }

            // Фильтр по жанрам
            if (genres != null && !genres.isEmpty()) {
                List<String> normalizedGenres = genres.stream()
                        .map(g -> g.toLowerCase().replace("ё", "е"))
                        .toList();

                Subquery<Long> subquery = cq.subquery(Long.class);
                Root<Anime> subRoot = subquery.from(Anime.class);
                Join<Anime, Genre> subJoin = subRoot.join("animeGenres", JoinType.INNER);
                Expression<String> genreNameNormalized = cb.function("replace", String.class, cb.lower(subJoin.get("name")), cb.literal("ё"), cb.literal("е"));

                subquery.select(subRoot.get("id"))
                        .where(genreNameNormalized.in(normalizedGenres))
                        .groupBy(subRoot.get("id"))
                        .having(cb.equal(cb.countDistinct(subJoin.get("id")), normalizedGenres.size()));

                predicates.add(root.get("id").in(subquery));
            }

            // Сортировка: DESC по shikimoriRating, null в конец
            Expression<Double> shikiRating = root.get("shikimoriRating");
            cq.orderBy(cb.desc(
                    cb.selectCase()
                            .when(cb.isNull(shikiRating), -1.0)  // null заменяем на минимальное значение
                            .otherwise(shikiRating)
            ));


            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return animeRepository.findAll(spec, pageable);
    }


    public Anime getRandomAnime() {
        return animeRepository.getRandomAnime();
    }

    @Transactional(readOnly = true)
    public List<Genre> getGenres() {
        if (genreRepository == null) throw new RuntimeException("GenreService is null!");
        List<Genre> genres = genreRepository.findAll();
        return genres;
    }
}
