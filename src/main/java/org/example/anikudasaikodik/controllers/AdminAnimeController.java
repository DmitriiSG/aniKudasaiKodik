package org.example.anikudasaikodik.controllers;

import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.repositories.AnimeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/anime")

public class AdminAnimeController {

    private final AnimeRepository animeRepository;

    public AdminAnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }


    @GetMapping
    public String listAnime(Model model) {
        model.addAttribute("anime", animeRepository.findAll());
        return "admin/anime/list";
    }

    // Форма редактирования
    @GetMapping("/edit/{id}")
    public String editAnime(@PathVariable Long id, Model model) {
        Anime anime = animeRepository.findById(id).orElseThrow();
        model.addAttribute("anime", anime);
        return "admin/anime/edit";
    }

    // Сохранение после редактирования
    @PostMapping("/save")
    public String saveAnime(@ModelAttribute Anime anime) {
        animeRepository.save(anime);
        return "redirect:/admin/anime";
    }

    // Добавление нового аниме
    @GetMapping("/new")
    public String newAnime(Model model) {
        model.addAttribute("anime", new Anime());
        return "admin/anime/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnime(@PathVariable Long id) {
        animeRepository.deleteById(id);
        return "redirect:/admin/anime";
    }

}
