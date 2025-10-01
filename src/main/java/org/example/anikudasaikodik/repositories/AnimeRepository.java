package org.example.anikudasaikodik.repositories;

import org.example.anikudasaikodik.models.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository  extends JpaRepository<Anime, Long>{

    Optional<Anime> findTopByOrderByUpdatedAtDesc();

    Optional<Anime> findAnimeByKodikId(String kodikId);

    Optional<Anime> findAnimeByShikimoriId(Long shikimoriId);

    Optional<Anime> findByShikimoriIdOrKodikIdOrKinopoiskId(Long shikimoriId, String kodikId, Long kinopoiskId);

    Optional<Anime> findByShikimoriId(Long aLong);

    Optional<Anime> findByKodikId(String kodikId);

    Optional<Anime> findByKinopoiskId(Long aLong);
    @Query("""
    SELECT a FROM Anime a
    WHERE (:shikimoriId IS NOT NULL AND a.shikimoriId = :shikimoriId)
       OR (:kodikId IS NOT NULL AND a.kodikId = :kodikId)
       OR (:kinopoiskId IS NOT NULL AND a.kinopoiskId = :kinopoiskId)
""")
    Optional<Anime> findByAnyId(
            @Param("shikimoriId") Long shikimoriId,
            @Param("kodikId") String kodikId,
            @Param("kinopoiskId") Long kinopoiskId
    );

    Page<Anime> findByAnimeStatus(String ongoing, Pageable pageable);

    Page<Anime> findAll(Specification<Anime> spec, Pageable pageable);

    @Query(value = "SELECT * FROM anime ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Anime getRandomAnime();
}
