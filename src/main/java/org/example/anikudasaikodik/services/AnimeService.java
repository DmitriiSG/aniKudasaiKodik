package org.example.anikudasaikodik.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.repositories.AnimeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
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
        return animeRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return animeRepository.existsById(id);
    }

    public List<Anime> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Anime> animePage = animeRepository.findAll(pageable);
        return animePage.getContent();

    }


}
