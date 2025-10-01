package org.example.anikudasaikodik.controllers;

import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.dto.kodikDTO.KodikAnimeDTO;

import org.example.anikudasaikodik.services.KodikParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kodik")
public class KodikController {

    private final KodikParserService parserService;

    @PostMapping("/parse")
    public ResponseEntity<String> parseAll() {
        parserService.parseAllAnime();
        return ResponseEntity.ok("Парсинг завершён успешно");
    }
}
