package org.example.anikudasaikodik.services;


import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.repositories.AnimeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;

    }

    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    @Transactional
    public void save(Anime anime) {
        animeRepository.save(anime);
    }

    public void deleteById(Long id) {
        animeRepository.deleteById(id);
    }

    public Optional<Anime> findById(Long id) {

        Optional<Anime> anime = animeRepository.findById(id);

        return anime;
    }

    public boolean existsById(Long id) {
        return animeRepository.existsById(id);
    }

    @Transactional
    public List<Anime> findAll(int page, int limit) {
        return animeRepository.findAll();


    }
}
