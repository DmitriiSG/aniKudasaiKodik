package org.example.anikudasaikodik.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.dto.shikimoriDTO.AnimeDTO;
import org.example.anikudasaikodik.mappers.ShikimoriMapper;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShikimoriParserService {

    private final AnimeService animeService;
    private final RestTemplate restTemplate;
    private final ShikimoriMapper shikimoriMapper;

    public ShikimoriParserService(AnimeService animeService, RestTemplate restTemplate, ShikimoriMapper shikimoriMapper) {
        this.animeService = animeService;
        this.restTemplate = restTemplate;
        this.shikimoriMapper = shikimoriMapper;
    }

    public void parseAndSave(int pages) {
        for (int page = 1; page <= pages; page++) {
            String url = "https://shikimori.one/api/animes?page=" + page + "&limit=50&order=popularity";
            AnimeDTO[] animeList = restTemplate.getForObject(url, AnimeDTO[].class);
            if (animeList != null) {
                for (AnimeDTO dto : animeList) {
                    animeService.save(shikimoriMapper.mapToEntity(dto));
                    if (!animeService.existsById(dto.getId())) {
                        animeService.save(shikimoriMapper.mapToEntity(dto));
                    }
                }
            }
        }
    }
}



