package org.example.anikudasaikodik.repositories;

import org.example.anikudasaikodik.models.AnimeStudios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeStudiosRepository extends JpaRepository<AnimeStudios, Long> {
    Optional<AnimeStudios> findByName(String name);
}
