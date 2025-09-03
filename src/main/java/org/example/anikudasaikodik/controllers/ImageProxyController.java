package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.services.AnimeService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/api/image")
public class ImageProxyController {

    private final AnimeService animeService;
    private static final String BASE_URL = "https://shikimori.one"; // добавляем домен к относительным ссылкам

    public ImageProxyController(AnimeService animeService) {
        this.animeService = animeService;
    }

    // Универсальный метод для загрузки картинки
    private ResponseEntity<byte[]> fetchImage(String imageUrl) throws IOException {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new RuntimeException("Image URL is empty");
        }

        // Если путь относительный -> дополняем базовым URL
        if (!imageUrl.startsWith("http")) {
            imageUrl = BASE_URL + imageUrl;
        }

        URL url = new URL(imageUrl);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream in = url.openStream()) {
            in.transferTo(baos);
        }

        String contentType = URLConnection.guessContentTypeFromStream(
                new ByteArrayInputStream(baos.toByteArray()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "image/jpeg"))
                .body(baos.toByteArray());
    }

    @GetMapping("/original/{animeId}")
    public ResponseEntity<byte[]> getImageOriginal(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        return fetchImage(anime.getImage().getOriginal());
    }

    @GetMapping("/x96/{animeId}")
    public ResponseEntity<byte[]> getImageX96(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        return fetchImage(anime.getImage().getX96());
    }

    @GetMapping("/x48/{animeId}")
    public ResponseEntity<byte[]> getImageX48(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        return fetchImage(anime.getImage().getX48());
    }

    @GetMapping("/preview/{animeId}")
    public ResponseEntity<byte[]> getImagePreview(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        return fetchImage(anime.getImage().getPreview());
    }
}
