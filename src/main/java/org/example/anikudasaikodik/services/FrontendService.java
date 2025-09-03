package org.example.anikudasaikodik.services;

import org.example.anikudasaikodik.dto.frontendDTO.FrontendAnimeDTO;
import org.example.anikudasaikodik.mappers.FrontendMapper;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.repositories.AnimeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FrontendService {
    private final AnimeRepository animeRepository;
    private final FrontendMapper frontendMapper;
    public FrontendService(AnimeRepository animeRepository, FrontendMapper frontendMapper) {
        this.animeRepository = animeRepository;
        this.frontendMapper = frontendMapper;
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


}
