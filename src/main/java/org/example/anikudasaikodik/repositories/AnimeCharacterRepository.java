package org.example.anikudasaikodik.repositories;

import org.example.anikudasaikodik.models.AnimeCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeCharacterRepository extends JpaRepository<AnimeCharacter, Long> {


}
