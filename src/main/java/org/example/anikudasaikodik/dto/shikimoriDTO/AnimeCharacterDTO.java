package org.example.anikudasaikodik.dto.shikimoriDTO;

import jakarta.persistence.*;
import org.example.anikudasaikodik.models.Anime;
import org.example.anikudasaikodik.models.Image;

import java.util.ArrayList;
import java.util.List;

public class AnimeCharacterDTO {

    private Long id;

    // Имя оригинальное (например японское)

    private String name;

    // Русское имя (если есть)
    private String russian;

    // Краткое описание персонажа

    private String description;

    // Картинка

    private ImageDTO imageDTO;

    // Связь многие-ко-многим с аниме

    private List<AnimeDTO> animesDTO = new ArrayList<>();

}
