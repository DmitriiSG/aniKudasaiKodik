package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.services.AnimeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
public class FrontendController {
    private final AnimeService animeService;

    public FrontendController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public List<Anime> getAnime(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int limit) {
        return animeService.findAll(page, limit);
    }
    @GetMapping("/{id}")
    public Anime getAnimeById(@PathVariable Long id) {
        return animeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Anime not found"));
    }

}
