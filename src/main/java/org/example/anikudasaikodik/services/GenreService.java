package org.example.anikudasaikodik.services;

import org.example.anikudasaikodik.models.Genre;
import org.example.anikudasaikodik.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
}
