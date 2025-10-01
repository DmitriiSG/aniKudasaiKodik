package org.example.anikudasaikodik.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.dto.kodikDTO.KodikAnimeDTO;
import org.example.anikudasaikodik.mappers.KodikMapper;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.repositories.AnimeRepository;
import org.example.anikudasaikodik.util.KodikProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class KodikParserService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final KodikProperties properties;
    private final KodikMapper mapper;
    private final AnimeRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Thread parserThread;
    private volatile boolean running = false;
    private volatile long parserDelay = 21600000L; // 6 часов по умолчанию

    // Старт парсера
    public synchronized void startParser() {
        if (running) {
            System.out.println("Парсер уже запущен");
            return;
        }
        running = true;
        System.out.println("Парсер стартует...");
        parserThread = new Thread(() -> {
            while (running) {
                parseAllAnime();
                try {
                    Thread.sleep(parserDelay);
                } catch (InterruptedException e) {
                    // Пересчитываем новый интервал, не останавливаем парсер
                    System.out.println("⏱ Применён новый интервал, продолжаем работу парсера...");
                    // Не присваиваем running = false
                }
            }
        });
        parserThread.start();
    }


    // Стоп парсера
    public synchronized void stopParser() {
        running = false;
        if (parserThread != null) parserThread.interrupt();
    }

    // Изменение интервала
    public synchronized void setParserDelay(long delayMillis) {
        long oldDelay = this.parserDelay;
        this.parserDelay = delayMillis;

        logDelayChange(oldDelay, delayMillis);

        // Только прерываем, чтобы Thread.sleep() пересчитал новый интервал
        if (parserThread != null && parserThread.isAlive()) {
            parserThread.interrupt();
        }
    }


    private void logDelayChange(long oldDelay, long newDelay) {
        String logMessage = LocalDateTime.now() + " ⏱ Изменение интервала парсера: " +
                oldDelay + "ms → " + newDelay + "ms\n";
        System.out.print(logMessage);

        try (FileWriter writer = new FileWriter("parser_delay.log", true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в лог parser_delay.log: " + e.getMessage());
        }
    }



    public void parseAllAnime() {

        if (!running) return;

        List<String> types = List.of("anime", "anime-serial");

        for (String type : types) {
            int page = 1;
            String nextPage = properties.getUrl() + "/list?token=" + properties.getToken() +
                    "&types=" + type + "&with_material_data=true&limit=100";

            while (nextPage != null && running) {
                System.out.println("🔄 Парсинг типа " + type + ", страница " + page);

                try {
                    Map<String, Object> response = restTemplate.getForObject(nextPage, Map.class);
                    if (response == null || response.get("results") == null) break;

                    List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

                    for (Map<String, Object> result : results) {
                        try {
                            KodikAnimeDTO dto = objectMapper.convertValue(result, KodikAnimeDTO.class);

                            // --- Пропуск нежелательного контента ---
                            if (dto.getMaterialData().getAnimeGenres() != null && dto.getMaterialData().getAnimeGenres().stream()
                                    .anyMatch(g -> {
                                        String genre = g.toLowerCase();
                                        return genre.contains("хентай") || genre.contains("эротика") || genre.contains("яой") || genre.contains("юри");
                                    })) {
                                logSkippedAnime(dto.getTitle() + " (хентай/яой/эротика)");
                                continue;
                            }


                            Long shikimoriId = safeParseLong(dto.getShikimoriId());
                            Long kinopoiskId = safeParseLong(dto.getKinopoiskId());
                            String kodikId = dto.getKodikId();

                            if (shikimoriId == null && kodikId == null && kinopoiskId == null) {
                                logSkippedAnime(dto.getTitle());
                                continue;
                            }

                            Anime existingAnime = repository.findByAnyId(shikimoriId, kodikId, kinopoiskId)
                                    .orElse(null);

                            LocalDateTime dtoUpdatedAt = parseOffsetDateTime(dto.getUpdatedAt());

                            if (existingAnime == null) {
                                Anime anime = mapper.toEntity(dto, null);
                                repository.save(anime);
                                System.out.println("🆕 Добавлено новое аниме: " + anime.getTitle());
                            } else {
                                boolean shouldUpdate = false;

                                if (dtoUpdatedAt != null && (existingAnime.getUpdatedAt() == null ||
                                        existingAnime.getUpdatedAt().isBefore(dtoUpdatedAt))) {
                                    shouldUpdate = true;
                                }

                                if (dto.getLastEpisode() != null &&
                                        (existingAnime.getLastEpisode() == null || dto.getLastEpisode() > existingAnime.getLastEpisode())) {
                                    shouldUpdate = true;
                                    System.out.println("🎬 Новые серии для " + existingAnime.getTitle() +
                                            ": до " + existingAnime.getLastEpisode() + ", теперь " + dto.getLastEpisode());
                                }

                                if (shouldUpdate) {

                                    Anime anime = mapper.toEntity(dto, existingAnime);
                                    repository.save(anime);
                                    System.out.println("♻ Обновлено аниме: " + existingAnime.getTitle());
                                }
                            }
                        } catch (Exception e) {
                            logParsingError(result, e);
                        }
                    }

                    nextPage = (String) response.get("next_page");
                    page++;
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } catch (Exception e) {
                    logParsingError("Ошибка при парсинге страницы " + page, e);
                }
            }
        }
    }

    private void logSkippedAnime(String title) {
        try (FileWriter writer = new FileWriter("skipped_anime.log", true)) {
            writer.write(LocalDateTime.now() + " ⚠ Пропущено аниме без ID: " + title + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в лог skipped_anime.log: " + e.getMessage());
        }
    }

    private void logParsingError(Object context, Exception e) {
        try (FileWriter writer = new FileWriter("parsing_errors.log", true)) {
            writer.write(LocalDateTime.now() + " ❌ Ошибка при парсинге: " + context + "\n");
            writer.write("   Детали: " + e + "\n");
        } catch (IOException ex) {
            System.out.println("Ошибка при записи в лог parsing_errors.log: " + ex.getMessage());
        }
    }

    private Long safeParseLong(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            logParsingError("Ошибка парсинга ID: " + value, e);
            return null;
        }
    }

    private LocalDateTime parseOffsetDateTime(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return OffsetDateTime.parse(value).toLocalDateTime();
        } catch (Exception e) {
            logParsingError("Не удалось распарсить дату: " + value, e);
            return null;
        }
    }
}
