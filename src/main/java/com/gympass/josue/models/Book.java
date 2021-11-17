package com.gympass.josue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.models.Enums.Language;
import com.gympass.josue.models.Enums.Publisher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    protected UUID id;
    protected String name;
    protected String author;
    protected Publisher publisher;
    protected Integer pages;
    protected BigDecimal price;
    @JsonProperty("publication_date")
    protected OffsetDateTime publicationDate;
    protected boolean published;
    @JsonProperty("cover_image")
    protected String coverImage;
    @JsonProperty("book_language")
    protected Language bookLanguage;

    public Book() {
    }

    public Book(
            String name,
            String author,
            Publisher publisher,
            Integer pages,
            BigDecimal price,
            Language bookLanguage,
            String coverImage,
            OffsetDateTime publicationDate,
            boolean published
    ) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.price = price;
        this.bookLanguage = bookLanguage;
        this.coverImage = coverImage;
        this.publicationDate = publicationDate;
        this.published = published;
    }

    public static Book fromBookCreationRequest(BookRequest bookRequest) {
        bookRequest.setDefaultsValuesIfNull();
        return new Book(
                bookRequest.getName(),
                bookRequest.getAuthor(),
                bookRequest.getPublisher(),
                bookRequest.getPages(),
                bookRequest.getPrice(),
                bookRequest.getBookLanguage(),
                bookRequest.getCoverImage(),
                bookRequest.getPublicationDate(),
                bookRequest.isPublished()
        );
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OffsetDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(OffsetDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Language getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(Language bookLanguage) {
        this.bookLanguage = bookLanguage;
    }
}
