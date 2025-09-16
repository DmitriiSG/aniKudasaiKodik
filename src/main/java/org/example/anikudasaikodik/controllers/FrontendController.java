package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.dto.frontendDTO.FrontendAnimeDTO;
import org.example.anikudasaikodik.dto.frontendDTO.FrontendGenreDTO;
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
    public Page<FrontendAnimeDTO> getAnime(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int limit) {

        return frontendService.findAll(page, limit);
    }
    @GetMapping("/{id}")
    public FrontendAnimeDTO getAnimeById(@PathVariable Long id) {
        return frontendService.findById(id)
                .orElseThrow(() -> new RuntimeException("Anime not found"));
    }
    @GetMapping("/ongoing")
    public Page<FrontendAnimeDTO> getOngoing(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return frontendService.findOngoing(page, size);
    }


    @GetMapping("/search")
    public Page<FrontendAnimeDTO> searchAnime(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo
    ) {
        return frontendService.search(query, genres, page, limit,  yearFrom, yearTo);
    }

    @GetMapping("/genres")
    public List<FrontendGenreDTO> getGenres() {
        return frontendService.getFrontendGenre();
    }

    @GetMapping("/random")
    public Page<FrontendAnimeDTO> getRandomAnime(){
        return frontendService.getRandomAnime();
    }



}
