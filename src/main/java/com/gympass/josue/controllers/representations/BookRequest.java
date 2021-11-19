package com.gympass.josue.controllers.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gympass.josue.models.Enums.Language;
import com.gympass.josue.models.Enums.Publisher;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Validated
public @Data class BookRequest {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String author;
    @NotNull
    Publisher publisher;
    @NotNull
    Integer pages;
    @JsonProperty("book_language")
    Language bookLanguage;
    @JsonProperty("cover_image")
    String coverImage;
    boolean published;
    @JsonProperty("publication_date")
    OffsetDateTime publicationDate;
    BigDecimal price;

    public void setDefaultsValuesIfNull() {
        if (this.price == null) {
            this.setPrice(new BigDecimal(0));
        }
        if (this.bookLanguage == null) {
            this.setBookLanguage(Language.UNKNOWN);
        }
    }

}
