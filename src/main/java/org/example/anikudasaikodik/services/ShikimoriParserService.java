package org.example.anikudasaikodik.services;

import org.example.anikudasaikodik.dto.shikimoriDTO.AnimeDTO;
import org.example.anikudasaikodik.mappers.ShikimoriMapper;
import org.example.anikudasaikodik.models.Anime;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ShikimoriParserService {

    private final AnimeService animeService;
    private final RestTemplate restTemplate;
    private final ShikimoriMapper shikimoriMapper;
    private final AnimeCharacterService animeCharacterService;

    public ShikimoriParserService(AnimeService animeService,
                                  RestTemplate restTemplate,
                                  ShikimoriMapper shikimoriMapper, AnimeCharacterService animeCharacterService) {
        this.animeService = animeService;
        this.restTemplate = restTemplate;
        this.shikimoriMapper = shikimoriMapper;
        this.animeCharacterService = animeCharacterService;
    }

    public void parseAndSave(int pages) throws InterruptedException {
        for (int page = 1; page <= pages; page++) {
            String urlList = "https://shikimori.one/api/animes?page=" + page + "&limit=50&order=popularity";
            AnimeDTO[] animeList;

            try {
                animeList = restTemplate.getForObject(urlList, AnimeDTO[].class);
            } catch (HttpClientErrorException.TooManyRequests e) {
                System.out.println("Слишком много запросов, попробуйте позже.");
                return;
            }

            if (animeList == null) continue;

            // Сохранение скелета
            for (AnimeDTO dto : animeList) {
                saveSkeleton(dto);
            }

            // Сохранение деталей
            for (AnimeDTO dto : animeList) {
                Thread.sleep(500);
                saveDetails(dto);
            }


        }
    }

    private void saveSkeleton(AnimeDTO dto) {
        Anime anime = animeService.findById(dto.getId()).orElse(null);
        if (anime == null) {
            anime = shikimoriMapper.mapSkeleton(dto);
            animeService.save(anime);
        }
    }

    private void saveDetails(AnimeDTO dto) {
        Anime anime = animeService.findById(dto.getId()).orElse(null);
        if (anime == null) return; // если нет скелета, детали не сохраняем

        String urlId = "https://shikimori.one/api/animes/" + dto.getId();
        AnimeDTO fullDto;
        try {
            fullDto = restTemplate.getForObject(urlId, AnimeDTO.class);
        } catch (HttpClientErrorException.TooManyRequests e) {
            System.out.println("Слишком много запросов при получении деталей, попробуйте позже.");
            return;
        }

        if (fullDto != null) {
            shikimoriMapper.updateAnimeFromDTO(anime, fullDto);
            animeService.save(anime);
        }

    }


}




