package org.example.anikudasaikodik.dto.shikimoriDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShikimoriGenreDTO {
    private Long id;

    private String name;

    private String russian;

    private String kind;
    @JsonProperty("entry_type")
    private String entryType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }
}
