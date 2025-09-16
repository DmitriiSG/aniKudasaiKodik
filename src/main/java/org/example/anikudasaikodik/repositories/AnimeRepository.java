package org.example.anikudasaikodik.repositories;

import org.example.anikudasaikodik.models.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository  extends JpaRepository<Anime, Long>,
        JpaSpecificationExecutor<Anime> {
        // поиск по имени + фильтр по жанрам с постраничкой
        Page<Anime> findByNameContainingIgnoreCaseAndGenres_NameIn(String name, List<String> genres, Pageable pageable);

        // если нужно искать ещё и по русскому названию
        Page<Anime> findByRussianContainingIgnoreCaseAndGenres_NameIn(String russian, List<String> genres, Pageable pageable);

        // поиск только ongoing
        Page<Anime> findByOngoingTrue(Pageable pageable);

        @Query(value = "SELECT * FROM anime ORDER BY random() LIMIT 1",
                nativeQuery = true)
         Anime findRandomAnime( );


}
