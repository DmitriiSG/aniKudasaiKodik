package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.services.AnimeService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URLConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@RestController
@RequestMapping("/api/image")
public class ImageProxyController {

    private final AnimeService animeService;

    public ImageProxyController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/original/{animeId}")
    public ResponseEntity<byte[]> getImageOriginal(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        String imageUrl = anime.getImage().getOriginal();

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
    @GetMapping("/x96/{animeId}")
    public ResponseEntity<byte[]> getImageX96(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        String imageUrl = anime.getImage().getX96();

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
    @GetMapping("/x48/{animeId}")
    public ResponseEntity<byte[]> getImageX48(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        String imageUrl = anime.getImage().getX48();

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
    @GetMapping("/preview/{animeId}")
    public ResponseEntity<byte[]> getImagePreview(@PathVariable Long animeId) throws IOException {
        Anime anime = animeService.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found: " + animeId));
        String imageUrl = anime.getImage().getPreview();

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


}