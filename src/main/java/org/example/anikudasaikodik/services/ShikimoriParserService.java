package org.example.anikudasaikodik.services;


import org.example.anikudasaikodik.dto.shikimoriDTO.ShikimoriAnimeDTO;
import org.example.anikudasaikodik.mappers.ShikimoriMapper;
import org.example.anikudasaikodik.models.Anime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@Service
public class ShikimoriParserService {

    private final AnimeService animeService;
    private final RestTemplate restTemplate;
    private final ShikimoriMapper shikimoriMapper;

    public ShikimoriParserService(AnimeService animeService,
                                  RestTemplate restTemplate,
                                  ShikimoriMapper shikimoriMapper) {
        this.animeService = animeService;
        this.restTemplate = restTemplate;
        this.shikimoriMapper = shikimoriMapper;
    }

    // Запуск парсера вручную (по API)
    public void parseAndSave(int pages) throws InterruptedException {
        runParsing(pages, null);
    }

    // Автоматический запуск раз в день ( в 03:00 ночи)
    @Scheduled(cron = "0 0 3 * * *")
    public void dailyUpdate() throws InterruptedException {
        // Берём только новые сезоны + без хентая
        runParsing(3, "status=ongoing,anons&censored=true&rating=!rx");
    }

    private void runParsing(int pages, String extraParams) throws InterruptedException {
        for (int page = 1; page <= pages; page++) {
            String urlList = "https://shikimori.one/api/animes?page=" + page + "&limit=50&order=popularity";

            if (extraParams != null) {
                urlList += "&" + extraParams;
            }

            ShikimoriAnimeDTO[] animeList;
            try {
                animeList = restTemplate.getForObject(urlList, ShikimoriAnimeDTO[].class);
            } catch (HttpClientErrorException.TooManyRequests e) {
                System.out.println("Слишком много запросов, попробуйте позже.");
                return;
            }

            if (animeList == null) continue;

            for (ShikimoriAnimeDTO dto : animeList) {
                saveSkeleton(dto);
            }

            for (ShikimoriAnimeDTO dto : animeList) {
                Thread.sleep(500);
                saveDetails(dto);
            }
        }
    }

    private void saveSkeleton(ShikimoriAnimeDTO dto) {
        Anime anime = animeService.findById(dto.getId()).orElse(null);
        if (anime == null) {
            anime = shikimoriMapper.mapSkeleton(dto);
            animeService.save(anime);
        }
    }

    private void saveDetails(ShikimoriAnimeDTO dto) {
        Anime anime = animeService.findById(dto.getId()).orElse(null);
        if (anime == null) return;

        String urlId = "https://shikimori.one/api/animes/" + dto.getId();
        ShikimoriAnimeDTO fullDto;
        try {
            fullDto = restTemplate.getForObject(urlId, ShikimoriAnimeDTO.class);
        } catch (HttpClientErrorException.TooManyRequests e) {
            System.out.println("Слишком много запросов при получении деталей.");
            return;
        }

        if (fullDto != null) {
            shikimoriMapper.updateAnimeFromDTO(anime, fullDto);
            animeService.save(anime);
        }
    }
}
