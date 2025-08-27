package org.example.anikudasaikodik.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.anikudasaikodik.services.ShikimoriParserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parser")
public class ShikimoriParserController {

        private ShikimoriParserService shikimoriParser;

    public ShikimoriParserController(ShikimoriParserService shikimoriParser) {
        this.shikimoriParser = shikimoriParser;
    }

    @PostMapping("/run")
        public String runParser(@RequestParam String source, @RequestParam(defaultValue = "1") int pages) {
            switch (source.toLowerCase()) {
                case "shikimori" -> shikimoriParser.parseAndSave(pages);
                //case "kodik" -> kodikParser.parseAndSave(pages);
                //case "anilibria" -> anilibriaParser.parseAndSave(pages);
                default -> throw new IllegalArgumentException("Unknown source: " + source);
            }
            return "Parsing finished";
        }
    }
