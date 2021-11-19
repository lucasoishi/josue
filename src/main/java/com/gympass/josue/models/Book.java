package com.gympass.josue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.models.Enums.Language;
import com.gympass.josue.models.Enums.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public @Data class Book extends BaseEntity {
    @NotNull
    protected String name;
    @NotNull
    protected String author;
    protected Publisher publisher;
    @NotNull
    protected Integer pages;
    protected BigDecimal price;
    @JsonProperty("publication_date")
    protected OffsetDateTime publicationDate;
    protected boolean published;
    @JsonProperty("cover_image")
    protected String coverImage;
    @JsonProperty("book_language")
    protected Language bookLanguage;

    public static Book fromBookCreationRequest(BookRequest bookRequest) {
        bookRequest.setDefaultsValuesIfNull();
        return new Book(
                bookRequest.getName(),
                bookRequest.getAuthor(),
                bookRequest.getPublisher(),
                bookRequest.getPages(),
                bookRequest.getPrice(),
                bookRequest.getPublicationDate(),
                bookRequest.isPublished(),
                bookRequest.getCoverImage(),
                bookRequest.getBookLanguage()
        );
    }
}
