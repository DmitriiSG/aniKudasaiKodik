package org.example.anikudasaikodik.services;

import org.example.anikudasaikodik.models.AnimeCharacter;
import org.example.anikudasaikodik.repositories.AnimeCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeCharacterService {
    private final AnimeCharacterRepository animeCharacterRepository;
    public AnimeCharacterService(AnimeCharacterRepository animeCharacterRepository) {
        this.animeCharacterRepository = animeCharacterRepository;
    }

    public void saveAll(List<AnimeCharacter> animeCharacter){
        animeCharacterRepository.saveAll(animeCharacter);
    }
}
