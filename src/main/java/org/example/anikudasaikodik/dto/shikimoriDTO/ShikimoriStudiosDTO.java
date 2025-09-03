package org.example.anikudasaikodik.dto.shikimoriDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShikimoriStudiosDTO {

        private Long id;
        private String name;
        private String filteredName;
        private Boolean real;
        @JsonProperty("image")
        private String imageDTO;

        public ShikimoriStudiosDTO() {}

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

        public String getFilteredName() {
            return filteredName;
        }

        public void setFilteredName(String filteredName) {
            this.filteredName = filteredName;
        }

        public Boolean getReal() {
            return real;
        }

        public void setReal(Boolean real) {
            this.real = real;
        }

    public String getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(String imageDTO) {
        this.imageDTO = imageDTO;
    }
}
