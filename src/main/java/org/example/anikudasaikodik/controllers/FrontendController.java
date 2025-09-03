package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.dto.frontendDTO.FrontendAnimeDTO;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.services.AnimeService;
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

}
