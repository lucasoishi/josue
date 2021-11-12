package com.gympass.josue.controllers.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gympass.josue.models.Enums.Language;
import com.gympass.josue.models.Enums.Publisher;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Validated
public class BookRequest {
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

    public Language getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(Language bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public OffsetDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(OffsetDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setDefaultsValuesIfNull() {
        if (this.price == null) {
            this.setPrice(new BigDecimal(0));
        }
        if (this.bookLanguage == null) {
            this.setBookLanguage(Language.UNKNOWN);
        }
    }

}
