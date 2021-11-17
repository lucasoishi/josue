package com.gympass.josue.controllers.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.models.Book;
import com.gympass.josue.models.Enums.Language;
import com.gympass.josue.models.Enums.Publisher;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Validated
public class BookResponse extends Book {
    @JsonProperty("readable_book_language")
    private String readableBookLanguage;
    @JsonProperty("readable_publisher")
    private String readablePublisher;

    public BookResponse() {
    }

    public BookResponse(
            UUID id,
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
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.price = price;
        this.bookLanguage = bookLanguage;
        this.readableBookLanguage = bookLanguage.toString();
        this.readablePublisher = publisher.toString();
        this.coverImage = coverImage;
        this.publicationDate = publicationDate;
        this.published = published;
    }
    public static BookResponse fromBook(Book book) {
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPages(),
                book.getPrice(),
                book.getBookLanguage(),
                book.getCoverImage(),
                book.getPublicationDate(),
                book.isPublished()
        );
    }
}