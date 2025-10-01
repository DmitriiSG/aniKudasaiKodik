package org.example.anikudasaikodik.controllers;

import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.repositories.AnimeRepository;
import org.example.anikudasaikodik.services.KodikParserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AnimeRepository animeRepository;
    private final KodikParserService parserService;

    // Главная страница админки
    @GetMapping
    public String adminHome(Model model) {
        List<Anime> animeList = animeRepository.findAll();
        model.addAttribute("animeList", animeList);
        return "admin/index";
    }

    // Редактирование аниме
    @GetMapping("/edit/{id}")
    public String editAnime(@PathVariable Long id, Model model) {
        Anime anime = animeRepository.findById(id).orElseThrow();
        model.addAttribute("anime", anime);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveAnime(@PathVariable Long id, @ModelAttribute Anime animeForm) {
        Anime anime = animeRepository.findById(id).orElseThrow();
        // Обновляем поля
        anime.setTitle(animeForm.getTitle());
        anime.setType(animeForm.getType());
        anime.setLink(animeForm.getLink());
        anime.setYear(animeForm.getYear());
        anime.setShikimoriId(animeForm.getShikimoriId());
        anime.setKinopoiskId(animeForm.getKinopoiskId());
        anime.setKodikId(animeForm.getKodikId());
        anime.setLastEpisode(animeForm.getLastEpisode());
        anime.setEpisodesCount(animeForm.getEpisodesCount());
        anime.setAnimeStatus(animeForm.getAnimeStatus());
        anime.setPosterUrl(animeForm.getPosterUrl());
        anime.setAnimeDescription(animeForm.getAnimeDescription());
        anime.setKinopoiskRating(animeForm.getKinopoiskRating());
        anime.setImdbRating(animeForm.getImdbRating());
        anime.setShikimoriRating(animeForm.getShikimoriRating());
        animeRepository.save(anime);
        return "redirect:/admin";
    }

    // Удаление аниме
    @PostMapping("/delete/{id}")
    public String deleteAnime(@PathVariable Long id) {
        animeRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/parser/start")
    public String startParser() {
        parserService.startParser();
        return "redirect:/admin";
    }

    @PostMapping("/parser/stop")
    public String stopParser() {
        parserService.stopParser();
        return "redirect:/admin";
    }

    @PostMapping("/parser/set-delay")
    public String setParserDelay(@RequestParam long delayMillis) {
        parserService.setParserDelay(delayMillis);
        return "redirect:/admin";
    }

}
