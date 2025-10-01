package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Genre;
import org.example.anikudasaikodik.services.FrontendService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
public class FrontendController {
    private final FrontendService frontendService;


    public FrontendController(FrontendService frontendService) {
        this.frontendService = frontendService;

    }

    @GetMapping
    public Page<Anime> getAnime(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int limit) {

        return frontendService.findAll(page, limit);
    }
    @GetMapping("/{id}")
    public Anime getAnimeById(@PathVariable Long id) {
        return frontendService.findById(id)
                .orElseThrow(() -> new RuntimeException("Anime not found"));
    }
    @GetMapping("/ongoing")
    public Page<Anime> getOngoing(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size
    ) {
        return frontendService.findOngoing(page, size);
    }


    @GetMapping("/search")
    public Page<Anime> searchAnime(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo
    ) {
        return frontendService.searchAnime(query, genres, page, limit,  yearFrom, yearTo);
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return frontendService.getGenres();
    }

    @GetMapping("/random")
    public  Anime getRandomAnime(){
        return frontendService.getRandomAnime();
    }



}
