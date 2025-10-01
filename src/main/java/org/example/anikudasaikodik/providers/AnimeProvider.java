package org.example.anikudasaikodik.providers;


import org.example.anikudasaikodik.dto.kodikDTO.KodikAnimeDTO;

import java.util.List;
import java.util.Optional;

public interface AnimeProvider {
    Optional<KodikAnimeDTO> findById(String id);
    List<KodikAnimeDTO> search(String query);
    List<KodikAnimeDTO> getList(int page, int limit);
}